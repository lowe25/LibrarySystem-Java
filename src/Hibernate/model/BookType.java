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
@Table(name="BookType")
public class BookType implements Serializable {
	private static final long serialVersionUID = -5302830493777198637L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BookTypeID")
	private int booktypeid;
	
	@Column(name="BookType")
	private String booktype;
	
	public BookType() {}
	
	public BookType(int BookTypeID, String BookType) {
		this.booktypeid = booktypeid;
		this.booktype = booktype;
	}
	
	public int getBooktypeid() {
		return booktypeid;
	}

	public void setBooktypeid(int booktypeid) {
		this.booktypeid = booktypeid;
	}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	
}
