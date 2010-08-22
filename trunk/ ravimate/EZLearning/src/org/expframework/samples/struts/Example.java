package org.expframework.samples.struts;

import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;
import org.expframework.samples.NoAreasFoundException;

public class Example extends BaseApp{
	
	@Override
	protected int getInt() throws BaseAppException {
		
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("SearchStore",
					"storeAction.searchStore");
		expDisplayDetails.set(expDTO);		
		return 1;				
	}
	
	public static void main(String[] args) throws Exception {
		new Example().disp();
	}	
			
}
