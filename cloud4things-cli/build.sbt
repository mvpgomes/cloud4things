name := "Cloud4ThingsCLI"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq("org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.0",
                            "org.apache.cxf" % "cxf-rt-transports-http" % "3.1.0",
                            "org.apache.cxf" % "cxf-rt-databinding-jaxb" % "3.1.0",
                            "javax.xml.bind" % "jaxb-api" % "2.2.12",
                            "org.eclipse.persistence" % "org.eclipse.persistence.moxy" % "2.6.0")