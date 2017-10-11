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
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "org.scalatest" %%% "scalatest" % "3.0.1" % Test
    )
  )

val webtest = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin)
  .settings(Settings.commonSettings)
  .settings(
    //webpackBundlingMode := BundlingMode.LibraryAndApplication(),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
//    npmDependencies in Compile ++= Seq(
//      "react" -> "15.6.1",
//      "react-dom" -> "15.6.1",
//      "jsdom" -> "11.3.0"
//      //      "redux" -> "^3.6.0",
//      //      "react-redux" -> "^5.0.3"
//    ),
//    version in webpack := "2.2.1",
//    version in installWebpackDevServer := "2.4.1"
  )