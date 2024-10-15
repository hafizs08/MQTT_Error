FROM almalinux:8


RUN yum -y install java-11-openjdk-devel


RUN mkdir -p /opt/app

COPY target/demo.jar /opt/app/demo.jar

RUN useradd -ms /bin/bash appuser
RUN chown -R appuser:appuser /opt/app


USER appuser

WORKDIR /opt/app


CMD ["java", "-jar", "demo.jar"]
