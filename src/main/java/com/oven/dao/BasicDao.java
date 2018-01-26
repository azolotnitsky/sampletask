package com.oven.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
     Generic class for all DAO objects
*/
public class BasicDao<T> {

    private final Log LOG = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    /*
	Saves new object or updates existing object
	@param obj object to be saved
	@return void
    */
    public void saveOrUpdate(T obj) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
        } catch(HibernateException e) {
            LOG.error("HibernateException in BasicDao.saveOrUpdate", e);
        } finally {
            tx.commit();
            if(session != null) session.close();
        }
    }

    /*
	Deletes existing object
	@param obj object to be deleted
	@return void
    */
    public void remove(T obj) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(obj);
            session.flush();
        } catch(HibernateException e) {
            LOG.error("HibernateException in BasicDao.remove", e);
        } finally {
            tx.commit();
            if(session != null) session.close();
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
