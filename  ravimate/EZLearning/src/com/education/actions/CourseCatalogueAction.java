package com.education.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.CourseCatalogueForm;
import com.education.services.CourseCatalogueServie;
import com.education.transferobj.CourseCatalogueTO;

public class CourseCatalogueAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionFwdString = "displayCatlog";
		CourseCatalogueForm courseCatform = (CourseCatalogueForm) form;
		CourseCatalogueServie courseCatalogueServie = new CourseCatalogueServie();
		List<CourseCatalogueTO> toList = courseCatalogueServie.getDefaultValues();
		request.getSession().setAttribute("CourseCatalogueTO", toList);
		courseCatform.setCourseCatalogueTOList(toList);
		return mapping.findForward(actionFwdString);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionFwdString = "displayCatlog";
		String[] strIds = getCheckedIds(request);
		CourseCatalogueForm courseCatform = (CourseCatalogueForm) form;
		UserSessionInfo objUserInfo =  (UserSessionInfo)request.getSession().getAttribute(SessionConstants.user_info);
		List<CourseCatalogueTO> toList = (List<CourseCatalogueTO>) request.getSession().getAttribute("CourseCatalogueTO");
		List<CourseCatalogueTO> selectedToList = new ArrayList(); 
		request.getSession().removeAttribute("CourseCatalogueTO");
		if(strIds !=null && toList!= null){
			for(int i=0; i<strIds.length;i++){
				CourseCatalogueTO to = toList.get(Integer.parseInt(strIds[i]));
				to.setUserId(objUserInfo.getUserId());
				selectedToList.add(to);
			}
		}
		CourseCatalogueServie courseCatalogueServie = new CourseCatalogueServie();
		courseCatalogueServie.addCourseCatalogue(selectedToList);
		dispMessage(request,"",new ActionMessage("message.saved",
				new String[]{"","Saved...."}));
		request.getSession().setAttribute("CourseCatalogueTO", toList);
		courseCatform.setCourseCatalogueTOList(toList);
		return mapping.findForward(actionFwdString);
	}

	
}
