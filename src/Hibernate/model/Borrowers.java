package Hibernate.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Borrower")
public class Borrowers implements Serializable{
	private static final long serialVersionUID = -7466445239396094388L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BorrowerID")
	private int borrowerid;
	
	@Column(name="StudentID")
	private int studentID;

	@Column(name="Fullname")
	private String fullname;
	
	@Column(name="BookID")
	private int bookid;
	
	@Column(name="BookName")
	private String bookname;
	
	@Column(name="BorrowDate")
	private String borrowdate;
	
	@Column(name="ReturnDate")
	private String returndate;
	
	@Column(name="BookStatus")
	private String bookstatus;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Books book;
	public Borrowers() {}
	
	public Borrowers(int BorrowerID, Books book, int StudentID,  String Fullname, int bookid, String BookName, String BorrowDate, String ReturnDate, String BookStatus) {
		super();
		this.borrowerid = borrowerid;
		this.studentID = studentID;
		this.bookid = bookid;
		this.book = book;
		this.fullname = fullname;
		this.bookname = bookname;
		this.borrowdate = borrowdate;
		this.returndate = returndate;
		this.bookstatus = bookstatus;
	}
	  public int getBorrowerid() {
			return borrowerid;
		}

		public void setBorrowerid(int borrowerid) {
			this.borrowerid = borrowerid;
		}
		
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public Books getBook() {
		return book;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBorrowdate() {
		return borrowdate;
	}

	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getBookstatus() {
		return bookstatus;
	}

	public void setBookstatus(String bookstatus) {
		this.bookstatus = bookstatus;
	}

}
