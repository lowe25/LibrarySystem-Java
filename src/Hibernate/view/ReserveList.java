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
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.Books;
import Hibernate.model.Borrowers;
import Hibernate.model.Reserves;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReserveList extends JFrame {

	private JPanel contentPane;
	private JTable tblReserves;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JTextField txtBookType;
	private JTextField txtBookStatus;
	private JTextField txtStudentID;
	private JTextField txtFullname;
	private JTextField txtContact;
	private JTextField txtResID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AddStudent view = new AddStudent();
		AddBook ab = new AddBook();
		ab.cboType();
		view.comboCourse();
		view.comboStrand();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveList frame = new ReserveList();
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
	
	public void refreshReserve() {
		Session session1 = Database.getSession();
		try {
			String sql=" SELECT ReserveBookID AS resevebookid, "
					+ "BookID AS bookid, BookName AS bookname, BookType AS booktype, BookStatus AS bookstatus, StudentID AS studentid, "
					+ "Fullname AS fullname, Contact AS contact FROM ReserveBooks";
			DefaultTableModel model = (DefaultTableModel)tblReserves.getModel();
			model.setRowCount(0);
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Reserves.class));
			List<Reserves> reserve = query.list();
			for(Reserves res : reserve) {
				model.addRow(new Object[] {
						res.getBookid(),
						res.getBookname(),
						res.getBooktype(),
						res.getBookstatus(),
						res.getStudentid(),
						res.getFullname(),
						res.getContact()
				});
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public ReserveList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReserveBooksPage = new JLabel("Reserve Books Page:");
		lblReserveBooksPage.setFont(new Font("Arial", Font.BOLD, 15));
		lblReserveBooksPage.setBounds(10, 11, 176, 14);
		contentPane.add(lblReserveBooksPage);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 744, 458);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Reserve Books", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblReserveBookList = new JLabel("Reserve Books:");
		lblReserveBookList.setFont(new Font("Arial", Font.BOLD, 15));
		lblReserveBookList.setBounds(10, 11, 176, 14);
		panel.add(lblReserveBookList);
		
		JDateChooser dateBorrow = new JDateChooser();
		dateBorrow.setBounds(545, 327, 103, 20);
		panel.add(dateBorrow);
		
		JDateChooser dateReturn = new JDateChooser();
		dateReturn.setBounds(545, 357, 103, 20);
		panel.add(dateReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 719, 128);
		panel.add(scrollPane);
		
		tblReserves = new JTable();
		tblReserves.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 DefaultTableModel model = (DefaultTableModel)tblReserves.getModel();
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      txtBookID.setText(model.getValueAt(row, 0).toString());
			      txtBookName.setText(model.getValueAt(row, 1).toString());
			      txtBookType.setText(model.getValueAt(row, 2).toString());
			      txtBookStatus.setText(model.getValueAt(row, 3).toString());
			      txtStudentID.setText(model.getValueAt(row, 4).toString());
			      txtFullname.setText(model.getValueAt(row, 5).toString());
			      txtContact.setText(model.getValueAt(row, 6).toString());   
			      txtResID.setText(model.getValueAt(row, 7).toString());
			}
		});
		scrollPane.setViewportView(tblReserves);
		tblReserves.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "BookType", "BookStatus", "StudentID", "Fullname", "Contact", "ReserveID"
			}
		));
		Session session1 = Database.getSession();
		try {
			String sql=" SELECT ReserveBookID AS resevebookid, "
					+ "BookID AS bookid, BookName AS bookname, BookType AS booktype, BookStatus AS bookstatus, StudentID AS studentid, "
					+ "Fullname AS fullname, Contact AS contact FROM ReserveBooks";
			DefaultTableModel model = (DefaultTableModel)tblReserves.getModel();
			model.setRowCount(0);
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Reserves.class));
			List<Reserves> reserve = query.list();
			for(Reserves res : reserve) {
				model.addRow(new Object[] {
						res.getBookid(),
						res.getBookname(),
						res.getBooktype(),
						res.getBookstatus(),
						res.getStudentid(),
						res.getFullname(),
						res.getContact(),
						res.getResevebookid()
				});
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		JButton btnBorrowBook = new JButton("Borrow Book");
		btnBorrowBook.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			public void actionPerformed(ActionEvent e) {
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
					String booktype = txtBookType.getText();
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
					//book.setBorrow(borrow);
		          Integer id = (Integer)session.save(borrow1);
		            session.update(books);
		            tx.commit();
		            JOptionPane.showMessageDialog(null, "Borrow Successfull");
		            System.out.println(status);
				}catch(Exception ex){
					if(tx != null) {
						tx.rollback();
					}
					ex.printStackTrace();
				}
			}
		});
		btnBorrowBook.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBorrowBook.setBounds(10, 344, 143, 33);
		panel.add(btnBorrowBook);
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			public void actionPerformed(ActionEvent e) {
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
						     query1.setParameter("id", 3);
						     query1.setResultTransformer(Transformers.aliasToBean(BookStatus.class));
						     List<BookStatus> statusList = query1.list();
						     String status = null;
						     for(BookStatus stat : statusList) {
						    	 status = stat.getStatus();
						     }
						     String sql2=" SELECT ReserveBookID AS resevebookid, "
										+ "BookID AS bookid, BookName AS bookname, BookType AS booktype, BookStatus AS bookstatus, StudentID AS studentid, "
										+ "Fullname AS fullname, Contact AS contact FROM ReserveBooks "
										+ "WHERE ReserveBookID = :rsi ";
						     int ResID = Integer.parseInt(txtResID.getText());
						      Query query2 = session.createSQLQuery(sql2);
						      query2.setParameter("rsi", ResID);
						      query2.setResultTransformer(Transformers.aliasToBean(Reserves.class));
						      Reserves re = (Reserves)query2.uniqueResult();
				    //BookInfo
					String bookname = txtBookName.getText();
					String booktype = txtBookType.getText();
					String bookstatus = txtBookStatus.getText();
					//BorrowerInfo
					int studentid = Integer.parseInt(txtStudentID.getText());
					String fullname = txtFullname.getText();
					int contact = Integer.parseInt(txtContact.getText());
					//String borrowDate = dateFormat.format(dateBorrow.getDate());
					//String returnDate = dateFormat.format(dateReturn.getDate());
					//
					tx = session.beginTransaction();
					Books book = new Books();
					book.setBookname(bookname);
					book.setBooktype(booktype);
					books.setBookstatus(status);	
					//
					Reserves res = new Reserves();
					res.setBookid(bookid);
					res.setBookname(bookname);
					res.setBooktype(booktype);
					re.setBookstatus(status);
					res.setStudentid(studentid);
					res.setFullname(fullname);
					res.setContact(contact);
		         // Integer id = (Integer)session.save(borrow1);
		            //session.update(books);
		            session.update(books);
		            session.update(re);
		            tx.commit();
		            JOptionPane.showMessageDialog(null, "Book Reserve Cancelled");
		            refreshReserve();
		            System.out.println(status);
				}catch(Exception ex){
					if(tx != null) {
						tx.rollback();
					}
					ex.printStackTrace();
				}
			}
		});
		btnCancelReservation.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancelReservation.setBounds(175, 344, 143, 33);
		panel.add(btnCancelReservation);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("* Fill up If Borrow");
		lblNewLabel_2_2_3.setForeground(Color.RED);
		lblNewLabel_2_2_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_3.setBounds(450, 302, 143, 14);
		panel.add(lblNewLabel_2_2_3);
		
		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("*Borrow Date:");
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1.setBounds(450, 327, 93, 14);
		panel.add(lblNewLabel_2_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1_2_1_1 = new JLabel("*Return Date:");
		lblNewLabel_2_1_1_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1_1.setBounds(450, 357, 93, 14);
		panel.add(lblNewLabel_2_1_1_1_2_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Book Details:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(20, 179, 123, 17);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("BookID:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(20, 229, 92, 14);
		panel.add(lblNewLabel_2_2);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setColumns(10);
		txtBookID.setBounds(115, 227, 103, 20);
		panel.add(txtBookID);
		
		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setColumns(10);
		txtBookName.setBounds(115, 254, 177, 20);
		panel.add(txtBookName);
		
		txtBookType = new JTextField();
		txtBookType.setEditable(false);
		txtBookType.setColumns(10);
		txtBookType.setBounds(115, 285, 177, 20);
		panel.add(txtBookType);
		
		txtBookStatus = new JTextField();
		txtBookStatus.setEditable(false);
		txtBookStatus.setColumns(10);
		txtBookStatus.setBounds(115, 313, 103, 20);
		panel.add(txtBookStatus);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("BookStatus:");
		lblNewLabel_2_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2_1.setBounds(20, 315, 92, 14);
		panel.add(lblNewLabel_2_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("BookType:");
		lblNewLabel_2_2_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_2.setBounds(20, 287, 92, 14);
		panel.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("BookName:");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(20, 256, 92, 14);
		panel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Borrower Details:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(460, 179, 142, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("StudentID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(460, 212, 92, 14);
		panel.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setEditable(false);
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(545, 210, 92, 20);
		panel.add(txtStudentID);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fullname:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(460, 239, 92, 14);
		panel.add(lblNewLabel_2_1);
		
		txtFullname = new JTextField();
		txtFullname.setEditable(false);
		txtFullname.setColumns(10);
		txtFullname.setBounds(545, 237, 129, 20);
		panel.add(txtFullname);
		
		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setColumns(10);
		txtContact.setBounds(545, 268, 129, 20);
		panel.add(txtContact);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Contact:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(460, 270, 92, 14);
		panel.add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_2_2_4 = new JLabel("ReserveID:");
		lblNewLabel_2_2_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_2_4.setBounds(20, 200, 92, 14);
		panel.add(lblNewLabel_2_2_4);
		
		txtResID = new JTextField();
		txtResID.setEditable(false);
		txtResID.setColumns(10);
		txtResID.setBounds(115, 198, 103, 20);
		panel.add(txtResID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard home = new Dashboard();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBack.setBounds(651, 11, 103, 33);
		contentPane.add(btnBack);
	}
}
