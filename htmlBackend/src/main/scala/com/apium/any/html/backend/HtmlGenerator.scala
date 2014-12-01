package com.apium.any.html.backend

import com.apium.any.tree._

/**
 * Created by kevin on 11/30/14.
 */
object HtmlGenerator {
  def generate(doc: Document): String = {
    """
      |<html>
      | <head>
      | </head>
      | <body>
      | %s
      | </body>
      |</html>
    """.stripMargin.format(doc.children.map(generate).mkString("\n<!-- **** -->\n"))
  }

  def generate(el: TreeNode): String = el match {
    case c: Chapter => "<h1>%s</h1>\n%s".format(c.title, c.children.map(generate).mkString("\n"))
    case p: Page => "%s\n<hr />\n".format(p.children.map(generate).mkString("\n"))
    case par: Paragraph => "<p>%s</p>".format(par.text)
  }

  def main (args: Array[String]) {
    val x = generate(Document(Seq(Chapter("First chapter!", Seq(
      Page(Seq(Paragraph("Page 1 Paragraph 1"), Paragraph("Page 1 Paragraph 2"))),
      Page(Seq(Paragraph("Page 2 Paragraph 1"), Paragraph("Page 2 Paragraph 2"))))),
      Chapter("Second chapter!", Seq(
        Page(Seq(Paragraph("Page 3 Paragraph 1"), Paragraph("Page 3 Paragraph 2"))),
        Page(Seq(Paragraph("Page 4 Paragraph 1"), Paragraph("Page 4 Paragraph 2"))))))))

    println(x)
  }
}
