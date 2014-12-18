package com.apium.any.pdf

import java.io.InputStream

import com.apium.any.tree.generator.{DocumentGenerator, GeneratorSource}
import com.apium.any.tree.{Chapter, Document}
import org.apache.pdfbox.pdmodel.PDDocument

/**
 * Created by kevin on 11/30/14.
 */

case class PdfGeneratorSource(override val source: PDDocument) extends GeneratorSource[PDDocument]

class PdfDocumentParser extends DocumentGenerator[PdfGeneratorSource] {
  private[this] val pageParser = new PdfPageParser

  def generate(document: PdfGeneratorSource): Document = {
    val pages = (1 to document.source.getNumberOfPages).par.map(PdfPageGeneratorSource(document.source, _)).map(pageParser.generate).seq
    document.source.close()
    
    Document(Seq(Chapter("", pages)))
  }
}

object PdfDocumentParser {
  def sourceFromInputStream(source: InputStream): PdfGeneratorSource = {
    PdfGeneratorSource(PDDocument.load(source))
  }
}
