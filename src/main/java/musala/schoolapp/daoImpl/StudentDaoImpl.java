package musala.schoolapp.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import musala.schoolapp.dao.StudentDao;
import musala.schoolapp.main.DBSessionFactory;
import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;
import musala.schoolapp.model.StudentDTO;

public class StudentDaoImpl implements StudentDao {

	private SessionFactory sessionFactory;
	private Session session = null;
	private Transaction tx = null;

	/* Method to ADD a student to the records */
	public Integer addStudent(Student student) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.save(student);
			tx.commit();

			System.out.println("Student added.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Student is not added.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return student.getId();
	}

	/* Method to DELETE a student from the records */
	public void deleteStudent(Integer id) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			student.setSchool(null);
			session.save(student);
			session.delete(student);
			tx.commit();

			System.out.println("Student deleted.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Student is not deleted.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	

	/* Method to UPDATE student by id */
	public Student updateStudent(Student student) {
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			student = (Student) session.merge(student);
			tx.commit();

			System.out.println("Student updated.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Student is not updated.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return student;
	}

	/* Method to FIND student by id */
	public Student findById(Integer id) {
		Student student = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			student = (Student) session.get(Student.class, id);
			tx.commit();

			System.out.println("Returned " + (student != null ? student.toString() : null));
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Student is not found.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	/* Method to LIST all the students */
	public List<Student> listStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Criteria cr = session.createCriteria(Student.class);
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.addOrder(Order.asc("id"));
			students = cr.list();

		} catch (Exception ex) {
			System.out.println("Students are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return students;
	}

	/* Method to RETURN list of girls which index starts with 135 */
	@SuppressWarnings("unchecked")
	public List<Student> countGirls() {
		List<Student> students = new ArrayList<Student>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Criteria cr = session.createCriteria(Student.class);
			cr.setProjection(Projections.rowCount());
			cr.add(Restrictions.and(Restrictions.like("index", "135%"), Restrictions.eq("gender", "female")));
			students = cr.list();

		} catch (Exception ex) {
			System.out.println("Students are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return students;
	}

	/* Method to RETURN list of girls which index starts with 135 */
	@SuppressWarnings("unchecked")
	public List<StudentDTO> listGirls() {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Criteria cr = session.createCriteria(Student.class);
			ProjectionList pr = Projections.projectionList();
			pr.add(Projections.groupProperty("id"), "id");
			pr.add(Projections.groupProperty("firstname"), "firstname");
			pr.add(Projections.groupProperty("lastname"), "lastname");
			pr.add(Projections.groupProperty("gender"), "gender");
			pr.add(Projections.groupProperty("dateOfBirth"), "dateOfBirth");
			pr.add(Projections.groupProperty("index"), "indexNumber");
			cr.setProjection(pr);
			cr.add(Restrictions.and(Restrictions.like("index", "135%"), Restrictions.eq("gender", "female")));
			cr.addOrder(Order.asc("id"));
			cr.setResultTransformer(Transformers.aliasToBean(StudentDTO.class));
			students = cr.list();

		} catch (Exception ex) {
			System.out.println("Students are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudentsBySubjectName(String subjectName) {
		List<Student> students = new ArrayList<Student>();
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();

			Criteria cr = session.createCriteria(Student.class).createAlias("subjects", "subject");
			cr.add(Restrictions.and(Restrictions.eq("subject.name", subjectName)));
			students = cr.list();

		} catch (Exception ex) {
			System.out.println("Students are not listed.");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return students;
	}

	@Override
	public School findSchoolByStudent(Integer id) {
		School school = null;
		try {
			sessionFactory = DBSessionFactory.getSessionFactory();
			session = sessionFactory.openSession();
			
			Query query = session.getNamedQuery("student.listSchoolByStudents");
			query.setInteger("id", id);
			school = (School) query.list().get(0);
			
		} catch(Exception ex){
			System.out.println("School not found");
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return school;
	}

}