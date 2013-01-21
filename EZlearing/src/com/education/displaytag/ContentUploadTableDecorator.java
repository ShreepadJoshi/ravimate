package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.ContentUploadTO;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.Utilities;

public class ContentUploadTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getContentId() {
		
		
		m_ids = m_ids.equals("") ? String.valueOf(((ContentUploadTO)getCurrentRowObject()).getContentId())
								 : m_ids+","+((ContentUploadTO)getCurrentRowObject()).getContentId();
		
		String hiddenTextBoxSubjectTopicId =" <input type='hidden' name='SubjectTopicIdList' value='"+
											((ContentUploadTO)getCurrentRowObject()).getContentId()+"'>";
		
		if(isLastRow()){
			return "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' />" +
				   "<input type='radio' id='"+ ((ContentUploadTO)getCurrentRowObject()).getContentId() +
				   "'  value='"+
				   ((ContentUploadTO)getCurrentRowObject()).getContentId() +"' name='checkbox_id' />"+hiddenTextBoxSubjectTopicId;
		}else{		
			return "<input type='radio' id='"+ ((ContentUploadTO)getCurrentRowObject()).getContentId() +
			"' value='"+
			((ContentUploadTO)getCurrentRowObject()).getContentId() +"' name='checkbox_id' />"+hiddenTextBoxSubjectTopicId;
		}
    }
	
//	public String getSupportingtFileName() {
//		String readOnlyTextBox = "";
//		String suppFileName = ((ContentUploadTO)getCurrentRowObject()).getSupportingtFileName();
//		int contentId = Integer.parseInt( ((ContentUploadTO)getCurrentRowObject()).getContentId() );
//		String label = "";
//		String fileInput = "<input type='file' name='suppFileList'/>";
//		
//		if(Utilities.isNullOrBlank(suppFileName)){
//			//readOnlyTextBox = "<input type='text' value='"+ "N/A" +"' disabled='disabled'/>";
//			readOnlyTextBox = "N/A";
//		}else{
//			readOnlyTextBox = suppFileName;
//		}		
//		label = ( contentId > 0 ? "Replace" : "Add");
//		return readOnlyTextBox+"<br/>"+label+fileInput;
//	}
//	
//	public String getContentFileName() {
//		int contentId = Integer.parseInt( ((ContentUploadTO)getCurrentRowObject()).getContentId() );
//		String contentFileName = ((ContentUploadTO)getCurrentRowObject()).getContentFileName();
//		String fileInput = "<input type='file' name='contentFileList'/>";
//		String label = "";
//		label = ( contentId > 0 ? "Replace" : "Add");		
//		return contentFileName+"<br/>"+label+fileInput;		
//	}
}




