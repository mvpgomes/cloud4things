FROM tomcat:7.0

MAINTAINER Marcus Gomes <email>

# Download the Fosstrak ale-repository from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/fc/fc-server/1.2.0/fc-server-1.2.0-bin-with-dependencies.zip /tmp/fc-server.zip

# Change the working directory
WORKDIR /tmp

# Extract downloaded file
RUN unzip fc-server.zip && \
    rm fc-server.zip && \
    mv fc-server-* fc-server && \
    unzip fc-server/*.war -d /usr/local/tomcat/webapps/fc-server-1.2.0 && \
    rm -r fc-server

# Add log4j configuration file
ADD files/log4j.xml /usr/local/tomcat/webapps/fc-server-1.2.0/WEB-INF/classes/log4j.xml

# Add a script to set the $CATALINA_OPTS environment variable
ADD files/setenv.sh /usr/local/tomcat/bin/setenv.sh

# Change working directory 
WORKDIR /usr/local/tomcat/bin

# Set the setenv.sh permissions
RUN chmod +x setenv.sh

# Set the CATALINA_OPTS variable
CMD source setenv.sh

# Run the server
CMD ["catalina.sh", "run"]

