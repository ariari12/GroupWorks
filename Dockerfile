FROM openjdk:17-alpine
COPY app.jar app.jar
ENV KEY_PROPERTY aaa
#ENTRYPOINT ["java","-jar" , "app.jar"]
#ENTRYPOINT ["java","-jar" , "-Dcoolsms.encryptor.key.property=${KEY_PROPERTY}","-Dspring.profiles.active=prod","app.jar"]
ENTRYPOINT ["sh", "-c", "java -jar -Dcoolsms.encryptor.key.property=${KEY_PROPERTY} -Dspring.profiles.active=prod app.jar"]
