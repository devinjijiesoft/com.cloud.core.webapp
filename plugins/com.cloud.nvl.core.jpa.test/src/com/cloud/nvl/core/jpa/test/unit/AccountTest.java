package com.cloud.nvl.core.jpa.test.unit;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;
import org.junit.Before;
import org.junit.Test;
import com.cloud.nvl.core.jpa.Account;
import com.cloud.nvl.core.jpa.Customer;
import com.cloud.nvl.core.jpa.test.JpaTest;

public class AccountTest extends JpaTest {

	public static final String TEST_NAME = "AccountTest";
	public static final String PERSISTENCE_UNIT_UNDER_TEST = "Accounts";

	protected static EntityManagerFactory emf;

	@Before
	public void setUp() throws Exception {
		emf = lookupEntityManagerFactory(TEST_NAME, PERSISTENCE_UNIT_UNDER_TEST);
	}

	/* === Subclassed methods === */

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public String getTestPersistenceUnitName() {
		return PERSISTENCE_UNIT_UNDER_TEST;
	}

	@Test
	public void newObject() {
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
	}

	@Test
	public void queryObjects() {
		EntityManager em = (EntityManager) getEmf();
		TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a",
				Account.class);
		List<Account> results = q.getResultList();
		System.out.println("\n*** Account Report ***");
		for (Account acct : results) {
			System.out.println("Account: " + acct);
		}
		em.close();
	}

	@Test
	public void testGettingMetamodel() {
		log("testGettingMetamodel");
		EntityManager em = getEmf().createEntityManager();
		Set<EntityType<?>> s = em.getMetamodel().getEntities();
		for (EntityType<?> et : s) {
			log("Managed Entity name: " + et.getName());
			log("Managed Entity class: " + et.getJavaType());
			log("Classloader: " + et.getJavaType().getClassLoader());
		}
	}
}
