package com;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ConvertImageToPDF {

	static String nameOfImage = "avani_passport1.jpg";

	/**
	 * @param args
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException {
		
		String pathOfImage = "input/" + nameOfImage;
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(pathOfImage
					+ ".pdf"));
			document.open();

			Image image = Image.getInstance(pathOfImage);
			// image.scalePercent(33);

			Rectangle pageSize = document.getPageSize();
		
			image.scaleToFit(pageSize.getWidth() - 80, pageSize.getHeight() - 80);

			// float height = image.getScaledHeight();
			// float width = image.getScaledWidth();
			// document.setPageSize(new Rectangle(height, width));

			document.add(image);

			document.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
