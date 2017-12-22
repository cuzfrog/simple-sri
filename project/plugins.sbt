addSbtPlugin("com.github.cuzfrog" % "sbt-tmpfs" % "0.3.2")
//addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
//addSbtPlugin("com.dwijnand" % "sbt-dynver" % "1.3.0")
//addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.9.0")

//release:
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")

//scalajs
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.21")

//source map
//addSbtPlugin("com.thoughtworks.sbt-scala-js-map" % "sbt-scala-js-map" % "2.0.0")
libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "4.9.2.201712150930-r"