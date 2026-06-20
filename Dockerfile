FROM maven:4.0.0-rc-4-eclipse-temurin-25-alpine
WORKDIR /app
COPY . .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=0 /app/target/*.jar app.jar
EXPOSE 8065

ENTRYPOINT ["java", "-jar", "app.jar"]