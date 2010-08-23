package com.education.tiles.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.ControllerSupport;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.util.EducationConstant;

/*public class UserHomePageRender extends TilesAction{

 @Override
 public ActionForward execute(ComponentContext context,
 ActionMapping mapping, ActionForm form, HttpServletRequest request,
 HttpServletResponse response) throws Exception {	

 //return Actionforward
 return mapping.findForward("indexPage");

 }
 }*/

public class UserHomePageRender extends ControllerSupport {

	@Override
	public void execute(ComponentContext tileContext,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws Exception {

		String strRole = "999"; // default dummy value
		HttpSession session = request.getSession();
		UserSessionInfo objUserinfo = (UserSessionInfo) (session
				.getAttribute(SessionConstants.user_info) == null ? null
				: session.getAttribute(SessionConstants.user_info));

		if (objUserinfo == null) {
			return;
		} else {
			strRole = objUserinfo.getRoleId();

			// display bodyPart only for loggedIn user not for other user
			if (String.valueOf(strRole).equalsIgnoreCase(
					(String) tileContext.getAttribute("definition"))) {
				insertBody(String.valueOf(strRole), tileContext);
			}
		}

		// If loggedIn then display -
		// left,right nav panels and body content as per login role
		if (strRole.equals(EducationConstant.TEACHER_USER_ROLE)) {
			tileContext.putAttribute("leftNavigation",
					"tiles.leftNavigationPanel.teacher");
			tileContext.putAttribute("rightNavigation",
					"tiles.rightNavigationPanel.teacher");
			tileContext.putAttribute("leftMenu",
					"tiles.leftMenuNavigationPanel.teacher");
		} else if (strRole.equals(EducationConstant.AGENT_USER_ROLE)) {
			tileContext.putAttribute("leftNavigation",
					"tiles.leftNavigationPanel.agent");
			tileContext.putAttribute("rightNavigation",
					"tiles.rightNavigationPanel.agent");
			tileContext.putAttribute("leftMenu",
					"tiles.leftMenuNavigationPanel.agent");
		} else if (strRole.equals(EducationConstant.AFFILIATE_USER_ROLE)) {
			tileContext.putAttribute("leftNavigation",
					"tiles.leftNavigationPanel.teacher");
			tileContext.putAttribute("rightNavigation",
					"tiles.rightNavigationPanel.teacher");
			tileContext.putAttribute("leftMenu",
					"tiles.leftMenuNavigationPanel.affiliate");
		} else if (strRole.equals(EducationConstant.GUEST_USER_ROLE)) {
			tileContext.putAttribute("leftNavigation",
					"tiles.leftNavigationPanel.guest");
			tileContext.putAttribute("rightNavigation",
					"tiles.rightNavigationPanel.guest");
			tileContext.putAttribute("leftMenu",
					"tiles.leftMenuNavigationPanel.guest");
		} else if (strRole.equals(EducationConstant.REVIEWER_USER_ROLE)) {
			tileContext.putAttribute("leftNavigation",
					"tiles.leftNavigationPanel.reviewer");
			tileContext.putAttribute("rightNavigation",
					"tiles.rightNavigationPanel.reviewer");
			tileContext.putAttribute("leftMenu",
					"tiles.leftMenuNavigationPanel.reviewer");
		}
	}

	private void insertBody(String defnitionType, ComponentContext tileContext) {

		if (defnitionType == null) {
			return;
		}

		if (defnitionType.equals(EducationConstant.TEACHER_USER_ROLE)) {
			tileContext.putAttribute("bodyContent",
					"tiles.bodyPanel.teacher.login");
		} else if (defnitionType.equals(EducationConstant.AGENT_USER_ROLE)) {
			tileContext.putAttribute("bodyContent",
					"tiles.bodyPanel.agent.login");
		} else if (defnitionType.equals(EducationConstant.AFFILIATE_USER_ROLE)) {
			tileContext.putAttribute("bodyContent",
					"tiles.bodyPanel.affiliate.login");
		} else if (defnitionType.equals(EducationConstant.GUEST_USER_ROLE)) {
			tileContext.putAttribute("bodyContent",
					"tiles.bodyPanel.guest.login");
		} else if (defnitionType.equals(EducationConstant.REVIEWER_USER_ROLE)) {
			tileContext.putAttribute("bodyContent",
					"tiles.bodyPanel.reviewer.login");
		}
	}
}
