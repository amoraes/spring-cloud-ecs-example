FROM openjdk:8-jdk-alpine
EXPOSE 8761
RUN /bin/sh -c "apk add --no-cache curl"
RUN /bin/sh -c "apk add --no-cache jq"
RUN /bin/sh -c "apk add --no-cache dos2unix"
COPY @project.build.finalName@.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN dos2unix /entrypoint.sh
ENTRYPOINT ["/bin/sh", "entrypoint.sh"]