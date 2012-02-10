package com.cloud.nvl.core.jpa.test.unit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.cloud.nvl.core.jpa.Account;
import com.cloud.nvl.core.jpa.Customer;
import com.cloud.nvl.core.jpa.test.JpaTest;

public class AccountTest extends JpaTest {

	public static final String TEST_NAME = "AccountTest";
	public static final String PERSISTENCE_UNIT_UNDER_TEST = "Accounts";

	protected static EntityManagerFactory emf;

	/* === Test Methods === */

	@BeforeClass
	public static void classSetUp() {
		slog(TEST_NAME, "In setup");
		emf = lookupEntityManagerFactory(TEST_NAME, PERSISTENCE_UNIT_UNDER_TEST);
		slog(TEST_NAME, "Got EMF - " + emf);
	}

	@AfterClass
	public static void classCleanUp() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

	/* === Subclassed methods === */

	public boolean needsDsfService() {
		return false;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public String getTestPersistenceUnitName() {
		return PERSISTENCE_UNIT_UNDER_TEST;
	}

	public Object newObject() {
		EntityManager em = (EntityManager) getEmf();
		em.getTransaction().begin();

		Customer c = new Customer("Chan", "Jackie",
				"1034 KingFu Lane, Los Angeles, CA");
		em.persist(c);
		Account a = new Account(c);
		a.setBalance(100.0);
		em.persist(a);

		em.getTransaction().commit();

		TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a",
				Account.class);
		List<Account> results = q.getResultList();
		System.out.println("\n*** Account Report ***");
		for (Account acct : results) {
			System.out.println("Account: " + acct);
		}
		em.close();
		return em;
	}

	public Object queryObjects() {
		EntityManager em = (EntityManager) getEmf();
		TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a",
				Account.class);
		List<Account> results = q.getResultList();
		System.out.println("\n*** Account Report ***");
		for (Account acct : results) {
			System.out.println("Account: " + acct);
		}
		em.close();
		return results;
	}
}
