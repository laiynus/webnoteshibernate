package by.khrapovitsky.dao;

import by.khrapovitsky.model.Note;
import by.khrapovitsky.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class NotesDAOImplement implements NotesDAO {

    public void delete(Note note) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void insert(Note note) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Note note) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(note);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Note> getAllNotes() {
        Session session = null;
        List notes = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            notes = session.createCriteria(Note.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }

   public Note getNote(int id) {
       Session session = null;
       Note note = null;
       try {
           session = HibernateUtil.getSessionFactory().openSession();
           note = (Note) session.get(Note.class, id);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return note;
    }

   public List<Note> getLastUserNotes(String login) {
       Session session = null;
       List notes = new ArrayList();
       try {
           session = HibernateUtil.getSessionFactory().openSession();
           notes = session.createCriteria(Note.class).add(Restrictions.like("login", login)).addOrder(Order.desc("datetimecreate")).setMaxResults(10).list();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return notes;
   }

    public List<Note> getUserNotes(String login) {
        Session session = null;
        List notes = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            notes = session.createCriteria(Note.class).add(Restrictions.like("login", login)).addOrder(Order.desc("datetimecreate")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }
}
