package com.education.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.services.ContentUploadService;
import com.education.services.QuestionBankService;
import com.education.transferobj.ContentUploadTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class ContentRenderAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return performAction(mapping, form, request, response);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int contentID = Integer.parseInt(request.getParameter("contentId"));
		ContentUploadService service = new ContentUploadService();

		// Get content Blob details

		ContentUploadTO contentTO = service.getContent_Blob_ById(contentID);
		String contentType = Utilities.getMIMEHeader(contentTO
				.getContentTypeID());

		if (contentType.equalsIgnoreCase("mm")) {

			String finalPath = writeStreamToFile(contentTO, request);
//			response.sendRedirect(getServlet().getServletContext()
//					.getContextPath()
//					+ "/pages/freemindbrowser.jsp?mmfilepath="
//					+ getServlet().getServletContext().getContextPath()
//					+ finalPath);
			response.sendRedirect(getServlet().getServletContext()
					.getContext("")
					+ "/pages/freemindbrowser.jsp?mmfilepath="
					+ getServlet().getServletContext().getContext("")
					+ finalPath);

			return null;
		} else if (contentType.equalsIgnoreCase("video/x-msvideo")) {
			String finalPath = writeStreamToFile(contentTO, request);

//			request.setAttribute("filePath", getServlet().getServletContext()
//					.getContextPath()
//					+ finalPath);
			request.setAttribute("filePath", getServlet().getServletContext()
					.getContext("")
					+ finalPath);
			return mapping.findForward("video");
		}

		// Render content details to sream
		response.setContentType(contentType);
		response.setHeader("Content-disposition", " inline; filename="
				+ contentTO.getContentFileName());
		ServletOutputStream os = response.getOutputStream();
		InputStream in = contentTO.getContentFileStream();

		byte[] bytes = new byte[1024];
		int readByte = 0;
		while ((readByte = in.read(bytes)) != -1) {
			os.write(bytes, 0, readByte);
		}
		os.flush();
		os.close();
		in.close();
		return null;
	}

	@Override
	/**
	 * Return true for user as Admin Role
	 */
	protected boolean hasValidPermission(HttpServletRequest request) {
		String strRole = getUserRoleFromSession(request);
		if (strRole == null
				|| !strRole.equals(EducationConstant.ADMIN_USER_ROLE))
			return false;
		else
			return true;
	}

	private String writeStreamToFile(ContentUploadTO contentUploadTO,
			HttpServletRequest request) {

		String fileName = contentUploadTO.getContentFileName();
		String userDiretory = request.getSession().getServletContext()
				.getRealPath("/");
		userDiretory.replace("\\", "/");
		// String projectRoot=getServlet().getServletContext().getContextPath();
		String dirName = userDiretory
				+ fileName.substring(0, fileName.indexOf("."));
		File f = new File(dirName);
		// File f = new File("/"+dirName);
		f.mkdir();

		File mmFile = new File(f.getAbsolutePath() + "/" + fileName);
		writeToFile(contentUploadTO.getContentFileStream(), mmFile);

		if (contentUploadTO.getHasSupportingFiles() == 1) {
			File supportingFile = new File(f.getAbsolutePath() + "/"
					+ contentUploadTO.getSupportingtFileName());
			writeToFile(contentUploadTO.getSupportingFileStream(),
					supportingFile);
			try {
				unzip(supportingFile.getAbsolutePath(), f.getAbsolutePath());

			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		request.setAttribute("mmfilepath", "/" + f.getName() + "/"
				+ mmFile.getName());
		String finalPath = "/" + f.getName() + "/" + mmFile.getName();
		return finalPath;
	}

	public void writeToFile(InputStream is, File file) {
		try {
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			int c;
			while ((c = is.read()) != -1) {
				out.writeByte(c);
			}
			is.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Error Writing/Reading Streams.");
		}
	}

	public void unzip(String zipFile, String destinationPath)
			throws ZipException, IOException {

		int BUFFER = 2048;
		File file = new File(zipFile);

		ZipFile zip = new ZipFile(file);
		// String newPath = zipFile.substring(0, zipFile.length() - 4);

		String newPath = destinationPath;
		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

			String currentEntry = entry.getName();

			File destFile = new File(newPath, currentEntry);
			destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();
			if (!entry.isDirectory()) {
				BufferedInputStream is = new BufferedInputStream(zip
						.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest = new BufferedOutputStream(fos,
						BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}
			if (currentEntry.endsWith(".zip")) {
				// found a zip file, try to open
				unzip(destFile.getAbsolutePath(), destinationPath);
			}
		}
	}
}
