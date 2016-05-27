package com.pFoods.dbo.singleton;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This class initiates hibernate sessionfactory at application start up
 * It implements singleton pattern so we have only one instance per application  
 * @author Priti Patel
 *
 */
public enum SessionFactoryBuilderSinglton {
	
	INSTANCE;
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getInstance(){
		return sessionFactory;
	}
	
	public SessionFactory buildHibernateSessionFactory(){
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}