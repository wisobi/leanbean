FROM mysql:5.6
MAINTAINER Jonas Björk <bjork@wisobi.com>

ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_USER leanbean
ENV MYSQL_PASSWORD leanbean
ENV MYSQL_DATABASE leanbean

# Add MySQL server specific options 
RUN echo "bind-address	= 0.0.0.0" >> /usr/my.cnf

EXPOSE 3306

#CMD ["/usr/bin/mysqld_safe > /dev/null 2>&1 &"]
