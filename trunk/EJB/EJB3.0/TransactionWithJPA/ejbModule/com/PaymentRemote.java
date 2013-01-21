package com;

import javax.ejb.Remote;
import javax.xml.ws.RequestWrapper;

@Remote
public interface PaymentRemote {
	
	@RequestWrapper
	public void transferMoneyWithECS();

}
