package com.oven.dao;

import com.oven.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.HibernateException;

import java.util.List;

@Repository
public class OvenDao extends BasicDao<Oven> {

    private final Log LOG = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;


    public int registerDevice(int devicenumber) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            result = session.createQuery("insert into DeviceRegsitry (devicenumber) VALUES (:devicenumber)").executeUpdate();
        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.registerDevice()", e);
        } finally {
            if(session != null) session.close();
        }

        return result;
    }

    public int changeTemperature(double delta) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("update Temperature t set t.degrees = t.degrees + :delta");
            query.setParameter("delta", delta);
            result = query.executeUpdate();

        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.changeTemperature()", e);
        } finally {
            if(session != null) session.close();
        }

        return result;
    }

    public int setMode(Oven.Mode newmode) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("update Mode (mode) VALUES (:newmode)");
            query.setParameter("mode", newmode);
            result = query.executeUpdate();

        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.setMode()", e);
        } finally {
            if(session != null) session.close();
        }

        return result;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
