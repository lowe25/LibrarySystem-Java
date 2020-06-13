package Hibernate.model;
import java.io.Serializable;



import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="Books")
public class Books implements Serializable{
	private static final long serialVersionUID = -5302830493777198637L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookID")
    private int bookid;

	@Column(name="BookName")
    private String bookname;
    
    @Column(name="Page")
    private int page;
    
    @Column(name="Author")
    private String author;
    
    @Column(name="ISBN")
    private int isbn;
    
    @Column(name="BookType")
    private String booktype;
    
	@Column(name="BookStatus")
    private String bookstatus;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Set<Borrowers> borrow = new HashSet<>();
    
	public Books() {}
    
    public Books(int BookID, String BookName, int Page, String Author, String BookType, int ISBN, String BookStatus, Set<Borrowers> borrow) {
    	this.bookid = bookid;
    	this.bookname = bookname;
    	this.page = page;
    	this.author = author;
    	this.booktype = booktype;
		this.isbn = isbn;
    	this.bookstatus = bookstatus;
    }
    
    /*=======================================================================*/
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

  	public int getPage() {
  		return page;
  	}

  	public void setPage(int page) {
  		this.page = page;
  	}

  	public String getAuthor() {
  		return author;
  	}

  	public void setAuthor(String author) {
  		this.author = author;
  	}
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
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
  	
    public Set<Borrowers> getBorrow() {
 		return borrow;
 	}

 	public void setBorrow(Set<Borrowers> borrow) {
 		this.borrow = borrow;
 	}

}
