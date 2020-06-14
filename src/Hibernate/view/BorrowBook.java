package Hibernate.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSplitPane;

import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.Books;
import Hibernate.model.Borrowers;
import Hibernate.model.Students;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class BorrowBook extends JFrame {

	private JPanel contentPane;
	private JTable tblBookList;
	private JTextField txtStudentID;
	private JTextField txtFullname;
	private JTextField txtContact;
	private JTextField txtSection;
	private JTextField txtStrand;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JTextField txtType;
	private JTextField txtBookStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AddStudent view = new AddStudent();
		AddBook ab = new AddBook();
		BookList booklist = new BookList();
		ab.cboType();
		view.comboCourse();
		view.comboStrand();
		booklist.cboType();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBook frame = new BorrowBook();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void tblRefresh() {
		Session session = Database.getSession();
		try {
			String sql ="SELECT BookID AS bookid, "
					+ "BookName AS bookname, Page AS page, Author AS author,"
					+ "BookType AS booktype, ISBN AS isbn, BookStatus AS bookstatus FROM Books ";
			DefaultTableModel model = (DefaultTableModel)tblBookList.getModel();
			model.setRowCount(0);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Books.class));
			List<Books> bookList = query.list();
		    for(Books books: bookList) {
		    	model.addRow(new Object[] {
					books.getBookid(),
					books.getBookname(),
					books.getPage(),
					books.getAuthor(),
					books.getIsbn(),
				    books.getBooktype(),
					books.getBookstatus()
				});
		    }
		}catch(Exception e) {
			
		}
	}
	public void refresh() {
		 txtStudentID.setText(null);
	 txtFullname.setText(null);
		 txtContact.setText(null);
		 txtSection.setText(null);
		txtStrand.setText(null);
		txtBookID.setText(null);
		 txtBookName.setText(null);
		 txtType.setText(null);
		 txtBookStatus.setText(null);
	}
	public BorrowBook() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BorrowBook Page:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 128, 14);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 792, 409);
		contentPane.add(tabbedPane);
		
		JPanel BorrowBook = new JPanel();
		tabbedPane.addTab("BorrowBook", null, BorrowBook, null);
		BorrowBook.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BookList:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 92, 14);
		BorrowBook.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 765, 112);
		BorrowBook.add(scrollPane);
		
		tblBookList = new JTable();
		tblBookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  DefaultTableModel model = (DefaultTableModel)tblBookList.getModel();
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      txtBookID.setText(model.getValueAt(row, 0).toString());
			      txtBookName.setText(model.getValueAt(row, 1).toString());
			      txtType.setText(model.getValueAt(row,5).toString());
			      txtBookStatus.setText(model.getValueAt(row, 6).toString());
			}
		});
		scrollPane.setViewportView(tblBookList);
		tblBookList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "Pages", "Author", "ISBN", "BookType", "BookStatus"
			}
		));
		//SHOW SQL DATA TO JAVA TABLE
		Session session = Database.getSession();
		try {
			String sql ="SELECT BookID AS bookid, "
					+ "BookName AS bookname, Page AS page, Author AS author,"
					+ "BookType AS booktype, ISBN AS isbn, BookStatus AS bookstatus FROM Books ";
			DefaultTableModel model = (DefaultTableModel)tblBookList.getModel();
			model.setRowCount(0);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Books.class));
			List<Books> bookList = query.list();
		    for(Books books: bookList) {
		    	model.addRow(new Object[] {
					books.getBookid(),
					books.getBookname(),
					books.getPage(),
					books.getAuthor(),
					books.getIsbn(),
				    books.getBooktype(),
					books.getBookstatus()
				});
		    }
		}catch(Exception e) {
			
		}
		
		JLabel lblNewLabel_2 = new JLabel("StudentID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(450, 192, 92, 14);
		BorrowBook.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setBounds(535, 190, 92, 20);
		BorrowBook.add(txtStudentID);
		txtStudentID.setColumns(10);
		
		//DATABASE TO TEXTBOX
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				try {
					int StudID = Integer.parseInt(txtStudentID.getText());
					String sql ="SELECT StudentID AS studentid,"
							+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
							+ "Section AS section, Year AS year, Fullname AS fullname FROM Students "
							+ "WHERE StudentID = :id";
					Query query = session.createSQLQuery(sql);
					query.setParameter("id", StudID);
					query.setResultTransformer(Transformers.aliasToBean(Students.class));
					List<Students> stud = query.list();
					String Fname=null;
					int Contact=0;
					for(Students studInfo : stud) {
						 Fname = studInfo.getFullname();
						Contact = studInfo.getContact();
					}
				//	int Contact = Integer.parseInt(txtContact.getText());
					txtFullname.setText(Fname);
					txtContact.setText(String.valueOf(Contact));
					//Contact = Integer.parseInt();
				} catch(Exception ex) {
					ex.printStackTrace();
				} 
			}
		});
		btnSearch.setBounds(642, 189, 89, 23);
		BorrowBook.add(btnSearch);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fullname:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(450, 219, 92, 14);
		BorrowBook.add(lblNewLabel_2_1);
		
		txtFullname = new JTextField();
		txtFullname.setEditable(false);
		txtFullname.setColumns(10);
		txtFullname.setBounds(535, 217, 129, 20);
		BorrowBook.add(txtFullname);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Contact:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(450, 250, 92, 14);
		BorrowBook.add(lblNewLabel_2_1_1_1_1);
		
		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setColumns(10);
		txtContact.setBounds(535, 248, 129, 20);
		BorrowBook.add(txtContact);
		
		txtSection = new JTextField();
		txtSection.setColumns(10);
		txtSection.setBounds(631, 397, 92, 20);
		BorrowBook.add(txtSection);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Section:");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1.setBounds(546, 399, 92, 14);
		BorrowBook.add(lblNewLabel_2_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Year/Strand:");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(546, 428, 92, 14);
		BorrowBook.add(lblNewLabel_2_1_1_1_1_1_1);
		
		txtStrand = new JTextField();
		txtStrand.setColumns(10);
		txtStrand.setBounds(631, 426, 92, 20);
		BorrowBook.add(txtStrand);
		
		JLabel lblNewLabel_3 = new JLabel("Borrower Details:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(450, 159, 142, 14);
		BorrowBook.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Book Details:");
		lblNewLabel_3_1.setBounds(10, 159, 123, 17);
		BorrowBook.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2 = new JLabel("BookID:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 194, 92, 14);
		BorrowBook.add(lblNewLabel_2_2);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setColumns(10);
		txtBookID.setBounds(105, 192, 103, 20);
		BorrowBook.add(txtBookID);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("BookName:");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(10, 221, 92, 14);
		BorrowBook.add(lblNewLabel_2_2_1);
		
		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setColumns(10);
		txtBookName.setBounds(105, 219, 177, 20);
		BorrowBook.add(txtBookName);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("BookType:");
		lblNewLabel_2_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2.setBounds(10, 252, 92, 14);
		BorrowBook.add(lblNewLabel_2_2_2);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBounds(105, 250, 177, 20);
		BorrowBook.add(txtType);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("BookStatus:");
		lblNewLabel_2_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2_1.setBounds(10, 280, 92, 14);
		BorrowBook.add(lblNewLabel_2_2_2_1);
		
		txtBookStatus = new JTextField();
		txtBookStatus.setEditable(false);
		txtBookStatus.setColumns(10);
		txtBookStatus.setBounds(105, 278, 103, 20);
		BorrowBook.add(txtBookStatus);
		
		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Borrow Date:");
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1.setBounds(450, 280, 93, 14);
		BorrowBook.add(lblNewLabel_2_1_1_1_2_1);
		
		JDateChooser dateBorrow = new JDateChooser();
		dateBorrow.setBounds(545, 280, 103, 20);
		BorrowBook.add(dateBorrow);
		
		JLabel lblNewLabel_2_1_1_1_2_1_1 = new JLabel("Return Date:");
		lblNewLabel_2_1_1_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1_1.setBounds(450, 310, 93, 14);
		BorrowBook.add(lblNewLabel_2_1_1_1_2_1_1);
		
		JDateChooser dateReturn = new JDateChooser();
		dateReturn.setBounds(545, 310, 103, 20);
		BorrowBook.add(dateReturn);
		
		JButton btnBorrow = new JButton("Borrow Book");
		btnBorrow.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			public void actionPerformed(ActionEvent e) {
				if(txtBookID.getText().trim().isEmpty() && txtBookName.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Select a Book Before Borrowing");
				}else {
					//FOR BOOK UPDATE
					try {	
						String sql ="SELECT BookID AS bookid, "
								+ "BookName AS bookname, Page AS page, Author AS author, "
								+ "BookType AS booktype, ISBN AS isbn, BookStatus AS bookstatus FROM Books "
								+ "WHERE BookID = :bid ";
						     Query query = session.createSQLQuery(sql);
						     int bookid = Integer.parseInt(txtBookID.getText());
						     query.setParameter("bid", bookid);
						     query.setResultTransformer(Transformers.aliasToBean(Books.class));
						     Books books = (Books)query.uniqueResult();
						//FOR STATUS SET
						 	String sql1 ="SELECT StatusID AS statusid, "
									+ "Status AS status FROM Status "
									+ "WHERE StatusID = :id ";
							     Query query1 = session.createSQLQuery(sql1);
							     query1.setParameter("id", 2);
							     query1.setResultTransformer(Transformers.aliasToBean(BookStatus.class));
							     List<BookStatus> statusList = query1.list();
							     String status = null;
							     for(BookStatus stat : statusList) {
							    	 status = stat.getStatus();
							     }
					    //BookInfo
						String bookname = txtBookName.getText();
						String booktype = txtType.getText();
						String bookstatus = txtBookStatus.getText();
						//BorrowerInfo
						int studentid = Integer.parseInt(txtStudentID.getText());
						String fullname = txtFullname.getText();
						int contact = Integer.parseInt(txtContact.getText());
						String borrowDate = dateFormat.format(dateBorrow.getDate());
						String returnDate = dateFormat.format(dateReturn.getDate());
						//
						tx = session.beginTransaction();
						Books book = new Books();
						book.setBookname(bookname);
						book.setBooktype(booktype);
						books.setBookstatus(status);	
						//
						Set<Borrowers> borrow = new HashSet<>();
						Borrowers borrow1 = new Borrowers();
						borrow1.setStudentID(studentid);
						borrow1.setFullname(fullname);
						borrow1.setBookid(bookid);
						borrow1.setBookname(bookname);
						borrow1.setBookstatus(bookstatus);
						borrow1.setBorrowdate(borrowDate);
						borrow1.setReturndate(returnDate);
						borrow1.setBookstatus(status);	
						borrow.add(borrow1);
						book.setBorrow(borrow);
			             Integer id = (Integer)session.save(borrow1);
			            session.update(books);
			            tx.commit();
			            JOptionPane.showMessageDialog(null, "Borrow Successfull");
			            System.out.println(status);
			            refresh();
			            tblRefresh();
					}catch(Exception ex){
						if(tx != null) {
							tx.rollback();
						}
						ex.printStackTrace();
					}
				}
			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBorrow.setBounds(10, 309, 103, 33);
		BorrowBook.add(btnBorrow);

		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard home  = new Dashboard();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBack.setBounds(699, 11, 103, 33);
		contentPane.add(btnBack);
	}
}
