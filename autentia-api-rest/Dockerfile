FROM openjdk:8-jre-alpine
# copy target folder

COPY / /api

#RUN apk update && apk add bash
#
RUN chmod +x ./api/target/bin/autentia

CMD ["./api/target/bin/autentia"]

