name := """cloud4things-config-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.0",
  "org.apache.cxf" % "cxf-rt-transports-http" % "3.1.0",
  "org.apache.cxf" % "cxf-rt-databinding-jaxb" % "3.1.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


fork in run := true