package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class TeacherQuestionListTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getQuestionId() {
		
		//check question status for Approved Question - disable checkbox
		int nQuestionStatusId = ((QuestionBankTO)getCurrentRowObject()).getQuestionStatusId();
		boolean isdisable = false;
		String checkbox = ""; 
		
		if(nQuestionStatusId == EducationConstant.QUESTION_STATUS_APPROVED||
				nQuestionStatusId ==EducationConstant.QUESTION_STATUS_INACTIVE){
			isdisable = true;
		}
		
		if(!isdisable){
			m_ids = m_ids.equals("") ? String.valueOf( ((QuestionBankTO)getCurrentRowObject()).getQuestionId() ) :
					m_ids+","+((QuestionBankTO)getCurrentRowObject()).getQuestionId();
			checkbox = "<input type='checkbox' id='"+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+
			   		   "' value='"+
			           ((QuestionBankTO)getCurrentRowObject()).getQuestionId() +"' name='checkbox_id' />";
		}else{
			checkbox = "<input type='checkbox' id='"+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+
	   		           "' value='"+
	                   ((QuestionBankTO)getCurrentRowObject()).getQuestionId() +"' name='checkbox_id' disabled='true'  />";
		}
		
		if(isLastRow()){
			return "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' /> " +checkbox;
		}else{		
			return checkbox;
		}        
    }
	
	public String getQuestion() {
		String domain_name = (String)getPageContext().getAttribute("DOMAIN_NAME",PageContext.APPLICATION_SCOPE);
		int nQuestionStatusId = ((QuestionBankTO)getCurrentRowObject()).getQuestionStatusId();
		boolean isdisable = false;
		String link = "";
		String question_Desc = Utilities.isNullOrBlank(((QuestionBankTO)getCurrentRowObject()).getQuestion()) ?
				"Picture Question" : ((QuestionBankTO)getCurrentRowObject()).getQuestion();
		
		if(nQuestionStatusId == EducationConstant.QUESTION_STATUS_APPROVED||
				nQuestionStatusId == EducationConstant.QUESTION_STATUS_INACTIVE){
			isdisable = true;
		}
		if(isdisable){
			link= "<a>"+
			((QuestionBankTO)getCurrentRowObject()).getQuestion()+
			"</a>";
		}else{
			link= "<a href='"+ domain_name +"manageQuestion.do?cachedpage_for="+
			SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST+
			"&mode=edit"+
			"&id="+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+"'>"+
			question_Desc+
			"</a>";
		}		 
        return link;
    }
	
	 public String getQuestionStatusId() {
	        return Utilities.getQuestionStatusByID( ((QuestionBankTO)getCurrentRowObject()).getQuestionStatusId());
	}
	
//	public String getCreatedOn() {
//		return ((QuestionBankTO)getCurrentRowObject()).getCreatedOn();
//	}
	
	
}
