package com.education.actions;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.services.QuestionBankService;

public class ImageRenderAction extends EducationBaseAction {

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
		
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		int imageFor = Integer.parseInt(request.getParameter("imagefor")); 
		QuestionBankService service = new QuestionBankService();
		
		response.setContentType("image/jpeg");
		ServletOutputStream os = response.getOutputStream();		
		InputStream in = service.getPictureQuestion_ImagesByID(questionId, imageFor);
		
		byte[] bytes = new byte[1024];
		int readByte = 0;
		while( (readByte=in.read(bytes))!= -1 ){
			os.write(bytes,0,readByte);
		}
		os.flush();
		os.close(); 			
		return null;
	}
	
	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}
}
