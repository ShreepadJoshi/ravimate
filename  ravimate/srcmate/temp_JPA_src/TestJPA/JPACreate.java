package TestJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPACreate {

	
	public void createPerson() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("netjpa");
		EntityManager em=emf.createEntityManager();
		try{
		EntityTransaction txn=em.getTransaction();
		txn.begin();
		Person person = new Person();
		person.setFirstName("First Name");
		person.setLastName("LAST Name");
		em.persist(person);
		txn.commit();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		}
}
