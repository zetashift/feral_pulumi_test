val Versions = new {
  val Scala = "3.2.1"
  val Feral = "0.2.0"
  val Pulumi = "0.7.1"
  val AwsPulumi = "5.29.1"
  val AwsNativePulumi = "0.49.0"
}

ThisBuild / scalaVersion := Versions.Scala
Compile / run := (infra / Compile / run).evaluated

lazy val infra = project
  .in(file("modules/infrastructure"))
  .settings(
    name := "infrastructure",
    libraryDependencies ++= Seq(
      "com.pulumi" % "pulumi" % Versions.Pulumi,
      "com.pulumi" % "aws" % Versions.AwsPulumi
    )
  )

lazy val lambda = project
  .in(file("modules/hello-feral"))
  .enablePlugins(LambdaJSPlugin)
  .settings(
    libraryDependencies += "org.typelevel" %%% "feral-lambda-http4s" % "0.2.0"
  )
