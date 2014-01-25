import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "shortURL"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "se.radley" %% "play-plugins-salat" % "1.3.0",
    "net.glxn" % "qrgen" % "1.3",
    "org.clapper" %% "grizzled-scala" % "1.1.4",
    "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
           
    routesImport += "se.radley.plugin.salat.Binders._",
    templatesImport += "org.bson.types.ObjectId",
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
  
  

}
