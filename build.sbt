name := "sri"
version := "0.1.0"
scalaVersion := "2.12.3"
crossScalaVersions := Seq("2.11.11", "2.12.3")

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-P:scalajs:sjsDefinedByDefault"
)

//bintray
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("cuzfrog", "maven")
organization := "com.github.cuzfrog"
licenses += ("Apache-2.0", url("http://www.opensource.org/licenses/apache2.0.php"))


scalaJSUseMainModuleInitializer in Test := true
scalaJSModuleKind in Runtime := ModuleKind.CommonJSModule

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "org.scalatest" %%% "scalatest" % "3.0.1" % Test
)

parallelExecution in Test := false
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
npmDependencies in Compile ++= Seq(
  "react" -> "15.6.1",
  "react-dom" -> "15.6.1",
  "jsdom" -> "11.3.0"
  //      "redux" -> "^3.6.0",
  //      "react-redux" -> "^5.0.3"
)

version in webpack := "2.2.1"
version in installWebpackDevServer := "2.4.1"

enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)