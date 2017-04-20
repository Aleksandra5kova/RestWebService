package musala.schoolapp.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import musala.schoolapp.dao.StudentDao;
import musala.schoolapp.daoImpl.StudentDaoImpl;
import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;
import musala.schoolapp.model.StudentDTO;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

	StudentDao studentDao = new StudentDaoImpl();

	@GET
	public List<Student> getStudents() {
		return studentDao.listStudents();
	}

	@GET
	@Path("/{studentId}")
	public Response getStudent(@PathParam("studentId") Integer id) {
		Response response = null;
		Student student = studentDao.findById(id);
		if (student == null) {
			response = Response.status(500).entity("Student not found").build();
		} else {
			response = Response.status(200).entity(student).build();
		}
		return response;
	}

	@GET
	@Path("/{studentId}/subjects")
	public Response getSubjectsByStudent(@PathParam("studentId") Integer id) {
		Response response = null;
		Student student = studentDao.findById(id);
		if (student == null) {
			response = Response.status(500).entity("Student not found").build();
		} else {
			response = Response.status(200).entity(student.getSubjects()).build();
		}
		return response;
	}

	@GET
	@Path("/subjectName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> listStudentsBySubjectName(@QueryParam("subjectName") String subjectName) {
		StudentDao studentDao = new StudentDaoImpl();
		List<Student> students = new ArrayList<>();
		if (subjectName == null) {
			students = studentDao.listStudents();
		} else {
			students = studentDao.listStudentsBySubjectName(subjectName);
		}
		return students;
	}
	
	@GET
	@Path("/schoolByStudent/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public School listStudentsBySubjectName(@PathParam("id") Integer id) {
		
		return studentDao.findSchoolByStudent(id);
		
	}
	
	@GET
	@Path("/girls")
	public List<StudentDTO> getGirls() {
		return studentDao.listGirls();
	}

	@POST
	public Response addStudent(Student student) {
		Response response = null;
		if (student == null) {
			response = Response.status(500).entity("There is not valid student in the body of the request.").build();
		} else {
			Integer id = studentDao.addStudent(student);
			response = Response.status(200).entity(id).build();
		}
		return response;
	}

	@PUT
	@Path("/{studentId}")
	public Response updateStudent(@PathParam("studentId") Integer id, Student student) {
		Response response = null;
		if (student == null) {
			response = Response.status(500).entity("There is not valid student in the body of the request.").build();
		} else {
			student.setId(id);
			Student updatedStudent = studentDao.updateStudent(student);
			response = Response.status(200).entity(updatedStudent).build();
		}
		return response;
	}

	@DELETE
	@Path("/{studentId}")
	public Response deleteStudent(@PathParam("studentId") Integer id) {
		Response response = null;
		if (studentDao.findById(id) == null) {
			response = Response.status(500).entity("Student is not found.").build();
		} else {
			studentDao.deleteStudent(id);
			response = Response.status(500).entity("Student is deleted.").build();
		}
		return response;
	}
}
