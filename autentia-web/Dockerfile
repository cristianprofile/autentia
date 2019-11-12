FROM openjdk:8-jre-alpine
# copy target folder

COPY / /api

#
RUN chmod +x ./api/target/bin/autentia

CMD ["./api/target/bin/autentia"]