name := "MBR"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.github.melrief" % "purecsv_2.11" % "0.0.6",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
