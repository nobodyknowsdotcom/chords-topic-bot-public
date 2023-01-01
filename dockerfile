# build jar with gradle
FROM gradle:7.2.0-jdk17 AS build
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# actual container
FROM --platform=linux/amd64 openjdk:11-jdk-alpine
ENV ARTIFACT_NAME=telegram-bot-0.0.1.jar

WORKDIR $APP_HOME
COPY  ./build/libs/$ARTIFACT_NAME .

EXPOSE 80
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}