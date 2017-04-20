package musala.schoolapp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import musala.schoolapp.dao.SubjectDao;
import musala.schoolapp.daoImpl.SubjectDaoImpl;
import musala.schoolapp.model.Subject;

@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectResource {
	
	SubjectDao subjectDao = new SubjectDaoImpl();

	@GET
	public List<Subject> getSubjects() {
		return subjectDao.listSubjects();
	}

	@GET
	@Path("/{subjectId}")
	public Response getSubject(@PathParam("subjectId") Integer id) {
		Response response = null;
		Subject subject = subjectDao.findById(id);
		if (subject == null) {
			response = Response.status(500).entity("Subject not found").build();
		} else {
			response = Response.status(200).entity(subject).build();
		}
		return response;
	}

	@GET
	@Path("/{subjectId}/students")
	public Response getStudentsBySubject(@PathParam("subjectId") Integer id) {
		Response response = null;
		Subject subject = subjectDao.findById(id);
		if (subject == null) {
			response = Response.status(500).entity("Subject not found").build();
		} else {
			response = Response.status(200).entity(subject.getStudents()).build();
		}
		return response;
	}

	@POST
	public Response addSubject(Subject subject) {
		Response response = null;
		if (subject == null) {
			response = Response.status(500).entity("There is not subject in the body of the request.").build();
		} else {
			Integer id = subjectDao.addSubject(subject);
			response = Response.status(200).entity(id).build();
		}
		return response;
	}

	@PUT
	@Path("/{subjectId}")
	public Response updateSubject(@PathParam("subjectId") Integer id, Subject subject) {
		Response response = null;
		if (subject == null) {
			response = Response.status(500).entity("There is not valid subject in the body of the request.").build();
		} else {
			subject.setId(id);
			Subject updatedSubject = subjectDao.updateSubject(subject);
			response = Response.status(200).entity(updatedSubject).build();
		}
		return response;
	}

	@DELETE
	@Path("/{subjectId}")
	public Response deleteStudent(@PathParam("subjectId") Integer id) {
		Response response = null;
		if (subjectDao.findById(id) == null) {
			response = Response.status(500).entity("Subject is not found.").build();
		} else {
			subjectDao.deleteSubject(id);
			response = Response.status(500).entity("Subject is deleted.").build();
		}
		return response;
	}
}
