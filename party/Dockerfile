FROM arm64v8/eclipse-temurin:17.0.6_10-jre-jammy

RUN mkdir /opt/app

COPY ./target/mvp-gate-party-0.0.1-SNAPSHOT.jar /opt/app/party.jar

EXPOSE 8080 6565

CMD ["java", "-jar", "/opt/app/party.jar"]
