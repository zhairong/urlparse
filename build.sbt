import _root_.sbt._

name := "parser"
version := "1.0"

import com.typesafe.sbt.packager.docker._

lazy val `urlparse` = (project in file("."))
  .enablePlugins(PlayJava)
  .enablePlugins(JavaAppPackaging, DockerPlugin)


scalaVersion := "2.11.7"

//libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs )

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers ++= Seq(
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"
)

val kamonVersion = "0.6.0"
val slf4jVersion = "1.7.12"
val cassandraVersion = "3.0.1"

//
libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  "org.slf4j" % "slf4j-api" % "1.7.12",
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "commons-beanutils" % "commons-beanutils" % "1.9.2",
  "commons-io" % "commons-io" % "2.4",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.2",
  "com.typesafe.play" %% "play-mailer" % "4.0.0",
  "org.assertj" % "assertj-core" % "3.1.0" % "test",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.12",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.6",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.5.0-akka-2.4.x"
)
fork in run := false
routesGenerator := InjectedRoutesGenerator

maintainer in Docker := "Rong Zhai <zhairong1975@google.com>"

javaOptions in Test += "-Dconfig.resource=test.conf"
