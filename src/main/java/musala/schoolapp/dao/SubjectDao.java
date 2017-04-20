package musala.schoolapp.dao;

import java.util.List;

import musala.schoolapp.model.Subject;

public interface SubjectDao {
	
	public Integer addSubject(Subject subject);
	
	public Subject updateSubject(Subject subject);
	
	public void deleteSubject(Integer id);
	
	public Subject findById(Integer id);
	
	public List<Subject> listSubjects();

}
