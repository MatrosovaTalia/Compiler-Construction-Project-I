FROM openjdk:8-slim-buster

WORKDIR /app

RUN set -ex; \
    apt-get update; \
    apt-get install -y gcc curl make m4; \
    curl -o kawa-3.1.1.tar.gz https://ftp.gnu.org/pub/gnu/kawa/kawa-3.1.1.tar.gz; \
    tar xzf kawa-3.1.1.tar.gz; \
    ls; \
    cd kawa-3.1.1 && ./configure && make

