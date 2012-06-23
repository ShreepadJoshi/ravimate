package com;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.TransactionHistoryBean;
import bean.TransactionHistoryBeanLocal;

import com.pojo.Account;
import com.pojo.TransactionHistory;

/**
 * Session Bean implementation class Payment
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class PaymentBean implements PaymentRemote {

	@PersistenceContext(unitName = "netjpa")
	EntityManager entityManager;
	
	@Resource
	private SessionContext sessionContext;
	
	@EJB(beanInterface = TransactionHistoryBeanLocal.class, mappedName = "TransactionHistoryBean/local")
	private TransactionHistoryBeanLocal transactionHistoryBeanLocal; 

	@Override
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void transferMoneyWithECS() {

		// createData();		
		
		remove4mPrafullsAccount();
		transactionHistoryBeanLocal.addEntryToHistoryTable();		
		add2ShreepadAccout();

	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	private void remove4mPrafullsAccount() {
		Account account = new Account();

		Query query = entityManager
				.createQuery("SELECT a FROM " + Account.class.getName()
						+ " a WHERE a.firstName = 'Prafulla'");
		account = (Account) query.getSingleResult();

		System.out.println(account);
		double amount = account.getAmount();
		account.setAmount(amount - 1000);
		entityManager.merge(account);

	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	private void add2ShreepadAccout() {
		Account account = new Account();
		Query query = entityManager.createQuery("SELECT a FROM "
				+ Account.class.getName() + " a WHERE a.firstName = 'Shripad'");
		account = (Account) query.getSingleResult();
		System.out.println(account);
		double amount = account.getAmount();
		account.setAmount(amount + 1000);
		entityManager.merge(account);
		try{
			throw new java.lang.RuntimeException();	
		}catch(RuntimeException exception){
			sessionContext.setRollbackOnly();
		}
		
	}



	private void createData() {
		Account prafulla = new Account();
		// prafulla.setAccountNumber(11011);
		prafulla.setFirstName("Prafulla");
		prafulla.setLastName("Joshi");
		prafulla.setAmount(50000);
		entityManager.persist(prafulla);

		Account shripad = new Account();
		// shripad.setAccountNumber(11011);
		shripad.setFirstName("shripad");
		shripad.setLastName("Joshi");
		shripad.setAmount(1000);
		entityManager.persist(shripad);
	}

}
