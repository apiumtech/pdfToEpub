import sbt._
import Keys._

object Build extends Build {
  lazy val tree = project.settings(libraryDependencies ++= Seq(scalatest))

  /** front ends (tree generators) **/
  lazy val pdfFrontend = project.dependsOn(tree).settings(libraryDependencies ++= Seq(scalatest, pdfbox))
  /** back ends (file generators) **/
  lazy val epubBackend = project.dependsOn(tree).settings(libraryDependencies ++= Seq(scalatest))
  lazy val htmlBackend = project.dependsOn(tree).settings(libraryDependencies ++= Seq(scalatest))

  def scalatest = "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  def pdfbox = "org.apache.pdfbox" % "pdfbox" % "1.8.7"
}