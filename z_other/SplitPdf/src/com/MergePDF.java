package com;

//Please include the itext-2.1.4.jar in the classpath
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;

public class MergePDF {

      public static void main(String[] arg) {
      try {
              List<InputStream> pdfs = new ArrayList<InputStream>();
              pdfs.add(new FileInputStream("input/Avani.jpg.pdf"));
              pdfs.add(new FileInputStream("input/Avani2.jpg.pdf"));
              OutputStream output = new FileOutputStream("output/Avani_L_card.pdf");
              // boolean flag that represents whether you need to include page number or not
              MergePDF.concatPDFs(pdfs, output, true);        
      }

      catch (Exception e) 
      {
              System.err.println("Exception occurs : " + e.getMessage());
      }
      }


      public static void concatPDFs(List<InputStream> streamOfPDFFiles,
                      OutputStream outputStream, boolean paginate) {

      Document document = new Document();

      try {
              List<InputStream> pdfs = streamOfPDFFiles;
              List<PdfReader> readers = new ArrayList<PdfReader>();
              int totalPages = 0;
              Iterator<InputStream> iteratorPDFs = pdfs.iterator();

              // Create Readers for the pdfs.
              while (iteratorPDFs.hasNext()) {
                      InputStream pdf = iteratorPDFs.next();
                      PdfReader pdfReader = new PdfReader(pdf);
                      readers.add(pdfReader);
                      totalPages += pdfReader.getNumberOfPages();
              }

              // Create a writer for the outputstream
              PdfWriter writer = PdfWriter.getInstance(document, outputStream);
              document.open();
              BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                              BaseFont.CP1252, BaseFont.NOT_EMBEDDED);


              // Holds the PDF data
              PdfContentByte cb = writer.getDirectContent();

              PdfImportedPage page;
              int currentPageNumber = 0;
              int pageOfCurrentReaderPDF = 0;
              Iterator<PdfReader> iteratorPDFReader = readers.iterator();

              // Loop through the PDF files and add to the output.
              while (iteratorPDFReader.hasNext()) {
                      PdfReader pdfReader = iteratorPDFReader.next();

              // Create a new page in the target for each source page.
                      while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                            document.newPage();
                            pageOfCurrentReaderPDF++;
                            currentPageNumber++;
                            page = writer.getImportedPage(pdfReader,pageOfCurrentReaderPDF);
                            cb.addTemplate(page, 0, 0);

                              // Code for pagination.
                              if (paginate) {
                                   cb.beginText();
                                   cb.setFontAndSize(bf, 9);
                                   cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
                                   + currentPageNumber + " of " + totalPages, 520, 5, 0);
                                   cb.endText();
                              }
                      }
                      pageOfCurrentReaderPDF = 0;
              }
              outputStream.flush();
              document.close();
              outputStream.close();
      }
      
      catch (Exception e) {
              System.err.println("Exception : " + e.getMessage());
      }
      
      finally {
              if (document.isOpen())
                      document.close();
              
              try {
                      if (outputStream != null)
                              outputStream.close();
                  }
              
              catch (IOException ioe) {
                      System.err.println("Exception : " + ioe.getMessage());
              }
      }
      }
}