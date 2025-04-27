FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean install
EXPOSE 8000
CMD ["java", "-jar", "target/websocket-chat-0.0.1-SNAPSHOT.jar"]
