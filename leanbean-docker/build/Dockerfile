FROM wisobi/leanbean-dev
MAINTAINER Jonas Björk <bjork@wisobi.com>
ENV DEBIAN_FRONTEND noninteractive

# Add source code to build
WORKDIR /opt
RUN git clone https://github.com/wisobi/leanbean.git
RUN git checkout 91f25642453

CMD ["bash"]