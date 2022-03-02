# define base docker image
FROM openjdk:11
LABEL maintainer="nigel"
ADD target/nigel-iaido-person-0.0.1-SNAPSHOT.jar nigel-iaido-docker.jar
ENTRYPOINT ["java", "-jar", "nigel-iaido-docker.jar"]