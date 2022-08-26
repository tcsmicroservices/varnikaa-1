FROM openjdk
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.securi ty.egd=file:/dev/./urandom","-jar","/app.jar"]