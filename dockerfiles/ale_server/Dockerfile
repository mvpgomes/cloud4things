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

# Add the Client configuration file
ADD files/SimulatorClient.xml /usr/local/tomcat/webapps/fc-server-1.2.0/WEB-INF/classes/props/SimulatorClient.xml

# Add the Controller configuration file
ADD files/SimulatorController.xml /usr/local/tomcat/webapps/fc-server-1.2.0/WEB-INF/classes/props/SimulatorController.xml

# Add log4j configuration file
ADD files/log4j.xml /usr/local/tomcat/webapps/fc-server-1.2.0/WEB-INF/classes/log4j.xml

# Run the server
CMD ["catalina.sh", "run"]
