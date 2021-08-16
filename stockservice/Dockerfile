FROM java:8

EXPOSE 8082

ADD target/stock-service.jar stock-service.jar

ENTRYPOINT ["java", "-jar", "stock-service.jar"]