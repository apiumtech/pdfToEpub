package com.apium.any.pipelines

import java.io.{FileInputStream, FileOutputStream}

import com.apium.any.epub.backend.EpubGenerator
import com.apium.any.pdf.PdfDocumentParser

/**
 * Created by kevin on 12/18/14.
 */
object PdfToEpubPipeline {
  def main(args: Array[String]) {
    val is = new FileInputStream("/home/kevin/Resources/PDF/PS Sisley FRE.pdf")
    val os = new FileOutputStream("/home/kevin/Resources/PDF/PS Sisley FRE-utf16.epub")

    val generator = new EpubGenerator
    val documentParser = new PdfDocumentParser
    val source = PdfDocumentParser.sourceFromInputStream(is)

    val parser = new PdfDocumentParser
    val doc = parser.generate(source)
    generator.generate(doc, os)

    is.close()
    os.close()
  }
}
