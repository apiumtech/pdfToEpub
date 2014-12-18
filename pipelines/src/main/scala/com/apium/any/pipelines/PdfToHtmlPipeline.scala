package com.apium.any.pipelines

import java.io._

import com.apium.any.html.backend.HtmlGenerator
import com.apium.any.pdf.PdfDocumentParser

/**
 * Created by kevin on 12/18/14.
 */
object PdfToHtmlPipeline {
  def apply(pdf: InputStream, writer: Writer) = {
    val generator = new HtmlGenerator
    val documentParser = new PdfDocumentParser
    val source = PdfDocumentParser.sourceFromInputStream(pdf)

    writer.write(generator.generate(documentParser.generate(source)))
  }
}
