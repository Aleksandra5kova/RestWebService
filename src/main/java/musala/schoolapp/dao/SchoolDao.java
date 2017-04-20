package musala.schoolapp.dao;
import java.util.List;

import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;

public interface SchoolDao {

	public Integer addSchool(School school);
	
	public School updateSchool(School school);
	
	public void deleteSchool(Integer id);
	
	public School findById(Integer id);
	
	public List<School> listSchools();

	public List<Student> listStudentsBySchool(Integer id);
}
