FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN dos2unix ./mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]