FROM tomcat:7.0

MAINTAINER Marcus Gomes <email>

# Download the Fosstrak capturing app from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/capturingapp/capturingapp/0.1.1/capturingapp-0.1.1.war /tmp/capturingapp-0.1.1.war

# Change the working directory
WORKDIR /tmp

# Extract downloaded file and unpack the .war to the Tomcat webapps directory
RUN unzip capturingapp-0.1.1.war -d /usr/local/tomcat/webapps/capturingapp-0.1.1

# Confgure capturing application URL
ADD files/captureapplication.properties /usr/local/tomcat/webapps/capturingapp-0.1.1/WEB-INF/classes/captureapplication.properties

# Confgure capturing application changeset
ADD files/changeset.xml /usr/local/tomcat/webapps/capturingapp-0.1.1/WEB-INF/classes/changeset.xml

# Run the server
CMD ["catalina.sh", "run"]
