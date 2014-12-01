name := "any-epub"

version := "1.0"

resolvers ++= Seq(
  "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.12.0" % "test",
  "org.apache.pdfbox" % "pdfbox" % "1.8.7"
)