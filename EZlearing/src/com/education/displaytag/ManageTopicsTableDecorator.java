package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.Utilities;

public class ManageTopicsTableDecorator extends TableDecorator{

	String m_ids = "";
	public String getSubjectTopicId(){
		
		m_ids = m_ids.equals("") ? String.valueOf(((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId())
								 : m_ids+","+((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId();
		
		String hiddenTextBoxSubjectTopicId =" <input type='hidden' name='SubjectTopicIdList' value='"+
											((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId()+"'>";
		
		String checkedPorp = "";
		
		checkedPorp =(((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId()< 0 ?
						" checked='checked' " : "" );
		String onclickHandler = " onclick=\"toggle_ActionBtn('hiddenIdList','actionBtnList') \"";
		
		String checkbox = " <input type='checkbox' "+
						    checkedPorp+
						    onclickHandler+
						  " id='"+((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId()+"' "+
						  " value='"+((TopicSubTopicTO)getCurrentRowObject()).getSubjectTopicId() +"' "+
						  " name='checkbox_id' />";
		
		String hiddenIdList = "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' />"; 
		
		if(isLastRow()){
			return checkbox + hiddenIdList+hiddenTextBoxSubjectTopicId;
		}else{		
			return checkbox+hiddenTextBoxSubjectTopicId;
		}
    }	
}
