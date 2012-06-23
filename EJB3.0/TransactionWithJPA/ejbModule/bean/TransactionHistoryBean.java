package bean;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pojo.TransactionHistory;

/**
 * Session Bean implementation class TransactionHistoryBean
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TransactionHistoryBean implements TransactionHistoryBeanLocal {
	
	@PersistenceContext(unitName = "netjpa")
	EntityManager entityManager;
	
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public void addEntryToHistoryTable() {
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setTime("ECS transaction " + new Date());
		entityManager.persist(transactionHistory);
	}

    /**
     * Default constructor. 
     */
    public TransactionHistoryBean() {
        // TODO Auto-generated constructor stub
    }

}
