package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPACreate {

	public int createPerson() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("netjpa");
		EntityManager em = emf.createEntityManager();
		Person person = new Person();
		try {
			EntityTransaction txn = em.getTransaction();
			txn.begin();
			person.setFirstName("Shripad");
			person.setLastName("Joshi");
			em.persist(person);
			txn.commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
		return person.getId();
	}

	public void updatePerson(Person person) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("netjpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction txn = em.getTransaction();
			txn.begin();
			person.setFirstName("Prafulla");
			person.setLastName("Joshi (is same)");
			em.merge(person);
			txn.commit();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}

	}

	public Person readPerson4mDB(int id) {
		Person person = new Person();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("netjpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction txn = em.getTransaction();
			txn.begin();

			person = em.find(Person.class, id);

			txn.commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
		return person;
	}

	public void deletePerson(Person person) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("netjpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction txn = em.getTransaction();
			txn.begin();
			person = em.find(Person.class, person.getId());
			// em.merge(person);
			em.remove(person);
			txn.commit();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
	}
}
