package Hibernate.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;

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
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewBooks = new JButton("Book List");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookList booklist = new BookList();
				booklist.setVisible(true);
				booklist.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnViewBooks.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewBooks.setBounds(436, 85, 179, 42);
		contentPane.add(btnViewBooks);
		
		JLabel lblNewLabel = new JLabel("Simple Library System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(235, 11, 191, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnViewStudents = new JButton("Student List");
		btnViewStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudents view = new ViewStudents();
				view.setVisible(true);
				view.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnViewStudents.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewStudents.setBounds(435, 142, 180, 42);
		contentPane.add(btnViewStudents);
		
		JButton btnBorrowbook = new JButton("Borrow Book");
		btnBorrowbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowBook borrow = new BorrowBook();
				borrow.setVisible(true);
				borrow.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBorrowbook.setFont(new Font("Arial", Font.BOLD, 12));
		btnBorrowbook.setBounds(10, 195, 179, 42);
		contentPane.add(btnBorrowbook);
		
		JButton btnReservebook = new JButton("Reserve Book");
		btnReservebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveBook resbook = new ReserveBook();
				resbook.setVisible(true);
				resbook.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnReservebook.setFont(new Font("Arial", Font.BOLD, 12));
		btnReservebook.setBounds(10, 250, 179, 42);
		contentPane.add(btnReservebook);
		
		JButton btnBorrowerlist = new JButton("Borrower List");
		btnBorrowerlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrower bor = new Borrower();
				bor.setVisible(true);
				bor.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBorrowerlist.setFont(new Font("Arial", Font.BOLD, 12));
		btnBorrowerlist.setBounds(435, 195, 180, 42);
		contentPane.add(btnBorrowerlist);
		
		JButton btnViewStudents_1_1 = new JButton("Reserve List");
		btnViewStudents_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveList res = new ReserveList();
				res.setVisible(true);
				res.setLocationRelativeTo(null);
				dispose();
				}
		});
		btnViewStudents_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewStudents_1_1.setBounds(435, 250, 180, 42);
		contentPane.add(btnViewStudents_1_1);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook add = new AddBook();
				add.setVisible(true);
				add.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnAddBook.setFont(new Font("Arial", Font.BOLD, 12));
		btnAddBook.setBounds(10, 85, 179, 42);
		contentPane.add(btnAddBook);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent stud = new AddStudent();
				stud.setVisible(true);
				stud.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnAddStudent.setFont(new Font("Arial", Font.BOLD, 12));
		btnAddStudent.setBounds(10, 142, 179, 42);
		contentPane.add(btnAddStudent);
	}
}
