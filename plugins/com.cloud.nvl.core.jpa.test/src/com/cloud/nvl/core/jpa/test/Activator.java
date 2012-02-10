package com.cloud.nvl.core.jpa.test;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator, ServiceTrackerCustomizer {

	BundleContext ctx;
	ServiceTracker emfTracker;

	public void start(BundleContext context) throws Exception {
//		for (Bundle b : context.getBundles()) {
//			if (b.getSymbolicName().equals("com.cloud.nvl.core.jpa")) {
//				b.start();
//			}
//		}
		ctx = context;
		System.out.println("JPA Sample started");
		JpaTest.context = ctx;
		/*
		 * We are in the same bundle as the persistence unit so the services
		 * should be available when we start up (if nothing bad happened) and
		 * the tracker is really just saving us the lookup, but this is the idea
		 * of how you would listen for a persistence unit coming from another
		 * bundle.
		 */
		emfTracker = new ServiceTracker(ctx,
				EntityManagerFactory.class.getName(), this);
		emfTracker.open();
	}

	public void stop(BundleContext context) throws Exception {
		emfTracker.close();
		System.out.println("JPA Sample stopped");
	}

	/* ======================== */
	/* ServiceTracker methods */
	/* ======================== */

	public Object addingService(ServiceReference ref) {
		Bundle b = ref.getBundle();
		Object service = b.getBundleContext().getService(ref);
		String unitName = (String) ref
				.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);
		if (unitName.equals("Accounts")) {
			System.out.println("JPA Sample service"
					+ (EntityManagerFactory) service);
		}
		return service;
	}

	public void modifiedService(ServiceReference ref, Object service) {
	}

	public void removedService(ServiceReference ref, Object service) {
	}

}
