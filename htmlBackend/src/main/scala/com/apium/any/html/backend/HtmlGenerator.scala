package com.apium.any.html.backend

import com.apium.any.tree._

/**
 * Created by kevin on 11/30/14.
 */
class HtmlGenerator {
  def generate(doc: Document): String = {
    val data = doc.children.map(generate).mkString("\n<!-- **** -->\n")
    data.length match {
      case 0 => ""
      case _ =>
        """
          <html>
           <head>
           </head>
           <body>
           %s
           </body>
          </html>
        """.stripMargin.format(data)
    }
  }

  def generate(el: TreeNode): String = el match {
    case c: Chapter => "<h1>%s</h1>\n%s".format(c.title, c.children.map(generate).mkString("\n"))
    case p: Page => "<div class='page'>%s\n</div>\n".format(p.children.map(generate).mkString("\n"))
    case par: Paragraph => "<p>%s</p>".format(par.text)
  }
}
