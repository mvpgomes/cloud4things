FROM tomcat:6.0

MAINTAINER Marcus Gomes <email>

# Download the Fosstrak epcis-repository from source
ADD https://oss.sonatype.org/content/repositories/public/org/fosstrak/epcis/epcis-repository/0.5.0/epcis-repository-0.5.0-bin-with-dependencies.zip /tmp/epcis-repository.zip

# Download the MySQL ConnectorJ from source
ADD http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.33.tar.gz /tmp/mysql-connector.tar.gz

# Change the working directory
WORKDIR /tmp

# Extract EPCIS Repository file
RUN unzip epcis-repository.zip && \
    rm epcis-repository.zip && \
    mv epcis-repository-* epcis-repository && \
    mv epcis-repository/*.war /usr/local/tomcat/webapps/epcis-repository-0.5.0.war

# Extract MySQL ConnectorJ
RUN tar -zxvf mysql-connector.tar.gz && \
    rm mysql-connector.tar.gz && \
    mv mysql-connector-java-* mysql-connector-java && \
    cp mysql-connector-java/*.jar /usr/local/tomcat/lib/mysql-connector-java-5.1.33-bin.jar

## Configure the repository to connect to the database
COPY files/epcis-repository-0.5.0.xml /usr/local/tomcat/conf/Catalina/localhost/epcis-repository-0.5.0.xml

# Run the server
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
