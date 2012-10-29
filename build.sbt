seq(githubRepoSettings: _*)

organization := "com.github.hexx"

name := "dispatch-lingr"

version := "0.0.1"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.1", "2.9.2")

scalacOptions ++= Seq("-deprecation", "-unchecked")

localRepo := Path.userHome / "github" / "maven"

githubRepo := "git@github.com:hexx/maven.git"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-lift-json" % "0.9.3",
  "org.scalaj" %% "scalaj-time" % "0.6",
  "org.scalatest" %% "scalatest" % "2.0.M4" % "test",
  "org.slf4j" % "slf4j-nop" % "1.7.2" % "test"
)
