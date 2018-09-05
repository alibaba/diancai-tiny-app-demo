FROM maven:3-jdk-8

add settings.xml /root/.m2/settings.xml

ENV APP_SRC_DIR /home/admin/app_src
RUN mkdir -p ${APP_SRC_DIR}
ADD . ${APP_SRC_DIR}

WORKDIR ${APP_SRC_DIR}

RUN mvn clean package
RUN rm -f /root/.m2/settings.xml

ENV APP_DIR /home/admin/app
WORKDIR ${APP_DIR}
RUN mkdir -p ${APP_DIR}
RUN cp ${APP_SRC_DIR}/target/sell-tiny-app-web-1.0-SNAPSHOT-executable.jar ${APP_DIR}/app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${APP_DIR}/app.jar" ]