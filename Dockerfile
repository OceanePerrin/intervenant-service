FROM amazoncorretto:17.0.5
VOLUME /tmp
ADD target/intervenant-service-1.0.jar intervenant-service.jar
RUN bash -c 'touch /intervenant-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/intervenant-service.jar"]