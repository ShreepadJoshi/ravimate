package com.education.displaytag;

import org.displaytag.decorator.TableDecorator;

import com.education.transferobj.AffiliateTO;

public class ManageAffiliateTableDecorator extends TableDecorator {
	String m_ids = "";
	public String getSubjectTopicId(){
		
		m_ids = m_ids.equals("") ? String.valueOf(((AffiliateTO)getCurrentRowObject()).getAffiliateId())
								 : m_ids+","+((AffiliateTO)getCurrentRowObject()).getAffiliateId();
		
		String hiddenTextBoxSubjectTopicId =" <input type='hidden' name='AffiliateIdList' value='"+
											((AffiliateTO)getCurrentRowObject()).getAffiliateId()+"'>";
		
		String checkedPorp = "";
		
		checkedPorp =(((AffiliateTO)getCurrentRowObject()).getAffiliateId() != null ?
						" checked='checked' " : "" );
		String onclickHandler = " onclick=\"toggle_ActionBtn('hiddenIdList','actionBtnList') \"";
		
		String checkbox = " <input type='checkbox' "+
						    checkedPorp+
						    onclickHandler+
						  " id='"+((AffiliateTO)getCurrentRowObject()).getAffiliateId()+"' "+
						  " value='"+((AffiliateTO)getCurrentRowObject()).getAffiliateId() +"' "+
						  " name='checkbox_id' />";
		
		String hiddenIdList = "<input type='hidden' value='"+ m_ids +"' id='hiddenIdList' />"; 
		
		if(isLastRow()){
			return checkbox + hiddenIdList+hiddenTextBoxSubjectTopicId;
		}else{		
			return checkbox+hiddenTextBoxSubjectTopicId;
		}
    }
}
