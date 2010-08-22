package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.util.Utilities;

public class AdminQuestionListTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getQuestionId() {
		
		m_ids = m_ids.equals("") ? String.valueOf( ((QuestionBankTO)getCurrentRowObject()).getQuestionId() ) :
			 m_ids+","+((QuestionBankTO)getCurrentRowObject()).getQuestionId();
		
		if(isLastRow()){
			return "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' />" +
				   "<input type='checkbox' id='"+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+
				   "'  value='"+
				   ((QuestionBankTO)getCurrentRowObject()).getQuestionId() +"' name='checkbox_id' />";
		}else{		
			return "<input type='checkbox' id='"+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId() +
			"' value='"+
        	((QuestionBankTO)getCurrentRowObject()).getQuestionId() +"' name='checkbox_id' />";
		}
        
    }
	
	public String getQuestion() {
		String domain_name = (String)getPageContext().getAttribute("DOMAIN_NAME",PageContext.APPLICATION_SCOPE);
		String question_Desc = Utilities.isNullOrBlank(((QuestionBankTO)getCurrentRowObject()).getQuestion()) ?
								"Picture Question" : ((QuestionBankTO)getCurrentRowObject()).getQuestion();
			
			
		String link = "<a href='"+ domain_name +"manageQuestion.do?cachedpage_for="+
				SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST+
				"&mode=edit"+
				"&id="+ ((QuestionBankTO)getCurrentRowObject()).getQuestionId()+"'>"+
				question_Desc+
				"</a>";
        return link;
    }
	
	 public String getQuestionStatusId() {
	        return Utilities.getQuestionStatusByID( ((QuestionBankTO)getCurrentRowObject()).getQuestionStatusId());
	}
	
	public String getCreatedOn() {
		return ((QuestionBankTO)getCurrentRowObject()).getCreatedOn();
	}
	
	
}
