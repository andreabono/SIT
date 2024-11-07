FROM openjdk:22-jdk

# Instal Maven 
ARG MAVEN_VERSION=3.9.6
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Instal dependencies
RUN microdnf update -y && microdnf install -y \
    libX11 \
    glib2 \
    gtk3 \
    mesa-libGL \
    pulseaudio-libs \
    wget \
    tar \
    && microdnf clean all

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Definire la directory di lavoro
WORKDIR /app

COPY ../ .
RUN mvn --batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true clean
RUN mvn --batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true compile
RUN mvn --batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true package

# Comando di default
#CMD ["mvn", "spring-boot:run"]

