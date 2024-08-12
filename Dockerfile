FROM openjdk:17-alpine
COPY app.jar app.jar
ENTRYPOINT ["java","-jar" , "-Dcoolsms.encryptor.key.property=${KEY_PROPERTY} -Dspring.profiles.active=prod","app.jar"]