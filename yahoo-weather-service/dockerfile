FROM openjdk:8-jdk-alpine
WORKDIR  /springboot
EXPOSE 8080
COPY . .
RUN ./gradlew build
ENTRYPOINT ["java","-jar","build/libs/*.jar"]