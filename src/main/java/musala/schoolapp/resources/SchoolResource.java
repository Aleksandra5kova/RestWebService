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

import musala.schoolapp.dao.SchoolDao;
import musala.schoolapp.daoImpl.SchoolDaoImpl;
import musala.schoolapp.model.School;
import musala.schoolapp.model.Student;

@Path("/schools")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SchoolResource {

	private SchoolDao schoolDao = new SchoolDaoImpl();

	@GET
	public List<School> getSchools() {
		return schoolDao.listSchools();
	}

	@GET
	@Path("/{schoolId}")
	public Response getSchool(@PathParam("schoolId") Integer id) {
		School school = schoolDao.findById(id);
		Response response = null;
		if (school == null) {
			response = Response.status(500).entity("School not found.").build();
		} else {
			response = Response.status(200).entity(school).build();
		}
		return response;
	}

	@GET
	@Path("/{schoolId}/students")
	public Response getStudentsBySchool(@PathParam("schoolId") Integer id) {
		Response response = null;
		School school = schoolDao.findById(id);
		if (school == null) {
			response = Response.status(500).entity("School not found").build();
		} else {
			response = Response.status(200).entity(school.getStudents()).build();
		}
		return response;
	}

	@GET
	@Path("/studentsBySchool/{id}")
	public List<Student> getStudentsBySchool1(@PathParam("id") Integer id) {
		return schoolDao.listStudentsBySchool(id);
	}

	@POST
	public Response addSchool(School school) {
		Response response = null;
		if (school == null) {
			response = Response.status(500).entity("There is not school in the body of the request.").build();
		} else {
			Integer id = schoolDao.addSchool(school);
			response = Response.status(200).entity(id).build();
		}
		return response;
	}

	@PUT
	@Path("/{schoolId}")
	public Response updateSchool(@PathParam("schoolId") Integer id, School school) {
		Response response = null;
		if (school == null) {
			response = Response.status(500).entity("There is not valid school in the body of the request.").build();
		} else {
			school.setId(id);
			School updatedSchool = schoolDao.updateSchool(school);
			response = Response.status(200).entity(updatedSchool).build();
		}
		return response;
	}

	@DELETE
	@Path("/{schoolId}")
	public Response deleteSchool(@PathParam("schoolId") Integer id) {
		Response response = null;
		if (schoolDao.findById(id) == null) {
			response = Response.status(500).entity("School is not found.").build();
		} else {
			schoolDao.deleteSchool(id);
			response = Response.status(500).entity("School is deleted.").build();
		}
		return response;
	}
}
