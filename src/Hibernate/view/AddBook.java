package Hibernate.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.BookType;
import Hibernate.model.Books;
import Hibernate.model.Strand;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTable tblBookList;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JTextField txtBookPages;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtBookType;
	private JTextField txtBookStatus;
	static JComboBox cboBookType = new JComboBox();

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
					AddBook frame = new AddBook();
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
	
	//METHODS FOR EVENTS
	//FOR CLEAR TEXTBOX AFTER INSERT
	public void clearText() {
	     txtBookID.setText(null);
		 txtBookName.setText(null);
		 txtBookPages.setText(null);
		 txtAuthor.setText(null);
		 txtISBN.setText(null);
		 txtBookType.setText(null);
		 txtBookStatus.setText(null);
	}
	
	//FOR REFRESHING TABLE AFTER INSERT
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
	public static void cboType() {
		Session session = Database.getSession();
		try {
			String sql ="SELECT BookTypeID AS booktypeid,"
					+ "BookType AS booktype FROM BookType";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(BookType.class));
			List<BookType> StandList = query.list();
			for(BookType strand : StandList) {
				cboBookType.addItem(strand.getBooktype());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard home = new Dashboard();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(538, 11, 89, 33);
		contentPane.add(btnBack);
		
		JLabel lblAddBookPage = new JLabel("Add Book Page:");
		lblAddBookPage.setFont(new Font("Arial", Font.BOLD, 15));
		lblAddBookPage.setBounds(10, 20, 133, 14);
		contentPane.add(lblAddBookPage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 617, 139);
		contentPane.add(scrollPane);
		
		tblBookList = new JTable();
		tblBookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(tblBookList);
		tblBookList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "Page", "Author", "ISBN", "BookType", "BookStatus"
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
		
		JLabel lblNewLabel_1 = new JLabel("Book Details:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 218, 122, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 258, 84, 14);
		contentPane.add(lblNewLabel_2);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setColumns(10);
		txtBookID.setBounds(104, 258, 96, 20);
		contentPane.add(txtBookID);
		
		txtBookName = new JTextField();
		txtBookName.setColumns(10);
		txtBookName.setBounds(104, 289, 151, 20);
		contentPane.add(txtBookName);
		
		JLabel lblNewLabel_2_1 = new JLabel("BookName:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 289, 84, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Pages:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(10, 316, 84, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txtBookPages = new JTextField();
		txtBookPages.setColumns(10);
		txtBookPages.setBounds(104, 316, 151, 20);
		contentPane.add(txtBookPages);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(104, 343, 151, 20);
		contentPane.add(txtAuthor);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Author:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 343, 84, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("ISBN:");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(362, 256, 84, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(456, 254, 133, 20);
		contentPane.add(txtISBN);
		
		txtBookType = new JTextField();
		txtBookType.setColumns(10);
		txtBookType.setBounds(456, 343, 133, 20);
		contentPane.add(txtBookType);
		
		txtBookStatus = new JTextField();
		txtBookStatus.setEditable(false);
		txtBookStatus.setColumns(10);
		txtBookStatus.setBounds(456, 289, 133, 20);
		contentPane.add(txtBookStatus);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Book Status:");
		lblNewLabel_2_1_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_4.setBounds(362, 295, 84, 14);
		contentPane.add(lblNewLabel_2_1_4);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("BookType:");
		lblNewLabel_2_1_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_3.setBounds(362, 320, 84, 14);
		contentPane.add(lblNewLabel_2_1_3);
		
		JButton btnAddBook = new JButton("Add");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				 if(txtBookName.getText().trim().isEmpty()) {
					 JOptionPane.showMessageDialog(null, "Please Fill in the Required Fields Before Adding");
				 }else {
					try {
						String sql ="SELECT StatusID AS statusid, "
								+ "Status AS status FROM Status "
								+ "WHERE StatusID = :id ";
						     Query query = session.createSQLQuery(sql);
						     query.setParameter("id", 1);
						     query.setResultTransformer(Transformers.aliasToBean(BookStatus.class));
						     List<BookStatus> statusList = query.list();
						     String status = null;
						     for(BookStatus stat : statusList) {
						    	 status = stat.getStatus();
						     }
						tx = session.beginTransaction();
						String BookName = txtBookName.getText();
						int Page = Integer.parseInt(txtBookPages.getText());
						String Author = txtAuthor.getText();
						int ISBN = Integer.parseInt(txtISBN.getText());
						String BookType = txtBookType.getText();
						String BookStat = txtBookStatus.getText();
						//
						Books books = new Books();
						BookStatus bookstata = new BookStatus();
						books.setBookname(BookName);
						books.setPage(Page);
						books.setAuthor(Author);
						books.setIsbn(ISBN);
						books.setBooktype(BookType);
						books.setBookstatus(status);
						Integer id = (Integer)session.save(books);
						tx.commit();
						JOptionPane.showMessageDialog(null, "Book Added");
						tblRefresh();
						clearText();
					}catch(Exception ex)
					{
						if (tx != null) {
							tx.rollback();
						}
						ex.printStackTrace();
					}
				 }	 
			}
		});
		btnAddBook.setBounds(104, 374, 109, 33);
		contentPane.add(btnAddBook);
		
		cboBookType.addActionListener(new ActionListener() {
			Transaction tx = null;
			public void actionPerformed(ActionEvent e) {
				try {
					Session session = Database.getSession();
					String sql ="SELECT BookTypeID AS booktypeid, "
							+ "BookType AS booktype FROM BookType";
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(Transformers.aliasToBean(BookType.class));
					List<BookType> StandList = query.list();
					for(BookType strand : StandList) {
						cboBookType.addItem(strand.getBooktype());
					}
					String selectedCourse = cboBookType.getSelectedItem().toString();
					txtBookType.setText(selectedCourse);
				}catch(Exception ex) {
					
				}
			}
		});
		cboBookType.setBounds(456, 313, 133, 22);
		contentPane.add(cboBookType);
	}
}
