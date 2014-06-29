package com;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class SplitPdfByPageNumber {

	static int startPage = 0;
	static int endPage = 3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// String inFile = args[0].toLowerCase();
			String inFile = "input/Deputation Letter .pdf";
			System.out.println("Reading " + inFile);
			PdfReader reader = new PdfReader(inFile);			
			
			System.out.println("Number of pages : " + endPage);
			String outFile = inFile + "_" + startPage + "_" + endPage + ".pdf";
			System.out.println("Writing " + outFile);

			Document document = new Document(reader.getPageSizeWithRotation(1));
			PdfCopy writer = new PdfCopy(document,
					new FileOutputStream(outFile));
			document.open();

			while (startPage < endPage) {
				PdfImportedPage page = writer.getImportedPage(reader,
						++startPage);
				writer.addPage(page);
			}
			
			document.close();
			writer.close();
			System.out.println("Done..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}