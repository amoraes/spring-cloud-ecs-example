package com.github.amoraes.gateway.filter;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfiguration {

    private static final String SWAGGER_DOCS = "/docs/";
    private static final String SWAGGER_CONFIG = "/swagger-config";
    private static final String SLASH = "/";
    private static final String DOCS_SUFFIX = "-docs";

    @Bean
    public WebFilter swaggerDocsFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getURI().getPath().startsWith(SWAGGER_DOCS) && !request.getURI().getPath().endsWith(SWAGGER_CONFIG)) {

                ServerHttpResponse originalResponse = exchange.getResponse();
                DataBufferFactory bufferFactory = originalResponse.bufferFactory();

                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        if (!(body instanceof Flux)) {
                            return super.writeWith(body);
                        }

                        Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;

                        // modify the "servers" list to contain only the Gateway itself
                        return DataBufferUtils.join(flux)
                                .flatMap(dataBuffer -> {
                                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(bytes);
                                    DataBufferUtils.release(dataBuffer);
                                    String modifiedBody = modifyServers(new String(bytes, StandardCharsets.UTF_8));
                                    byte[] newBytes = modifiedBody.getBytes(StandardCharsets.UTF_8);
                                    DataBuffer newBuffer = bufferFactory.wrap(newBytes);
                                    getHeaders().setContentLength(newBytes.length);
                                    return super.writeWith(Mono.just(newBuffer));
                                });
                    }
                };

                exchange = exchange.mutate()
                        .request(request.mutate().path(request.getPath().toString().replace(SWAGGER_DOCS, SLASH).concat(DOCS_SUFFIX)).build())
                        .response(decoratedResponse)
                        .build();
                return chain.filter(exchange);
            }
            return chain.filter(exchange);
        };
    }

    private String modifyServers(String json) {
        int serversStartPos = json.indexOf("\"servers\":");
        int serverFinalPos = json.indexOf("]", serversStartPos);
        return json.substring(0, serversStartPos) + "\"servers\":[{\"url\":\"/\",\"description\":\"Gateway\"}]" + json.substring(serverFinalPos + 1);
    }


}
