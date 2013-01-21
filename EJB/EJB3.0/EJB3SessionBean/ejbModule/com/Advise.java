package com;

import interceptors.ShreeInterceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.interceptor.Interceptors;

/**
 * Session Bean implementation class Advise
 */

@Remote(AdviseRemote.class)
@Stateless
@Interceptors({ ShreeInterceptor.class })
public class Advise implements TimedObject {

	// @PersistenceContext EntityManager em;

	/**
	 * Default constructor.
	 */
	public Advise() {
		// TODO Auto-generated constructor stub
	}

	@Interceptors({ ShreeInterceptor.class })
	public String getAdvise() {
		// saveBook();
		return "This is Advise Stateless EJB";
	}

	@Override
	public void ejbTimeout(Timer arg0) {
		System.out.println("Timer Working");

	}

}
