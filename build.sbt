name := """ld-rg-scala"""
organization := "launchdarkly"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test

libraryDependencies ++= Seq(
  "com.launchdarkly" % "launchdarkly-java-server-sdk" % "7.4.1"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "launchdarkly.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "launchdarkly.binders._"
