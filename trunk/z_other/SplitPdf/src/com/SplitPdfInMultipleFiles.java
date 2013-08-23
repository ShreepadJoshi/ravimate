package com;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class SplitPdfInMultipleFiles {

	static String fileNeedsToBySplite = new String("How to.pdf");

	/**
	 * @param args
	 *            merge files
	 */
	public static void main(String[] args) {

		try {

			System.out.println("Reading " + fileNeedsToBySplite);
			PdfReader reader = new PdfReader(fileNeedsToBySplite);

			int totlePages = reader.getNumberOfPages();
			System.out.println(totlePages);

			for (int currentPage = 1; currentPage < totlePages; currentPage++) {
				String outPutFileName = getOutPutFileName(currentPage);
				System.out.println("Writing " + outPutFileName);

				Document document = new Document(
						reader.getPageSizeWithRotation(1));
				PdfCopy writer = new PdfCopy(document, new FileOutputStream(
						outPutFileName));
				document.open();

				PdfImportedPage page = writer.getImportedPage(reader,
						currentPage);
				writer.addPage(page);

				document.close();
				writer.close();

			}
			reader.close();
			System.out.println("Done..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getOutPutFileName(int currentPage) {
		String outPutFileName = "";
		int indexOfDOT = fileNeedsToBySplite.lastIndexOf(".");

		// If there wasn't any '.' just return the string as is.
		if (indexOfDOT == -1)
			outPutFileName = fileNeedsToBySplite;

		// Otherwise return the string, up to the dot.
		outPutFileName = fileNeedsToBySplite.substring(0, indexOfDOT);

		outPutFileName = "output/" + outPutFileName + "_" + currentPage
				+ ".pdf";

		return outPutFileName;

	}
}