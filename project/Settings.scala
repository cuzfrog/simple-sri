import sbt.Keys._
import sbt._
import com.github.cuzfrog.sbttmpfs.SbtTmpfsPlugin.autoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

object Settings {
  val commonSettings = Seq(
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-P:scalajs:sjsDefinedByDefault"
    ),
    resolvers ++= Seq(
      Resolver.bintrayRepo("cuzfrog", "maven"),
      Resolver.jcenterRepo
    ),
    organization := "com.github.cuzfrog",
    licenses += ("Apache-2.0", url("http://www.opensource.org/licenses/apache2.0.php")),
    tmpfsDirectoryMode := TmpfsDirectoryMode.Mount,
    logBuffered in Test := false,
    parallelExecution in Test := false
  )
}
