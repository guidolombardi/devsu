FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY target/ms-clientes.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]