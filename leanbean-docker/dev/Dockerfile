FROM ubuntu:14.04
MAINTAINER Jonas Björk <bjork@wisobi.com>

# Install dependencies
RUN apt-get update && apt-get install -y \
  ant \
  git \
  maven \
  mysql-client-5.6 \
  openjdk-7-jdk

# Setup Maven
ADD settings.xml root/.m2/

# Setup Git
ADD .gitconfig root/.gitconfig

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

CMD ["bash"]