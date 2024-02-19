#Stage 1 Build the application
FROM openjdk:17-oracle AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

#Stage 2 Create image
FROM openjdk:17-oracle
WORKDIR /opt
COPY --from=builder /app/target/*.jar /opt/app.jar
ENTRYPOINT exec java -jar app.jar