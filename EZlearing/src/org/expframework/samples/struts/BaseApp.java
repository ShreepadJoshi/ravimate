package org.expframework.samples.struts;

import org.apache.struts.action.ActionErrors;
import org.expframework.data.ExceptionDTO;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;

public class BaseApp {

	protected static ThreadLocal expDisplayDetails = new ThreadLocal();
	
	protected void disp() throws Exception{
		
		try {
            
			getInt();
            
        } catch (BaseAppException Ex) {
            ExceptionDisplayDTO expDTO = (ExceptionDisplayDTO) expDisplayDetails
                    .get();
            IExceptionHandler expHandler = ExceptionHandlerFactory
                    .getInstance().create();
            ExceptionDTO exDto = expHandler.handleException(
                    expDTO.getContext(), "userId", Ex);
            ActionErrors errors = new ActionErrors();
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(exDto
//                    .getMessageCode()));
//            saveErrors(request, errors);
//            return mapping.findForward(expDTO.getActionForwardName());
        } catch (Exception ex) {
            ExceptionUtil.logException(this.getClass(), ex, "userId");
            throw ex;
        } finally {
            expDisplayDetails.set(null);
        }
	}
	
	protected int getInt()throws BaseAppException{
		return 0;
	}
}
