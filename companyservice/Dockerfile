FROM java:8

EXPOSE 8083

ADD target/company-service.jar company-service.jar

ENTRYPOINT ["java", "-jar", "company-service.jar"]