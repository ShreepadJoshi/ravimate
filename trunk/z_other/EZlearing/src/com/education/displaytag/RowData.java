package com.education.displaytag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.education.util.EducationConstant;
import com.education.util.Utilities;
import com.education.util.Utilities.Option;

public class RowData implements Serializable {

	
	  public String cert ="<select name='classList' size='1'>"+ 
	  			"<option label='Engineering' value='Eng'/>"+
	  			"<option label='B.S.C' value='Bsc'/>"+
	  			"</select>";

	  public String complexity ="<select name='complexityList' size='1'>"+
	  							"<option label='Simple' value='simple'/>"+
	  							"<option label='Medium' value='medium'/>"+
	  							"<option label='Complex' value='complex'/>"+
	  							"<option label='Very Complex' value='verycomplex'/>"+
	  							"</select>";

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	 

	/*public String getCert() {
		String options = "";
		Iterator itr = new Utilities().getDropdownValue(
				EducationConstant.CLASS_DROPDOWN_VALUE).iterator();
		while (itr.hasNext()) {
			Option objOpt = (Utilities.Option) itr.next();
			options += "<option label='" + objOpt.getLabel() + "' value='"
					+ objOpt.getValue() + "'/>";
		}
		return "<select>" + options + "</select>";
	}

	public String getComplexity() {
		String options = "";
		Iterator itr = new Utilities().getDropdownValue(
				EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE)
				.iterator();
		while (itr.hasNext()) {
			Option objOpt = (Utilities.Option) itr.next();
			options += "<option label='" + objOpt.getLabel() + "' value='"
					+ objOpt.getValue() + "'/>";
		}
		return "<select>" + options + "</select>";
	} */
}