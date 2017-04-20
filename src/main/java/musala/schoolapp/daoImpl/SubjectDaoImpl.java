package musala.schoolapp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import musala.schoolapp.dao.SubjectDao;
import musala.schoolapp.main.DBSessionFactory;
import musala.schoolapp.model.Subject;

public class SubjectDaoImpl implements SubjectDao {

	private SessionFactory sessionFactory;
	private Session session = null;
	private Transaction tx = null;

	/* Method to ADD a subject to the records */
	public Integer addSubject(Subject subject) {
		Integer subjectId = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			subjectId = (Integer) session.save(subject);
			tx.commit();

			System.out.println("Subject added.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Subject is not added.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return subjectId;
	}
	

	/* Method to UPDATE a subject from the records */
	public Subject updateSubject(Subject subject) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			subject = (Subject) session.merge(subject);
			tx.commit();

			System.out.println("Subject updated.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Subject is not updated.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return subject;
	}

	/* Method to DELETE a subject from the records */
	public void deleteSubject(Integer id) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			Subject subject = (Subject) session.get(Subject.class, id);
			session.delete(subject);
			tx.commit();

			System.out.println("Subject deleted.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Subject is not deleted.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	

	/* Method to FIND subject by id */
	public Subject findById(Integer id) {
		Subject subject = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			subject = (Subject) session.get(Subject.class, id);
			tx.commit();

			System.out.println("Returned " + (subject != null ? subject.toString() : null));
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Subject is not found.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return subject;
	}

	@SuppressWarnings("unchecked")
	/* Method to LIST all the subjects */
	public List<Subject> listSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Query query = session.getNamedQuery("subject.listSubjects");
			subjects = query.list();

		} catch (Exception ex) {
			System.out.println("Subjects are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return subjects;
	}


}
