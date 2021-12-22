FROM openjdk:11

RUN mkdir -p /app && \
    chown -R daemon /app

USER daemon
WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar /app/spring-container.jar

CMD ["java", "-jar", "/app/spring-container.jar"]
EXPOSE 8080