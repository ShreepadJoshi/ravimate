package com;

import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.pdf.PRStream;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfObject;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStream;

public class ExtractImagesFromPDF {

	public static void main(String[] args) throws Exception {

		PdfReader reader;

		String path = "output/Deputation Letter4.pdf";

		

		File file = new File(path);
		
		System.out.println(file.getAbsolutePath());

		if (file.isFile() && file.canRead()) {

			reader = new PdfReader(file.getAbsolutePath());


			for (int i = 0; i < reader.getXrefSize(); i++)

			{

				PdfObject pdfobj = reader.getPdfObject(i);

				if (pdfobj != null) {

					if (pdfobj.isStream()) {

						PdfStream stream = (PdfStream) pdfobj;

						PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);

						if (pdfsubtype != null) {

							// PDF Subtype OK

							if (pdfsubtype.toString().equals(
									PdfName.IMAGE.toString())) {

								// image found

								byte[] img = PdfReader
										.getStreamBytesRaw((PRStream) stream);

								FileOutputStream out = new FileOutputStream(
										new File(path + "_" + i + ".jpg"));

								out.write(img);

								out.flush();

								out.close();

							}

						}

					}

				}

			}

		}

	}

}
