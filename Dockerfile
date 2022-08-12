FROM postgres:latest

ENV POSTGRES_DB=familydb
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

COPY docker-entrypoint-initdb.d /docker-entrypoint-initdb.d

