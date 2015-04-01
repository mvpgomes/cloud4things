FROM tomcat:6.0

MAINTAINER Marcus Gomes <email>

# Download the Fosstrak ale-repository from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/fc/fc-server/1.2.0/fc-server-1.2.0-bin-with-dependencies.zip /tmp/fc-server.zip

# Change the working directory
WORKDIR /tmp

# Extract downloaded file
RUN unzip fc-server.zip && \
    rm fc-server.zip && \
    mv fc-server-* fc-server && \
    mv fc-server/*.war /usr/local/tomcat/webapps/fc-server-1.2.0.war

# Run the server
CMD ["catalina.sh", "run"]
