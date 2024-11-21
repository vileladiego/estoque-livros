FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ls -la /app
RUN chmod +x ./gradlew
RUN ./gradlew build

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
