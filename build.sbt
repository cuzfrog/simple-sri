shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

version in ThisBuild := "0.1.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.3"
crossScalaVersions in ThisBuild := Seq("2.11.11", "2.12.3")

val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    name := "sri",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
      "org.scala-js" %%% "scalajs-dom" % "0.9.3"
    )
  )

val tests = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    scalaJSLinkerConfig ~= {_.withModuleKind(ModuleKind.CommonJSModule)},
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "diode" % "1.1.2",
      "com.lihaoyi" %%% "utest" % "0.5.4" % Test
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )