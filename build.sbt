import Settings._

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

version in ThisBuild := "0.3.0"
scalaVersion in ThisBuild := "2.12.4"
crossScalaVersions in ThisBuild := Seq("2.12.4")

val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJsMap)
  .settings(commonSettings, publicationSettings)
  .settings(
    name := "simple-sri",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
      "org.scala-js" %%% "scalajs-dom" % "0.9.4" % Provided
    )
  )

val `sri-diode-connector` = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin, ScalaJsMap)
  .settings(commonSettings, publicationSettings)
  .settings(
    name := "simple-sri-diode",
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "diode" % "1.1.2" % Provided
    )
  )

val `test-utils` = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin, ScalaJsMap)
  .settings(commonSettings, publicationSettings)
  .settings(
    name := "simple-sri-test-utils",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.4" % Provided
    )
  )

val jestFramework: TestFramework = new TestFramework("sjest.JestFramework")
val tests = project.dependsOn(root, `sri-diode-connector`, `test-utils` % Test)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "diode" % "1.1.2",
      "org.scala-js" %%% "scalajs-dom" % "0.9.4",
      "io.scalajs" %%% "nodejs" % "0.4.2" % Test,
      "com.github.cuzfrog" %%% "sjest" % "0.2.0" % Test
    ),
    testFrameworks += jestFramework,
    testOptions += Tests.Argument(jestFramework,
      s"-opt.js.path:${(artifactPath in Test in fastOptJS).value}")
  )

//mount tmpfs:
onLoad in Global := {
  val insertCommand: State => State =
    (state: State) =>
      state.copy(remainingCommands = Exec(";root/tmpfsOn;tests/tmpfsOn", None) +: state.remainingCommands)
  (onLoad in Global).value andThen insertCommand
}

//release:
import ReleaseTransformations._

pgpReadOnly := false
pgpSecretRing := baseDirectory.value / "project" / "codesigning.asc"
pgpPassphrase := sys.env.get("PGP_PASS").map(_.toArray)
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  releaseStepCommand("pgp-cmd recv-key 895B79DB hkp://keyserver.ubuntu.com"),
  releaseStepCommand("publishSigned"),
  releaseStepCommand("sri-diode-connector/publishSigned"),
  releaseStepCommand("test-utils/publishSigned"),
  releaseStepCommand("sonatypeReleaseAll")
)

