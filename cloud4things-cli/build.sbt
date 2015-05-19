name := "Cloud4ThingsCLI"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq("org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.0",
                            "org.apache.cxf" % "cxf-rt-transports-http" % "3.1.0",
                            "org.apache.cxf" % "cxf-rt-databinding-jaxb" % "3.1.0",
                            "org.json4s" %% "json4s-native" % "3.2.11")