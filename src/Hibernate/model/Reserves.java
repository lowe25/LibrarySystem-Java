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
@Table(name="ReserveBooks")
public class Reserves implements Serializable{
	private static final long serialVersionUID = -5302830493777198637L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ReserveBookID")
    private int resevebookid;
    
	@Column(name="BookID")
    private int bookid;
	
	@Column(name="BookName")
	private String bookname;
    
	@Column(name="BookType")
    private String booktype;
    
    @Column(name="BookStatus")
    private String bookstatus;
    
    @Column(name="StudentID")
    private int studentid;
    
    @Column(name="Fullname")
    private String fullname;
    
    @Column(name="Contact")
    private int contact;
    
    public Reserves() {}
    
    public Reserves(int ReserveBookID, int BookID, String BookName, String BookType, String BookStatus, int StudentID, String Fullname, int Contact) {
    	this.resevebookid = resevebookid;
    	this.bookid = bookid;
    	this.booktype = booktype;
    	this.bookstatus = bookstatus;
    	this.studentid = studentid;
    	this.fullname = fullname;
    	this.contact = contact;
    }
    
    public int getResevebookid() {
		return resevebookid;
	}

	public void setResevebookid(int resevebookid) {
		this.resevebookid = resevebookid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	 public String getBookname() {
			return bookname;
		}

		public void setBookname(String bookname) {
			this.bookname = bookname;
		}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	public String getBookstatus() {
		return bookstatus;
	}

	public void setBookstatus(String bookstatus) {
		this.bookstatus = bookstatus;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}
}
