package test.apium.any.pdf

import com.apium.any.pdf.{PdfPageParser, PdfPageGeneratorSource}
import com.apium.any.tree.{Paragraph, Page}
import org.apache.pdfbox.pdmodel.PDDocument
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by kevin on 11/30/14.
 */
class PdfPageParserSpecification extends FlatSpec with Matchers {
  "A PdfPageParserGeneratorSource" should "read the values for the first page in a document" in {
    val generatorSource = exerciseNewSource("page1.pdf", 1)
    generatorSource.source should be("Hello world!")
  }

  it should "read the values for the first page in a multi-page document" in {
    val generatorSource = exerciseNewSource("page2.pdf", 1)
    generatorSource.source should be("This is the first page!")
  }

  it should "read the values for a specific page in a document" in {
    val generatorSource = exerciseNewSource("page2.pdf", 2)
    generatorSource.source should be("This is the second page!")
  }

  it should "read the contents of a page as one paragraph" in {
    val generatorSource = exerciseNewSource("page1.pdf", 1)
    val sut = exerciseNewParser

    val result = sut.generate(generatorSource)
    result.children should have length 1
  }

  it should "read the contents of a page as one paragraph with valid content" in {
    val generatorSource = exerciseNewSource("page1.pdf", 1)
    val sut = exerciseNewParser

    val result = sut.generate(generatorSource)
    result.children should be(Seq(Paragraph("Hello world!")))
  }

  it should "read the contents of a page as multiple paragraphs with valid content" in {
    val generatorSource = exerciseNewSource("page1-multi-paragraph.pdf", 1)
    val sut = exerciseNewParser

    val result = sut.generate(generatorSource)
    result.children should be(Seq(Paragraph("This is the first paragraph"), Paragraph("This is the second paragraph :D")))
  }

  private[this] def exerciseLoadDocument(name: String): PDDocument = {
    PDDocument.load(getClass.getClassLoader.getResourceAsStream(name))
  }

  private[this] def exerciseNewSource(file: String, page: Int): PdfPageGeneratorSource = {
    new PdfPageGeneratorSource(exerciseLoadDocument(file), page)
  }

  private[this] def exerciseNewParser = new PdfPageParser

}
