FROM maven:3.9.6-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17.0.9
COPY --from=build /target/constructionApp-0.0.1-SNAPSHOT.jar constructionapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "constructionapp.jar"]