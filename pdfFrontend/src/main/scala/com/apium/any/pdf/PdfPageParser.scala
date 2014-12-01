package com.apium.any.pdf

import java.io.StringWriter

import com.apium.any.tree.{Document, Page, Paragraph, TreeNode}
import com.apium.any.tree.generator.{Generator, GeneratorSource}
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFTextStripper

/**
 * Created by kevin on 11/29/14.
 */

case class PdfPageGeneratorSource(document: PDDocument, page: Int)
  extends GeneratorSource[String] {
  private[this] val textStripper = new PDFTextStripper()

  def source = {
    val output = new StringWriter()
    textStripper.setStartPage(page)
    textStripper.setEndPage(page)
    textStripper.writeText(document, output)

    output.toString.trim
  }
}

class PdfPageParser extends Generator[PdfPageGeneratorSource] {
  def generate(page: PdfPageGeneratorSource): Page = {
    Page(page.source.split("\n").filter(_.trim.nonEmpty).map(Paragraph))
  }
}
