package com.apium.any.pdf

import com.apium.any.tree.{Chapter, Document}
import com.apium.any.tree.generator.{GeneratorSource, DocumentGenerator}
import org.apache.pdfbox.pdmodel.PDDocument

/**
 * Created by kevin on 11/30/14.
 */

case class PdfGeneratorSource(override val source: PDDocument) extends GeneratorSource[PDDocument]

class PdfDocumentParser extends DocumentGenerator[PdfGeneratorSource] {
  private[this] val pageParser = new PdfPageParser

  def generate(document: PdfGeneratorSource): Document = {
    val pages = (1 to document.source.getNumberOfPages).par.map(PdfPageGeneratorSource(document.source, _)).map(pageParser.generate).seq
    Document(Seq(Chapter("", pages)))
  }

}
