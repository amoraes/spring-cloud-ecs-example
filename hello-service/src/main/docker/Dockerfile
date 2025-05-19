FROM alpine:3.21.3
EXPOSE 8080
RUN /bin/sh -c "apk add --no-cache bash curl jq dos2unix openjdk17-jdk"
COPY maven/app.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN dos2unix /entrypoint.sh
ENTRYPOINT ["/bin/sh", "entrypoint.sh"]