FROM gradle:7.4-jdk11

COPY . /app
COPY ./docker/entrypoint.sh /app
WORKDIR /app

RUN chown -R gradle /app
USER gradle

ENTRYPOINT ["./entrypoint.sh"]
