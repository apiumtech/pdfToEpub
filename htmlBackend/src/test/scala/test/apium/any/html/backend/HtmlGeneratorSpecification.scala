package test.apium.any.html.backend

import com.apium.any.html.backend.HtmlGenerator
import com.apium.any.tree.{Paragraph, Chapter, Page, Document}
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by kevin on 12/18/14.
 */
class HtmlGeneratorSpecification extends FlatSpec with Matchers {
  def exerciseEmptyDocument() = Document.emptyDocument
  def exerciseDocument(chapter: String = "some chapter", paragraph: String = "some paragraph") = {
    Document(Seq(Chapter(title = chapter, children = Seq(Page(Seq(Paragraph(paragraph)))))))
  }

  "A HtmlGenerator" should "generate an empty string on an empty document" in {
    val sut = new HtmlGenerator
    val str = sut.generate(exerciseEmptyDocument())
    str shouldBe empty
  }

  it should "generate a valid element from a page" in {
    val sut = new HtmlGenerator
    val str = sut.generate(exerciseDocument())
    str.contains("<div class='page'>") shouldBe true
  }

  it should "generate a valid element from a chapter" in {
    val sut = new HtmlGenerator
    val str = sut.generate(exerciseDocument(chapter = "some chapter"))
    str.contains("<h1>some chapter</h1>") shouldBe true
  }

  it should "generate a valid element from a paragraph" in {
    val sut = new HtmlGenerator
    val str = sut.generate(exerciseDocument(paragraph = "some paragraph"))
    str.contains("<p>some paragraph</p>") shouldBe true
  }
}
