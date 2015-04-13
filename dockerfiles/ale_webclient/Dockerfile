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
    mv fc-webclient/*.war /usr/local/tomcat/webapps/fc-webclient-1.2.0.war

# Reader configuration files
ADD files/Reader_GoodsReceiving.xml Reader_GoodsReceiving.xml

ADD files/Reader_PointOfExit.xml Reader_PointOfExit.xml

ADD files/Reader_PointOfSale.xml Reader_PointOfSale.xml

# Run the server
CMD ["catalina.sh", "run"]
