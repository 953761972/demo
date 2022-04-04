FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    APP_SLEEP=3 \
    JAVA_OPTS=""

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh xzq
WORKDIR /home/xzq

ADD startup.sh startup.sh
#设置国内镜像 安装curl、telnet
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories && apk update && apk add curl && apk add busybox-extras

RUN chmod 755 startup.sh && chown xzq:xzq startup.sh
USER xzq

ENTRYPOINT ["./startup.sh"]

EXPOSE 5566

ADD *.jar app.jar

