package musala.schoolapp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import musala.schoolapp.dao.SchoolDao;
import musala.schoolapp.main.DBSessionFactory;
import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;

public class SchoolDaoImpl implements SchoolDao {

	private SessionFactory sessionFactory;
	private Session session = null;
	private Transaction tx = null;

	/* Method to ADD a school to the records */
	public Integer addSchool(School school) {
		Integer schoolId = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			schoolId = (Integer) session.save(school);
			tx.commit();

			System.out.println("School added." + school.getId());
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("School is not added.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return schoolId;
	}

	/* Method to UPDATE a school by id */
	public School updateSchool(School school) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			school = (School) session.merge(school);
			tx.commit();

			System.out.println("School updated.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("School is not updated.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return school;
	}

	/* Method to DELETE a school from the records */
	public void deleteSchool(Integer id) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			School school = (School) session.get(School.class, id);
			session.delete(school);
			tx.commit();

			System.out.println("School deleted.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("School is not deleted.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to FIND school by id */
	public School findById(Integer id) {
		School school = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			school = (School) session.get(School.class, id);
			tx.commit();

			System.out.println("Returned " + (school != null ? school.toString() : null));
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("School is not found.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return school;
	}

	@SuppressWarnings("unchecked")
	/* Method to LIST all the schools */
	public List<School> listSchools() {
		List<School> schools = new ArrayList<School>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Query query = session.getNamedQuery("school.listSchools");
			schools = query.list();

		} catch (Exception ex) {
			System.out.println("Schools are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return schools;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsBySchool(Integer id) {
		List<Student> students = new ArrayList<>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("school.listStudentsBySchool");
			query.setInteger("id", id);
			students = query.list();
		} catch(Exception ex){
			System.out.println("Student are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return students;
	}

}
