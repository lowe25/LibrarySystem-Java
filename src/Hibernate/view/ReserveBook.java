package Hibernate.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.Books;
import Hibernate.model.Borrowers;
import Hibernate.model.Reserves;
import Hibernate.model.Students;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class ReserveBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookStatus;
	private JTextField txtBookType;
	private JTextField txtBookName;
	private JTextField txtBookID;
	private JTextField txtStudentID;
	private JTextField txtFullname;
	private JTextField txtContact;
	private JTable tblBookList;

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
					ReserveBook frame = new ReserveBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReserveBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReserveBooksPage = new JLabel("Reserve Books Page:");
		lblReserveBooksPage.setFont(new Font("Arial", Font.BOLD, 15));
		lblReserveBooksPage.setBounds(10, 11, 176, 14);
		contentPane.add(lblReserveBooksPage);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 741, 449);
		contentPane.add(tabbedPane);
		
		JPanel ReservesList = new JPanel();
		tabbedPane.addTab("ReservesList", null, ReservesList, null);
		ReservesList.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Book Details:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 203, 123, 17);
		ReservesList.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("BookID:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 235, 92, 14);
		ReservesList.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("BookName:");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(10, 262, 92, 14);
		ReservesList.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("BookType:");
		lblNewLabel_2_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2.setBounds(10, 293, 92, 14);
		ReservesList.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("BookStatus:");
		lblNewLabel_2_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2_1.setBounds(10, 321, 92, 14);
		ReservesList.add(lblNewLabel_2_2_2_1);
		
		txtBookStatus = new JTextField();
		txtBookStatus.setEditable(false);
		txtBookStatus.setColumns(10);
		txtBookStatus.setBounds(105, 319, 103, 20);
		ReservesList.add(txtBookStatus);
		
		txtBookType = new JTextField();
		txtBookType.setEditable(false);
		txtBookType.setColumns(10);
		txtBookType.setBounds(105, 291, 177, 20);
		ReservesList.add(txtBookType);
		
		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setColumns(10);
		txtBookName.setBounds(105, 260, 177, 20);
		ReservesList.add(txtBookName);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setColumns(10);
		txtBookID.setBounds(105, 233, 103, 20);
		ReservesList.add(txtBookID);
		
		JLabel lblNewLabel_2 = new JLabel("StudentID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(390, 235, 92, 14);
		ReservesList.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(475, 233, 92, 20);
		ReservesList.add(txtStudentID);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fullname:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(390, 262, 92, 14);
		ReservesList.add(lblNewLabel_2_1);
		
		txtFullname = new JTextField();
		txtFullname.setEditable(false);
		txtFullname.setColumns(10);
		txtFullname.setBounds(475, 260, 129, 20);
		ReservesList.add(txtFullname);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Contact:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(390, 293, 92, 14);
		ReservesList.add(lblNewLabel_2_1_1_1_1);
		
		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setColumns(10);
		txtContact.setBounds(475, 291, 129, 20);
		ReservesList.add(txtContact);
		
		JButton btnBorrow = new JButton("Reserve Book");
		btnBorrow.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			public void actionPerformed(ActionEvent e) {
				
				if(txtBookID.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Select a Book to Reserve");
				}else {
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
		            	//
		            	String sql1 ="SELECT StatusID AS statusid, "
								+ "Status AS status FROM Status "
								+ "WHERE StatusID = :id ";
						     Query query1 = session.createSQLQuery(sql1);
						     query1.setParameter("id", 4);
						     query1.setResultTransformer(Transformers.aliasToBean(BookStatus.class));
						     List<BookStatus> statusList = query1.list();
						     String status = null;
						     for(BookStatus stat : statusList) {
						    	 status = stat.getStatus();
						     }
						//
						 	tx = session.beginTransaction();
						 	//int bookid = Integer.parseInt(txtBookID.getText());
						 	String bookname = txtBookName.getText();
						 	String booktype = txtBookType.getText();
						 	String bookstatus = txtBookStatus.getText();
						 	int studentid = Integer.parseInt(txtStudentID.getText());
						 	String fullname = txtFullname.getText();
						 	int contact = Integer.parseInt(txtContact.getText());
						 	//
						 	Reserves res = new Reserves();
						 	res.setBookid(bookid);
						 	res.setBookname(bookname);
						 	res.setBooktype(booktype);
						 	res.setStudentid(studentid);
						 	res.setFullname(fullname);
						 	res.setContact(contact);
						 	books.setBookstatus(status);
						 	res.setBookstatus(status);
						 	Integer id = (Integer)session.save(res);
						 	session.update(books);
						 	tx.commit();
						 	JOptionPane.showMessageDialog(null, "Book Reserved");
				}catch(Exception ex) {
					
				}
				}
			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBorrow.setBounds(10, 357, 143, 33);
		ReservesList.add(btnBorrow);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Student Details:");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(390, 205, 174, 17);
		ReservesList.add(lblNewLabel_3_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 719, 161);
		ReservesList.add(scrollPane);
		
		tblBookList = new JTable();
		tblBookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				 DefaultTableModel model = (DefaultTableModel)tblBookList.getModel();
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      txtBookID.setText(model.getValueAt(row, 0).toString());
			      txtBookName.setText(model.getValueAt(row, 1).toString());
			      txtBookType.setText(model.getValueAt(row, 5).toString());
			      txtBookStatus.setText(model.getValueAt(row, 6).toString());
			}
		});
		scrollPane.setViewportView(tblBookList);
		tblBookList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "Page", "Author", "ISBN", "BookType", "Status"
			}
		));
		
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
		btnSearch.setBounds(577, 232, 89, 23);
		ReservesList.add(btnSearch);
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard home = new Dashboard();
				home.setLocationRelativeTo(null);
				home.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBack.setBounds(635, 11, 103, 33);
		contentPane.add(btnBack);
	}
}
