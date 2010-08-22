package com.education.services;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.AgentRegistrationDAO;
import com.education.formbeans.AgentFullRegActionForm;
import com.education.transferobj.AgentFullRegTO;

public class AgentRegistrationService {

	public void registerAgent(AgentFullRegTO agentFullRegTO)
			throws BaseAppException {

		AgentRegistrationDAO agentRegDAO = new AgentRegistrationDAO();
		agentRegDAO.registerAgent(agentFullRegTO);

	}

	public AgentFullRegActionForm getAgentDetails(
			AgentFullRegActionForm agentRegistrationBean) throws BaseAppException {
		return new AgentRegistrationDAO().getAgentDetails(agentRegistrationBean);
	}

	public int modifyAgent(AgentFullRegTO agentFullRegTO) throws BaseAppException {
		return new AgentRegistrationDAO().modifyAgent(agentFullRegTO);
	}
}
