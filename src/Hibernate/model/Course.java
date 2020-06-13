package Hibernate.model;
import java.io.Serializable;

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
@Table(name="Course")
public class Course implements Serializable{
	private static final long serialVersionUID = -5302830493777198637L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CourseID")
    private int courseid;
 
	@Column(name="Course")
    private String course;
    
    public Course() {}
    
    public Course(int CourseID, String Course) {
    	this.courseid = courseid;
    	this.course = course;
    }
    
    public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
