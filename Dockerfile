FROM gradle:latest AS BUILD
COPY . /temp
WORKDIR /temp
RUN gradle assemble

FROM openjdk:17
COPY --from=BUILD /temp/build/libs/rinha-backend-1.1.jar app.jar

ENTRYPOINT ["java","-jar", "-noverify", "/app.jar"]