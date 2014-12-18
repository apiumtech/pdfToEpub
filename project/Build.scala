import sbt._
import Keys._

object Build extends Build {
  lazy val tree = project.settings(libraryDependencies ++= Seq(scalatest))

  /** front ends (tree generators) **/
  lazy val pdfFrontend = project.dependsOn(tree).settings(libraryDependencies ++= Seq(scalatest, pdfbox))
  /** back ends (file generators) **/
  lazy val epubBackend = project.dependsOn(tree)
    .settings(
      resolvers ++= Seq(repo.siegmann),
      libraryDependencies ++= Seq(scalatest, epublib)
    )

  lazy val htmlBackend = project.dependsOn(tree).settings(libraryDependencies ++= Seq(scalatest))

  lazy val pipelines = project
    .dependsOn(pdfFrontend, htmlBackend, epubBackend)
    .settings(libraryDependencies ++= Seq(scalatest, pdfbox, epublib, slf4j, xmlpull, kxml))

  def scalatest = "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  def pdfbox = "org.apache.pdfbox" % "pdfbox" % "1.8.7"
  def epublib = "nl.siegmann.epublib" % "epublib-core" % "3.1" from "http://github.com/psiegman/mvn-repo/raw/master/releases/nl/siegmann/epublib/epublib-core/3.1/epublib-core-3.1.jar"
  def slf4j = "org.slf4j" % "slf4j-api" % "1.7.7"
  def xmlpull = "xmlpull" % "xmlpull" % "1.1.3.4d_b4_min"
  def kxml = "net.sf.kxml" % "kxml2" % "2.3.0"

  object repo {
    def siegmann = "siegmann" at "http://github.com/psiegman/mvn-repo/raw/master/releases"
  }

}