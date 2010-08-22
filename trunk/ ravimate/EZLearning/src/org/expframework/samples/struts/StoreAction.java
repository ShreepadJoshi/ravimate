package org.expframework.samples.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;

public class StoreAction extends BaseAppDispatchAction {
   
//	public ActionForward execute(ActionMapping actionMapping,
//            ActionForm actionForm, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        return super.execute(actionMapping, actionForm, request, response);
//    }
//
    public ActionForward searchStore(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //Create an instance of ExceptionDisplayDTO with ActionMapping name
        // which will be
        //used to forward the page for application exception. Another param is
        // the context for context
        //sensitive exceptions.
        ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("SearchStore",
                "storeAction.searchStore");
        //expDisplayDetails.set(expDTO);

        //perform business processing
        //...
        //...

        //Forward to a page for successful conditions
        return actionMapping.findForward("SearchStore");

    }
}