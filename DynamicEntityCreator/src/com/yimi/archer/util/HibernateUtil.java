package com.yimi.archer.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate相关的功能类
 * 
 * @author chezg
 * 
 */
public class HibernateUtil {
	private static final SessionFactory m_session_factory = buildSessionFactory();

	private HibernateUtil() {
	}

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			SessionFactory sessionFactory = cfg
					.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Exception ex) {
			System.out.println("Initial SessionFactory creation failed!" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public synchronized static SessionFactory getSessionFactory() {
		return m_session_factory;
	}

	public static Session openSession() {
		return getSessionFactory().openSession();
	}

	private static Transaction getTransaction(Session _session) {
		return _session.getTransaction();
	}

	public static void commitTransaction(Session _session) {
		getTransaction(_session).commit();
	}

	public static void rollbackTransaction(Session _session) {
		getTransaction(_session).rollback();
	}

}
