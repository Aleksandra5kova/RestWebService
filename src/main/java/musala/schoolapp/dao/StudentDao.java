package musala.schoolapp.dao;

import java.util.List;

import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;
import musala.schoolapp.model.StudentDTO;

public interface StudentDao {

	public Integer addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(Integer id);
	
	public Student findById(Integer id);
	
	public List<Student> listStudents();
	
	public List<Student> countGirls();
	
	public List<StudentDTO> listGirls();

	public List<Student> listStudentsBySubjectName(String subjectName);
	
	public School findSchoolByStudent(Integer id);
}
