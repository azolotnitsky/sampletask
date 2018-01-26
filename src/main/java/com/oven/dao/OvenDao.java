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


    public int registerDevice(long id, int deviceNumber) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();

            session.beginTransaction();
            Oven oven = new Oven();
            oven.setId(id);
            oven.setDeviceNumber(deviceNumber);
            oven.setMode(Oven.Mode.SWITCHEDOFF);
            oven.setTemperature(10);

            // Save the invitation to database
            session.save(oven);

            // Commit the transaction
            session.getTransaction().commit();

        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.registerDevice()", e);
        } finally {
            if(session != null) session.close();
        }

        return 1;
    }

    public int changeTemperature(int id, double delta) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            Oven oven = session.get(Oven.class, id);
            oven.changeTemperature(delta);
            session.saveOrUpdate(oven);
            session.getTransaction().commit();

        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.changeTemperature()", e);
        } finally {
            if(session != null) session.close();
        }

        return 1;
    }

    public int setMode(int id, Oven.Mode newmode) {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            Oven oven = session.get(Oven.class, id);
            oven.setMode(newmode);
            session.saveOrUpdate(oven);
            session.getTransaction().commit();

        } catch(HibernateException e) {
            LOG.error("HibernateException in OvenDao.setMode()", e);
        } finally {
            if(session != null) session.close();
        }

        return 1;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
