FROM java:8

EXPOSE 8081

ADD target/user-auth.jar user-auth.jar

ENTRYPOINT ["java", "-jar", "user-auth.jar"]