package com;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.EJBMetaData;
import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.ejb.RemoveException;

public class AdviseHome implements EJBHome{

	@Override
	public EJBMetaData getEJBMetaData() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HomeHandle getHomeHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Handle arg0) throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object arg0) throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}



}
