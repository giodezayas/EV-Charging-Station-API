FROM openjdk:11-jre-slim

WORKDIR /app

COPY /target/EV_Charging_Station_API_jar/EV-Charging-Station-API.jar /app

CMD ["java", "-jar", "EV-Charging-Station-API.jar"]

