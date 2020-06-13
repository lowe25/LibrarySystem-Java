package Hibernate.model;
import java.io.Serializable;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Students")
public class Students implements Serializable{
	private static final long serialVersionUID = -5302830493777198637L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="StudentID")
    private int studentid;
    
	@Column(name="Firstname")
    private String firstname;
    
    @Column(name="Middlename")
    private String middlename;
    
    @Column(name="Lastname")
    private String lastname;
    
    @Column(name="Age")
    private int age;
    
    @Column(name="Contact")
    private int contact;
    
    @Column(name="DOB")
    private String dob;
    
    @Column(name="Course")
    private String course;
    
    @Column(name="Strand")
    private String strand;
    
    @Column(name="Section")
    private String section;
    
    @Column(name="Year")
    private int year;
    
    @Column(name="Fullname")
    private String fullname;
    
	public Students() {}
    public Students(int StudentID, String Firstname, String Middlename, String Lastname, int Age, int Contact, String DOB, String Course, String Strand, int Year, String Fullname) {
    	this.studentid = studentid;
    	this.firstname = firstname;
    	this.middlename = middlename;
    	this.lastname = lastname;
    	this.age = age;
    	this.contact = contact;
    	this.dob = dob;
    	this.course = course;
    	this.strand = strand;
    	this.year = year;
    	this.fullname = fullname;
    }
    
    /*====================================================================================*/
    
    public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStrand() {
		return strand;
	}
	public void setStrand(String strand) {
		this.strand = strand;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	  public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
}
