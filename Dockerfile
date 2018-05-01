FROM openjdk:8

MAINTAINER Michael Hauss <mihauss@microsoft.com>
 
COPY out/artifacts/ /opt/start/.

WORKDIR /opt/start

ENTRYPOINT ["java", "-jar", "upload-legacy.jar", "$(date +%s | sha256sum | base64 | head -c 32)"]