FROM amazoncorretto:17.0.9-al2-native-headless
WORKDIR /temp
COPY target/constructionApp-0.0.1-SNAPSHOT.jar constructionapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "constructionapp.jar"]