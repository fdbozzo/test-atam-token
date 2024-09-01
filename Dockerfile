FROM public.ecr.aws/docker/library/openjdk:17-alpine
LABEL authors="fdbozzo"
ARG DEPENDENCY=target/*.jar
COPY ${DEPENDENCY} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
