package hibernate.dao;

import hibernate.model.Estudiante;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.SystemException;
import java.util.List;

public class StudentDao {

    public void guardarEstudiante(Estudiante estudiante) throws SystemException {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(estudiante);

            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void actualizarEstudiante(Estudiante estudiante) throws SystemException {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(estudiante);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    public Estudiante obtenerEstudiante(long id) throws SystemException {
        Transaction transaction = null;
        Estudiante estudiante = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            estudiante = session.get(Estudiante.class, id);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return estudiante;
    }

    public List<Estudiante> obtenerEstudiantes() throws SystemException {
        Transaction transaction = null;
        List<Estudiante> estudiantes = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            estudiantes = session.createQuery("from Estudiante").list();

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return estudiantes;
    }

    public Estudiante borrarEstudiante(long id) throws SystemException {
        Transaction transaction = null;
        Estudiante estudiante = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            estudiante = session.get(Estudiante.class, id);

            session.delete(estudiante);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return estudiante;
    }
}
