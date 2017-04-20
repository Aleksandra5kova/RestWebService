package musala.schoolapp.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String firstname;

	private String lastname;

	private String gender;

	private Date dateOfBirth;

	private String indexNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIndexNumber() {
		return indexNumber;
	}
	
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	} 
	
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return "StudentDTO [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", dateOfBirth=" + df.format(dateOfBirth) + ", index=" + indexNumber + "]";
	}

}
