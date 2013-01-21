/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.actions;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleContentUploadDAO;

/**
 * 
 * @author Administrator
 */
public class DownloadFileAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			ServletOutputStream out = response.getOutputStream();
			OracleContentUploadDAO contentUploadDAO = new OracleContentUploadDAO();
			HSSFWorkbook workbook = contentUploadDAO.downloadFile();
			workbook.write(out);
		} catch (BaseAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	/*
	 * * Return true for user as Teacher
	 */
	/*
	 * protected boolean hasValidPermission(HttpServletRequest request) { String
	 * strRole = getUserRoleFromSession(request); if (strRole == null ||
	 * !strRole.equals(EducationConstant.TEACHER_USER_ROLE)) return false; else
	 * return true; }
	 */

}
