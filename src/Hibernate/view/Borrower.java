package Hibernate.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Borrower extends JFrame {

	private JPanel contentPane;
	private JTable tblBorrowers;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JTextField txtBookStatus;
	private JTextField txtFullname;
	private JTextField txtStudentID;

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
					Borrower frame = new Borrower();
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
	
	//
	public void tblRefresh() {
		Session session1 = Database.getSession();
		try {
			String sql ="SELECT BorrowerID AS borrowerid, "
					+ "StudentID AS studentID, Fullname AS fullname, BookID AS bookid, BookName AS bookname, BorrowDate AS borrowdate, "
					+ "ReturnDate AS returndate, BookStatus AS bookstatus FROM Borrower ";
			DefaultTableModel model = (DefaultTableModel)tblBorrowers.getModel();
			model.setRowCount(0);
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Borrowers.class));
			List<Borrowers> borrowerList = query.list();
			for(Borrowers borrow: borrowerList) {
				model.addRow(new Object[] {
						borrow.getStudentID(),
						borrow.getFullname(),
						borrow.getBookid(),
						borrow.getBookname(),
						borrow.getBorrowdate(),
						borrow.getReturndate(),
						borrow.getBookstatus()
				});
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public Borrower() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Borrower List Page:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 198, 18);
		contentPane.add(lblNewLabel);
		
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
		btnBack.setBounds(722, 10, 103, 33);
		contentPane.add(btnBack);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 40, 815, 438);
		contentPane.add(tabbedPane);
		
		JPanel Borrower = new JPanel();
		tabbedPane.addTab("BorrowerList", null, Borrower, null);
		Borrower.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("BorrowerList");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 11, 103, 14);
		Borrower.add(lblNewLabel_4);
		
		JDateChooser dateBorrowDate = new JDateChooser();
		dateBorrowDate.setBounds(537, 267, 103, 20);
		Borrower.add(dateBorrowDate);
		
		JDateChooser dateBorrowReturn = new JDateChooser();
		dateBorrowReturn.setBounds(537, 304, 103, 20);
		Borrower.add(dateBorrowReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 790, 186);
		Borrower.add(scrollPane);
		
		tblBorrowers = new JTable();
		tblBorrowers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 DefaultTableModel model = (DefaultTableModel)tblBorrowers.getModel();
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      try {
			    	  txtStudentID.setText(model.getValueAt(row, 0).toString());
			    	  txtFullname.setText(model.getValueAt(row, 1).toString());
			    	  txtBookID.setText(model.getValueAt(row, 2).toString());
				      txtBookName.setText(model.getValueAt(row, 3).toString());
				      Date dateFormat1 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 4));
				      Date dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 5));
				      txtBookStatus.setText(model.getValueAt(row, 6).toString());
				      dateBorrowDate.setDate(dateFormat1);
				      dateBorrowReturn.setDate(dateFormat);
			      }catch(Exception ex) {
			    	  ex.printStackTrace();
			      }  
			}
		});
		scrollPane.setViewportView(tblBorrowers);
		tblBorrowers.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, "", "", null},
			},
			new String[] {
				"StudentID", "Fullname", "BookID", "BookName", "BorrowDate", "ReturnDate", "BookStatus"
			}
		));
		//SQL TO JTABLE
				Session session1 = Database.getSession();
				try {
					String sql ="SELECT BorrowerID AS borrowerid, "
							+ "StudentID AS studentID, Fullname AS fullname, BookID AS bookid, BookName AS bookname, BorrowDate AS borrowdate, "
							+ "ReturnDate AS returndate, BookStatus AS bookstatus FROM Borrower ";
					DefaultTableModel model = (DefaultTableModel)tblBorrowers.getModel();
					model.setRowCount(0);
					Query query = session1.createSQLQuery(sql);
					query.setResultTransformer(Transformers.aliasToBean(Borrowers.class));
					List<Borrowers> borrowerList = query.list();
					for(Borrowers borrow: borrowerList) {
						model.addRow(new Object[] {
								borrow.getStudentID(),
								borrow.getFullname(),
								borrow.getBookid(),
								borrow.getBookname(),
								borrow.getBorrowdate(),
								borrow.getReturndate(),
								borrow.getBookstatus()
						});
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		
		JLabel lblNewLabel_4_1 = new JLabel("Book Info:");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(10, 245, 103, 14);
		Borrower.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5 = new JLabel("BookID:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 273, 71, 14);
		Borrower.add(lblNewLabel_5);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setColumns(10);
		txtBookID.setBounds(101, 270, 79, 20);
		Borrower.add(txtBookID);
		
		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setColumns(10);
		txtBookName.setBounds(101, 301, 79, 20);
		Borrower.add(txtBookName);
		
		JLabel lblNewLabel_5_1 = new JLabel("BookName:");
		lblNewLabel_5_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(10, 304, 86, 14);
		Borrower.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Book Status:");
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5_1_1.setBounds(10, 339, 86, 14);
		Borrower.add(lblNewLabel_5_1_1);
		
		txtBookStatus = new JTextField();
		txtBookStatus.setEditable(false);
		txtBookStatus.setColumns(10);
		txtBookStatus.setBounds(101, 336, 79, 20);
		Borrower.add(txtBookStatus);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    				    //BookInfo
    					String bookname = txtBookName.getText();
    					String bookstatus = txtBookStatus.getText();
    					//BorrowerInfo
    					int studentid = Integer.parseInt(txtStudentID.getText());
    					String fullname = txtFullname.getText();
    					String borrowDate = dateFormat.format(dateBorrowDate.getDate());
    					String returnDate = dateFormat.format(dateBorrowReturn.getDate());
    					//
    					tx = session.beginTransaction();
    					Books book = new Books();
    					book.setBookname(bookname);
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
    		          Integer id = (Integer)session.save(borrow1);
    		            session.update(books);
    		            tx.commit();
    		            JOptionPane.showMessageDialog(null, "Book Returned");
    		            System.out.println(status);
    				}catch(Exception ex){
    					if(tx != null) {
    						tx.rollback();
    					}
    					ex.printStackTrace();
    				}
			}
		});
		btnReturnBook.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnReturnBook.setBounds(256, 336, 103, 33);
		Borrower.add(btnReturnBook);
		
		JButton btnExtend = new JButton("Extend");
		btnExtend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnExtend.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExtend.setBounds(373, 336, 103, 33);
		Borrower.add(btnExtend);
		
		txtFullname = new JTextField();
		txtFullname.setEditable(false);
		txtFullname.setColumns(10);
		txtFullname.setBounds(337, 301, 79, 20);
		Borrower.add(txtFullname);
		
		JLabel lblNewLabel_5_3 = new JLabel("Fullname:");
		lblNewLabel_5_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5_3.setBounds(256, 304, 71, 14);
		Borrower.add(lblNewLabel_5_3);
		
		txtStudentID = new JTextField();
		txtStudentID.setEditable(false);
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(337, 267, 79, 20);
		Borrower.add(txtStudentID);
		
		JLabel lblNewLabel_5_2 = new JLabel("Student ID:");
		lblNewLabel_5_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(256, 270, 71, 14);
		Borrower.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Student Info:");
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(256, 246, 103, 14);
		Borrower.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_2_1_1_1_2_1_1_1 = new JLabel("Borrow Date:");
		lblNewLabel_2_1_1_1_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1_1_1.setBounds(442, 267, 93, 14);
		Borrower.add(lblNewLabel_2_1_1_1_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_2_1_1_2 = new JLabel("Return Date:");
		lblNewLabel_2_1_1_1_2_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1_1_2.setBounds(442, 304, 93, 14);
		Borrower.add(lblNewLabel_2_1_1_1_2_1_1_2);
	}
}
