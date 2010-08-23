package com.education.displaytag;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

import com.education.util.EducationConstant;

public class RegistrationStatusColumnDecorator implements ColumnDecorator{

	public String decorate(Object approvalType) throws DecoratorException {
		String key = null;
		String strApprovalType = "";
		
		if(  approvalType instanceof Integer  ){
			key = ((Integer)approvalType).toString();
		}else if(  approvalType instanceof String ){
			key = approvalType.toString();
		}
		
		if( key.equals(EducationConstant.REG_STATUS_APPROVED)){
			strApprovalType = "Approved";
		}else if( key.equals(EducationConstant.REG_STATUS_NOT_APPROVED)){
			strApprovalType = "NotApproved";		
		}else if( key.equals(EducationConstant.REG_STATUS_DISABLED)){
			strApprovalType = "Disbaled";		
		}
		return strApprovalType;
	}
}
