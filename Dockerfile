FROM gcr.io/distroless/java17-debian11
VOLUME /tmp
USER nonroot
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","org.miage.intervenantservice.IntervenantServiceApplication"]