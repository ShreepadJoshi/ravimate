package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class ReviewerQuestionListTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getQuestionId() {
		
		//check question status for Approved Question - disable checkbox		
		String checkbox = "";	
		
		m_ids = m_ids.equals("") ? String.valueOf( ((QuestionBankTO)getCurrentRowObject()).getQuestionId() ) :
				m_ids+","+((QuestionBankTO)getCurrentRowObject()).getQuestionId();
		checkbox = "<input type='checkbox' id='"+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+
			   	   "' value='"+
			       ((QuestionBankTO)getCurrentRowObject()).getQuestionId() +"' name='checkbox_id' />";
				
		if(isLastRow()){
			return "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' /> " +checkbox;
		}else{		
			return checkbox;
		}        
    }
	
	public String getQuestion() {
		String domain_name = (String)getPageContext().getAttribute("DOMAIN_NAME",PageContext.APPLICATION_SCOPE);
		String link = "<a href='"+ domain_name +"manageQuestion.do?cachedpage_for="+
				SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST+
				"&mode=view"+
				"&id="+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+"'>"+
				((QuestionBankTO)getCurrentRowObject()).getQuestion()+
				"</a>";
        return link;
    }
	
	public String getQuestionStatusId() {
        return Utilities.getQuestionStatusByID( ((QuestionBankTO)getCurrentRowObject()).getQuestionStatusId()  );
	}
	
	public String getCreatedOn() {
		return ((QuestionBankTO)getCurrentRowObject()).getCreatedOn();
	}
	
	
}
