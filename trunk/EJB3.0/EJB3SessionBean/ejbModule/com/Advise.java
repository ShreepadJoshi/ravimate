package com;

import interceptors.ShreeInterceptor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pojo.Book;

/**
 * Session Bean implementation class Advise
 */

@Remote(AdviseRemote.class)
@Stateless
@Interceptors( { ShreeInterceptor.class })

public class Advise implements TimedObject{

	
	
	@PersistenceContext	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public Advise() {
        // TODO Auto-generated constructor stub
    }
    
    @Interceptors( { ShreeInterceptor.class })
    public String getAdvise(){
    	saveBook();
    	return "Inserted data in table";
    }
    
    private boolean saveBook() {
    	boolean flag = false;
    	Book book = new Book();
    	book.setId(1);
    	book.setBookname("EJB 3.0");
    	em.persist(book);
    	return flag;
    }

	@Override
	public void ejbTimeout(Timer arg0) {
		System.out.println("Timer Working");
		
	}
    

}
