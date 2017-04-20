package musala.schoolapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({ @NamedQuery(name="school.listStudentsBySchool", query="select s.students FROM School s WHERE s.id = :id")})

@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue
	@Column(name = "school_id")
	private Integer id;

	@Column(name = "school_name")
	private String name;

	@Column(name = "school_address")
	private String address;

	@Column(name = "school_phone")
	private String phone;

	@OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
//	@JsonManagedReference
	@JsonIgnore
	private Set<Student> students;

	public School() {
	}

	public School(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		String school = "School [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
		return school;
	}
}