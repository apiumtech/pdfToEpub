package test.apium.any.pdf

import com.apium.any.pdf.{PdfDocumentParser, PdfGeneratorSource}
import com.apium.any.tree.{Chapter, Page, Paragraph, Document}
import org.apache.pdfbox.pdmodel.PDDocument
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by kevin on 11/30/14.
 */
class PdfDocumentParserSpecification extends FlatSpec with Matchers {
  "A PdfDocumentParser" should "read documents of one page and one paragraph" in {
    val sut = new PdfDocumentParser
    val tree = sut.generate(exerciseNewSource("page1.pdf"))
    tree should be(Document(Seq(Chapter("", Seq(Page(Seq(Paragraph("Hello world!"))))))))
  }

  it should "read documents of one page and more than one paragraphs" in {
    val sut = new PdfDocumentParser
    val tree = sut.generate(exerciseNewSource("page1-multi-paragraph.pdf"))
    tree should be(Document(Seq(Chapter("", Seq(Page(Seq(Paragraph("This is the first paragraph"), Paragraph("This is the second paragraph :D"))))))))
  }

  it should "read documents of two pages and one paragraph for page" in {
    val sut = new PdfDocumentParser
    val tree = sut.generate(exerciseNewSource("page2.pdf"))
    tree should be(Document(Seq(Chapter("", Seq(
      Page(Seq(Paragraph("This is the first page!"))),
      Page(Seq(Paragraph("This is the second page!"))))))))
  }

  it should "read documents of two pages and more than one paragraphs for page" in {
    val sut = new PdfDocumentParser
    val tree = sut.generate(exerciseNewSource("page2-multi-paragraph.pdf"))
    tree should be(Document(Seq(Chapter("", Seq(
      Page(Seq(Paragraph("Page 1 Paragraph 1"), Paragraph("Page 1 Paragraph 2"))),
      Page(Seq(Paragraph("Page 2 Paragraph 1"), Paragraph("Page 2 Paragraph 2"))))))))
  }

  private[this] def exerciseNewSource(name: String): PdfGeneratorSource = {
    val x = PDDocument.load(getClass.getClassLoader.getResourceAsStream(name))
    new PdfGeneratorSource(x)
  }
}
