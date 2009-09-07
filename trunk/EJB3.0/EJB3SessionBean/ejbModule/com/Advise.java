package com;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pojo.Book;

/**
 * Session Bean implementation class Advise
 */
@Stateless
public class Advise implements AdviseRemote {

	
	@PersistenceContext	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public Advise() {
        // TODO Auto-generated constructor stub
    }
    
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
    

}
