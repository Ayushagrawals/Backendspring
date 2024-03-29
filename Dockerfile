from adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENV PORT=8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar	 