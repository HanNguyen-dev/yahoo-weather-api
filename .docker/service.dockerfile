# build
FROM openjdk:8-jdk-alpine as build
WORKDIR /springboot
COPY ../yahoo-weather-service/ .
RUN ./gradlew build

# deploy
FROM build as deploy
WORKDIR /app
COPY --from=build /springboot/build .
EXPOSE 8080
ENTRYPOINT ["java","-jar","libs/*.jar"]