FROM tomcat:6.0

MAINTAINER Marcus Gomes <email>

# Download the Fosstrak fc webclient from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/fc/fc-webclient/1.2.0/fc-webclient-1.2.0-bin-with-dependencies.zip /tmp/fc-webclient.zip

# Change the working directory
WORKDIR /tmp

# Extract downloaded file and copy the .war to the Tomcat webapps directory
RUN unzip fc-webclient.zip && \
    rm fc-webclient.zip && \
    mv fc-webclient-* fc-webclient && \
    mv fc-webclient/*.war /usr/local/tomcat/webapps/fc-webclient-1.2.war

# Download the Fosstrak capturing app from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/capturingapp/capturingapp/0.1.1/capturingapp-0.1.1.war /tmp/capturingapp-0.1.1.war

# Extract downloaded file and copy the .war to the Tomcat webapps directory
RUN mv capturingapp-0.1.1.war /usr/local/tomcat/webapps/capturingapp-0.1.1.war

# Run the server
CMD ["catalina.sh", "run"]
