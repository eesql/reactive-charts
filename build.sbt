name := """reactive-stocks"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.5"

// scalaz-bintray resolver needed for specs2 library
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  ws, // Play's web services module
  specs2 % Test,
  "org.specs2" %% "specs2-matcher-extra" % "3.6" % Test,
  "org.easytesting" % "fest-assert" % "1.4" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % Test,
  "org.webjars" % "bootstrap" % "2.3.2",
  "org.webjars" % "flot" % "0.8.0",

  "org.apache.hbase"   %   "hbase-client"           % "1.2.1" ,
  "org.apache.hbase"   %   "hbase-common"           % "1.2.1" ,
  "org.apache.hbase"   %   "hbase-server"           % "1.2.1" ,
  "org.apache.hadoop"  %   "hadoop-client"          % "2.3.0"


).map(_.exclude("org.slf4j", "slf4j-log4j12")).map(_.exclude("com.google.guava","guava"))

libraryDependencies ++= Seq(
  "com.google.guava"   % "guava"                    % "16.0.1"
)

routesGenerator := InjectedRoutesGenerator

fork in run := true


