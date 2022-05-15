FROM openjdk:11.0-jdk-slim-stretch
COPY "./target/mercado-credito-0.0.1-SNAPSHOT.jar" "mercado-credito.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mercado-credito.jar"]