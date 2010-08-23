package com.education.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.expframework.data.ExceptionDTO;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;
import org.springframework.context.ApplicationContext;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.formbeans.FindSchool;
import com.education.services.SchoolContentUploadService;
import com.education.transferobj.TSchool;
import com.education.util.SpringContextAware;
import com.education.util.Utilities;

public class FindSchoolAction extends Action {
	protected final String SESSION_FAILURE_ACTION = "/sessionFaliure.do";
	protected final String AUTHORIZATION_FAILURE_PAGE = "/pages/authorizationFaliure.jsp";
	protected final String USER_RUNTIME_EXCEPTION_PAGE = "/pages/UserRuntimeExceptionPage.jsp";
	protected final String ADMIN_RUNTIME_EXCEPTION_PAGE = "/pages/adminRuntimeExceptionPage.jsp";
	protected final int SEARCH_RESULT_PAGE_SIZE = Integer.valueOf(Utilities.getApplicationProperty("pagination"));
	protected static ThreadLocal expDisplayDetails = new ThreadLocal();
	
	protected ActionForward getAuthorization_Failure_ActionForward() {
		ActionForward fwd = new ActionForward();
		fwd.setName("AUTHORIZATION_FAILURE");
		fwd.setPath(AUTHORIZATION_FAILURE_PAGE);
		return fwd;
	}
		
	protected ActionForward getRuntimeException_ActionForward(HttpServletRequest request) {
		ActionForward fwd = new ActionForward();
		fwd.setPath(USER_RUNTIME_EXCEPTION_PAGE);
		return fwd;
	}
	
	
	protected UserSessionInfo getUserInfoObject_FromSession(HttpServletRequest request){
		return (UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info);
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ActionForward actionForward = null;
		String userLoginName = null;
		
		try
		{
			
			userLoginName = getUserInfoObject_FromSession(request) != null ? getUserInfoObject_FromSession(request).getUserloginName() : "";
			
			if (request.getMethod().equals("GET")) {
				actionForward = displayAction(mapping, form, request, response);
			} else if (request.getMethod().equals("POST")) {
				if ((request.getAttribute("action") != null && request.getAttribute("action").equals("display"))
					|| (request.getParameter("action") != null && request.getParameter("action").equals("display"))) {
					actionForward = displayAction(mapping, form, request, response);
				} else {
					actionForward = performAction(mapping, form, request, response);
				}
			} else {
				throw new IllegalStateException("The only two HTTP methods supported are GET and POST");
			}
		}catch(RuntimeException ex){
			ExceptionUtil.logException(this.getClass(), ex, userLoginName);
			expDisplayDetails.set(null); 
			String msg =ExceptionUtil.getDetailedMessage(ex);
			request.setAttribute("stackTrace",msg);
			request.setAttribute("errorId",Utilities.getApplicationProperty("expError.runtimeException") );			
			return getRuntimeException_ActionForward(request);
			
		}catch (BaseAppException Ex) {
			//Exception Handler For Application
            ExceptionDisplayDTO expDTO = (ExceptionDisplayDTO) expDisplayDetails.get();
		    IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		    ExceptionDTO exDto = expHandler.handleException(expDTO.getContext(),userLoginName, Ex);		    
		    String msg = ExceptionUtil.getDetailedMessage(Ex);			
		    
		    String className = Ex.getClass().getName();
			className =  className.substring(className.lastIndexOf('.')+1);			
			if(className.equals("BussMasterDataMissingException") ||
					className.equals("DBDataSourceException") ||
					className.equals("DBInvalidDBDriverException") ||
					className.equals("BussTestCannotGeneratedException")){
				request.setAttribute("stackTrace",msg);
			    request.setAttribute("errorId",Utilities.getApplicationProperty(exDto.getMessageCode()));
				return getRuntimeException_ActionForward(request);
			}else{
				request.setAttribute("action","display");
				ActionErrors errors = getActionErrorForAppException(Ex,exDto);
				saveErrors(request,errors);
				return mapping.findForward(expDTO.getActionForwardName());
			}
		} catch (Exception ex) {
		    ExceptionUtil.logException(this.getClass(), ex,userLoginName);
		} finally {
		    expDisplayDetails.set(null);
		}
		return actionForward;
		
	}
	
	
	private ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("displayPage","");
		expDisplayDetails.set(expDTO);
		
		//condition to not display search results by default
		/*if(isNavigationDone(request)){
			getPaginatedSearchResults(request,bean,null);
		}*/
		
		return mapping.findForward("displayPage");		
	}

	private ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		TSchool schoolParam = new TSchool();
		FindSchool bean = (FindSchool)form;
		
		try
		{
			ApplicationContext ctx = SpringContextAware.getApplicationContext();
			BeanUtils.copyProperties(schoolParam, bean);
			
			SchoolContentUploadService service = (SchoolContentUploadService)ctx.getBean("schoolContentSerivce");
			List<TSchool> schoolList = service.findAllSchools(schoolParam);
			
			if (schoolList == null || schoolList.size() == 0)
			{
				ActionMessages errors = getErrors(request);
				errors.add("ERROR_NO_RECORDS", new ActionMessage("error.no.content","No Records Found for your search"));
				return mapping.findForward("displayPage");
			}
			
			IExtendedPaginatedList pageList = new PaginatedListImpl();
			int pageSize = SEARCH_RESULT_PAGE_SIZE; // Rows per page
			pageList.setPageSize(5);
			
			pageList.setList(schoolList);
			bean.setPgSearchResults(pageList);
						
			request.setAttribute("SCHOOLRESULT", schoolList);
			request.setAttribute("SIZE", schoolList.size());
			return mapping.findForward("success");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
	protected ActionErrors getActionErrorForAppException(BaseAppException ex,ExceptionDTO exDto){
		
		String className = ex.getClass().getName();
		ActionErrors errors = new ActionErrors();
		String columnName = null;
		String columnValue = null;
		className =  className.substring(className.lastIndexOf('.')+1);
		String errorId = "ErroID:"+Utilities.getApplicationProperty(exDto.getMessageCode());
		if(className.equals("DBInvalidDataInsertionException")){
			columnName = ((DBInvalidDataInsertionException)ex).getColumnName();
			columnValue = ((DBInvalidDataInsertionException)ex).getColumnValue();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode(),
					new String[]{errorId,columnValue,columnName}));
		}else if(className.equals("DBDataOutOfRangeException")){
			columnName = ((DBDataOutOfRangeException)ex).getColumnName();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode(),
					new String[]{errorId,columnName}));
		} else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode()));
		}	    
//	    ActionErrors errors = new ActionErrors();
//	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode()));
//	    saveErrors(request, errors);
	    return errors;
	}


}
