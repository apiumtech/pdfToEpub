package com.apium.any.epub.backend

import java.io.OutputStream

import com.apium.any.tree._
import nl.siegmann.epublib.domain._
import nl.siegmann.epublib.epub.EpubWriter

/**
 * Created by kevin on 12/18/14.
 */
class EpubGenerator {
  val writer = new EpubWriter

  def generate(document: Document, outputStream: OutputStream): Unit = {
    implicit val book = new Book()
    book.getMetadata.addTitle("Some Example Title")
    book.getMetadata.addAuthor(new Author("Chinaken","Brother"))
    book.getMetadata.addIdentifier(new Identifier("odf", "9sf89as9898asd987f7as98"))
    book.getMetadata.addDate(new Date(new java.util.Date))
    book.getMetadata.setLanguage("fr")

    document.children.foreach(process)

    writer.write(book, outputStream)
  }

  private def process(element: Chapter)(implicit book: Book): Unit = {
    book.addSection("some chapter", processResource(element, element.children))
  }

  private def processResource(chapter: Chapter, element: Seq[ChapterChildNode]): Resource = {
    new Resource(
      """
        |<html><head></head><body>%s</body></html>
      """.stripMargin.format(element.map {
      case a: Page => "<div>%s</div>".format(a.children.map(toHtml).mkString)
    }.mkString).getBytes("UTF-16"), "html/" + chapter.title + "_chapter.html")
  }

  private def toHtml(element: PageChildNode): String = element match {
    case p: Paragraph => "<p>%s</p>".format(p.text)
  }
}
