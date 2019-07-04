FROM oracle/graalvm-ce:19.0.0 as graalvm
COPY . /home/app/mn-sample-api
WORKDIR /home/app/mn-sample-api
RUN gu install native-image
RUN native-image --no-server -cp target/mn-sample-api-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/mn-sample-api .
ENTRYPOINT ["./mn-sample-api"]
