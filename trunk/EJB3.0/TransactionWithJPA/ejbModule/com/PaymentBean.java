package com;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Payment
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class PaymentBean implements PaymentRemote {

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("netjpa");
	EntityManager em = emf.createEntityManager();
	
	@Override
	public void transferMoneyWithECS() {
		
		remove4mPrafullsAccount();
		add2ShreepadAccout();
		addEntryToHistoryTable();
	}

	@TransactionAttribute(value=TransactionAttributeType.REQUIRED)
	private void addEntryToHistoryTable() {
		System.out.println("Removing..." + em);
		
	}

	@TransactionAttribute(value=TransactionAttributeType.REQUIRED)
	private void add2ShreepadAccout() {
		System.out.println("Removing...");
		
	}

	@TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
	private void remove4mPrafullsAccount() {
		System.out.println("Adding in log...");
		
	}

}
