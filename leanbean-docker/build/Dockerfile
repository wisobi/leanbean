FROM ubuntu:15.04
MAINTAINER Jonas Björk <bjork@wisobi.com>
ENV DEBIAN_FRONTEND noninteractive

# Setup node.js
RUN apt-get update && apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_4.x | bash -

# Install dependencies
RUN apt-get install -y \
  ant \
  maven \
  nodejs \
  openjdk-8-jdk

# Install Cordova
RUN npm install -g cordova

# Install Android SDK
# Additional packages needed for Ubuntu 13.10 and above
RUN dpkg --add-architecture i386 && \
  apt-get update && apt-get install -y \
  libncurses5:i386 \
  libstdc++6:i386 \
  zlib1g:i386
# Download and install SDK
WORKDIR /root
RUN curl http://dl.google.com/android/android-sdk_r24.2-linux.tgz | tar xz
ENV ANDROID_HOME /root/android-sdk-linux
ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools
RUN ( sleep 5 && while [ 1 ]; do sleep 1; echo y; done ) | android update sdk --all --filter build-tools-22.0.1,platform-tools,android-22,extra-android-support --no-ui --force

# Setup Git
ADD .gitconfig /root/.gitconfig

# Add source code to build
ADD leanbean-api /root/leanbean-api
ADD leanbean-google /root/leanbean-google
ADD leanbean-mobile /root/leanbean-mobile

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

CMD ["bash"]