package org.expframework.samples.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.actions.DispatchAction;
import org.expframework.data.ExceptionDTO;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;

//public abstract class BaseAppDispatchAction extends DispatchAction {
public class BaseAppDispatchAction  {

//    protected static ThreadLocal expDisplayDetails = new ThreadLocal();
//
//    public ActionForward execute(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        HttpSession session = request.getSession(false);
//        String userId = (String) session.getAttribute("userId");
//        try {
//            
//        	String actionMethod = request.getParameter(mapping.getParameter());
//            return dispatchMethod(mapping, form, request, response,
//                    actionMethod);
//            
//        } catch (BaseAppException Ex) {
//            ExceptionDisplayDTO expDTO = (ExceptionDisplayDTO) expDisplayDetails
//                    .get();
//            IExceptionHandler expHandler = ExceptionHandlerFactory
//                    .getInstance().create();
//            ExceptionDTO exDto = expHandler.handleException(
//                    expDTO.getContext(), userId, Ex);
//            ActionErrors errors = new ActionErrors();
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(exDto
//                    .getMessageCode()));
//            saveErrors(request, errors);
//            return mapping.findForward(expDTO.getActionForwardName());
//        } catch (Exception ex) {
//            ExceptionUtil.logException(this.getClass(), ex, userId);
//            throw ex;
//        } finally {
//            expDisplayDetails.set(null);
//        }
//    }
}