package com.education.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.dao.OracleEZDAO;
import com.education.formbeans.DropDownValueByAjaxActionForm;
import com.education.services.EZBusinessServices;
import com.education.transferobj.TopicSubTopicTO;

public class PopulateDropDownByAjaxAction extends EducationBaseAction{

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
		
		DropDownValueByAjaxActionForm dropDownBean = (DropDownValueByAjaxActionForm)form;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		EZBusinessServices service = new EZBusinessServices();
		ArrayList list = new ArrayList();
		String selectTag ="";
		String options = "<option value=''>Please Specify</option>";
		
		//According pickDropDownvalueFor value prepare new drop down and return
		
		if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("topiclist")){
			//get topic list
			 list = service.getTopicListBYSubjectId(dropDownBean.getClassId(),dropDownBean.getSubjectId());
			 //selectTag = "<select class='text' name='"+ dropDownBean.getElementName() +"' id='"+ dropDownBean.getElementName() +"'" +
			 //" onchange=\"populateSubTopics(document.getElementById('subject').value,this.value,'ManageQsubTopic_divID')\">";
			
		}else if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("subtopiclist")){
			//get subtopic list
			list = service.getSubTopicListBYSubjectId(dropDownBean.getSubjectId(), dropDownBean.getTopicValue());
			//selectTag = "<select class='text' name='"+ dropDownBean.getElementName() +"' id='"+ dropDownBean.getElementName() +"'>";
						
		}else if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("subjectlist")){
			//get subtopic list
			list = service.getSubjecListByClassId(dropDownBean.getClassId());
			//selectTag = "<select class='text' name='"+ dropDownBean.getElementName() +"' id='"+ dropDownBean.getElementName() +"'>";
						
		}		
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			TopicSubTopicTO objTo = (TopicSubTopicTO)itr.next();
			
			if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("subtopiclist")){
				options = options + "<option value='"+ objTo.getSubTopicId() +"'>"+ objTo.getSubTopicValue() +"</option>";
			}else if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("topiclist")){
				options = options + "<option value='"+ objTo.getTopicId() +"'>"+ objTo.getTopicvalue() +"</option>";
			}else if(dropDownBean.getPickDropDownvalueFor().equalsIgnoreCase("subjectlist")){
				options = options + "<option value='"+ objTo.getSubjectId() +"'>"+ objTo.getSubjectValue() +"</option>";
			}			
		}
		//selectTag = selectTag + options + "</select>";
		
		out.print(options);
		return null;
	}

}
