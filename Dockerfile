FROM amazoncorretto:17.0.9-al2-native-headless
WORKDIR /temp
COPY target/constructionApp-0.0.1-SNAPSHOT.jar constructionApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "constructionApp.jar"]