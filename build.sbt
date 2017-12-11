shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

version in ThisBuild := "0.1.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.4"
crossScalaVersions in ThisBuild := Seq("2.11.11", "2.12.4")

val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    name := "simple-sri",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
      "org.scala-js" %%% "scalajs-dom" % "0.9.3"
    )
  )

val `sri-diode-connector` = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    name := "simple-sri-diode",
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "diode" % "1.1.2" % Provided
    )
  )

val myTestFramework: TestFramework = new TestFramework("anywhere.MyTestFramework")
val tests = project.dependsOn(root, `sri-diode-connector`)
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    scalaJSLinkerConfig ~= {_.withModuleKind(ModuleKind.CommonJSModule)},
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "diode" % "1.1.2",
      "com.lihaoyi" %%% "utest" % "0.6.0" % Test,
      "com.github.cuzfrog" %%% "sjest" % "0.1.0-SNAPSHOT" % Test
    ),
    testFrameworks ++= Seq(
      myTestFramework,
      new TestFramework("utest.runner.Framework")
    ),
    testOptions += Tests.Argument(myTestFramework,
      s"-opt.js.path:${(artifactPath in Test in fastOptJS).value}")
  )

//mount tmpfs:
onLoad in Global := {
  val insertCommand: State => State =
    (state: State) =>
      state.copy(remainingCommands = Exec(";root/tmpfsOn;tests/tmpfsOn", None) +: state.remainingCommands)
  (onLoad in Global).value andThen insertCommand
}