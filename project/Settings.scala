import sbt.Keys._
import sbt._

object Settings {
  val commonSettings = Seq(
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:implicitConversions",
      "-P:scalajs:sjsDefinedByDefault"
    ),
    resolvers += Resolver.jcenterRepo,
    resolvers += Resolver.bintrayRepo("cuzfrog", "maven"),
    organization := "com.github.cuzfrog",
    licenses += ("Apache-2.0", url("http://www.opensource.org/licenses/apache2.0.php"))
  )
}
