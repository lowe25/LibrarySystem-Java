package Hibernate.view;

import java.awt.BorderLayout;




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;



import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.Books;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputFilter.Status;
import java.util.List;

public class BookList extends JFrame {

	private JPanel contentPane;
	private JTable tblBookList;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JTextField txtPages;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtBookType;
	private JTextField txtStatus;

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
					BookList frame = new BookList();
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
		 txtPages.setText(null);
		 txtAuthor.setText(null);
		 txtISBN.setText(null);
		 txtBookType.setText(null);
		 txtStatus.setText(null);
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
	
	/*==================================================*/
	public BookList() {
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BookList Page:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 133, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 579, 126);
		contentPane.add(scrollPane);
		
		tblBookList = new JTable();
		tblBookList.addMouseListener(new MouseAdapter() {
			@Override
			//TABLE TO TEXTBOX
			public void mouseClicked(MouseEvent e) {
				  DefaultTableModel model = (DefaultTableModel)tblBookList.getModel();
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      txtBookID.setText(model.getValueAt(row, 0).toString());
			      txtBookName.setText(model.getValueAt(row, 1).toString());
			      txtPages.setText(model.getValueAt(row, 2).toString());
			      txtAuthor.setText(model.getValueAt(row, 3).toString());
			     txtISBN.setText(model.getValueAt(row, 4).toString());
			      txtBookType.setText(model.getValueAt(row, 5).toString());
			      txtStatus.setText(model.getValueAt(row, 6).toString());
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
		
		JLabel lblNewLabel_1 = new JLabel("Book Details:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 200, 122, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 240, 84, 14);
		contentPane.add(lblNewLabel_2);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setBounds(104, 240, 96, 20);
		contentPane.add(txtBookID);
		txtBookID.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("BookName:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 271, 84, 14);
		contentPane.add(lblNewLabel_2_1);
		
		txtBookName = new JTextField();
		txtBookName.setColumns(10);
		txtBookName.setBounds(104, 271, 151, 20);
		contentPane.add(txtBookName);
		
		JLabel lblNewLabel_2_2 = new JLabel("Pages:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(10, 298, 84, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txtPages = new JTextField();
		txtPages.setColumns(10);
		txtPages.setBounds(104, 298, 151, 20);
		contentPane.add(txtPages);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Author:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 325, 84, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(104, 325, 151, 20);
		contentPane.add(txtAuthor);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("ISBN:");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(362, 238, 84, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(456, 236, 133, 20);
		contentPane.add(txtISBN);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("BookType:");
		lblNewLabel_2_1_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_3.setBounds(362, 269, 84, 14);
		contentPane.add(lblNewLabel_2_1_3);
		
		txtBookType = new JTextField();
		txtBookType.setColumns(10);
		txtBookType.setBounds(456, 267, 133, 20);
		contentPane.add(txtBookType);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Book Status:");
		lblNewLabel_2_1_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1_4.setBounds(362, 298, 84, 14);
		contentPane.add(lblNewLabel_2_1_4);
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(456, 292, 133, 20);
		contentPane.add(txtStatus);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			public void actionPerformed(ActionEvent e) {
				int BookID = Integer.parseInt(txtBookID.getText());
				String sql ="SELECT BookID AS bookid, "
						+ "BookName AS bookname, Page AS page, "
						+ "Author AS author, ISBN AS isbn, BookType AS booktype, BookStatus AS bookstatus FROM Books "
						+ "WHERE id = :bid";
				Query query = session.createSQLQuery(sql);
				query.setParameter("bid",BookID);
				query.setResultTransformer(Transformers.aliasToBean(Books.class));
				Books book = (Books)query.uniqueResult();
				//
				String BookName = txtBookName.getText();
				int Page = Integer.parseInt(txtPages.getText());
				String Author = txtAuthor.getText();
				int ISBN = Integer.parseInt(txtISBN.getText());
				String BookType = txtBookType.getText();
				String BookStat = txtStatus.getText();
				tx = session.beginTransaction();
				book.setBookname(BookName);
				book.setPage(Page);
				book.setAuthor(Author);
				book.setIsbn(ISBN);
				book.setBooktype(BookType);
				book.setBookstatus(BookStat);
				session.update(book);
				tx.commit();
				JOptionPane.showMessageDialog(null, "Book With ID " + book.getBookid() + "Is Updated");
				tblRefresh();
				clearText();
			}
		});
		btnEdit.setBounds(152, 356, 131, 33);
		contentPane.add(btnEdit);
		
		JButton btnDeleteBook = new JButton("Delete");
		btnDeleteBook.addActionListener(new ActionListener() {
			Session session = Database.getSession();
			Transaction tx = null;
			public void actionPerformed(ActionEvent e) {
				try {
					tx = session.beginTransaction();
					int BookID = Integer.parseInt(txtBookID.getText());
					String sql ="SELECT BookID AS bookid, "
							+ "BookName AS bookname, Page AS page, "
							+ "Author AS author, ISBN AS isbn, BookType AS booktype, BookStatus AS bookstatus FROM Books "
							+ "WHERE id = :bid";
					Query query = session.createSQLQuery(sql);
					query.setParameter("bid",BookID);
					query.setResultTransformer(Transformers.aliasToBean(Books.class));
					Books book = (Books)query.uniqueResult();
					System.out.println("id to delete " + book.getBookid());
					session.delete(book);
					tx.commit();
					JOptionPane.showMessageDialog(null, "ID with " + book.getBookid() + " Deleted");
					tblRefresh();
					clearText();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDeleteBook.setBounds(315, 356, 131, 33);
		contentPane.add(btnDeleteBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard home = new Dashboard();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(500, 8, 89, 33);
		contentPane.add(btnBack);
	}
}
