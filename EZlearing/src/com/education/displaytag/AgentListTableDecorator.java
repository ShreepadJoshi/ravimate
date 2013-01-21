package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.RegistrationTo;

public class AgentListTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getUserId() { 
		
		m_ids = m_ids.equals("") ? ((RegistrationTo)getCurrentRowObject()).getUserId()  :
			 m_ids+","+((RegistrationTo)getCurrentRowObject()).getUserId();
		String value = "";
		if(isLastRow()){
			value =  "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' />" +
				   "<input type='checkbox' id='"+ ((RegistrationTo)getCurrentRowObject()).getUserId()+
				   "'  value='"+
				   ((RegistrationTo)getCurrentRowObject()).getUserId() +"' name='checkbox_id' />";
		}else{		
			value = "<input type='checkbox' id='"+ ((RegistrationTo)getCurrentRowObject()).getUserId() +
			"' value='"+
			((RegistrationTo)getCurrentRowObject()).getUserId() +"' name='checkbox_id' />";
		}
		return value;
    }
	
	public String getFirstName() {
		String domain_name = (String)getPageContext().getAttribute("DOMAIN_NAME",PageContext.APPLICATION_SCOPE);
		String roleType = (String)getPageContext().getAttribute("roleType");
		return "<a href='"+ domain_name +"agentFullRegistration.do?mode=view&id="+
		((RegistrationTo)getCurrentRowObject()).getUserId()
		+"&roleType="+ roleType +"'>"+
		((RegistrationTo)getCurrentRowObject()).getFirstName()+
		" "+((RegistrationTo)getCurrentRowObject()).getLastName()+"</a>";
	}
		
}
