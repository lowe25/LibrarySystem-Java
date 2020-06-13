package Hibernate.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Hibernate.controller.Database;
import Hibernate.model.Course;
import Hibernate.model.Strand;
import Hibernate.model.Students;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTable tblStudents;
	private JTextField txtStudentID;
	private JTextField txtAge;
	private JTextField txtContact;
	private JTextField txtFirstname;
	private JTextField txtMiddlename;
	private JTextField txtLastname;
	private JTextField txtCourse;
	private JTextField txtStrand;
	private JTextField txtSection;
	private JTextField txtYear;
	static JComboBox cboCourse = new JComboBox();
	static JComboBox cboStrand = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AddBook ab = new AddBook();
		ab.cboType();
		comboCourse();
		comboStrand();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	
	public void clearTextbox() {
		 txtStudentID.setText(null);
		 txtFirstname.setText(null);
		 txtMiddlename.setText(null);
		 txtLastname.setText(null);
		 txtCourse.setText(null);
		 txtAge.setText(null);
		 txtStrand.setText(null);
		txtSection.setText(null);
		txtYear.setText(null);
		txtContact.setText(null);
	}
	//DATABASE TO COMBO BOX
		public static  void comboStrand() {
			try {
				Session session = Database.getSession();
				String sql ="SELECT StrandID AS strandid,"
						+ "Strand AS strand FROM Strand";
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(Strand.class));
				List<Strand> StandList = query.list();
				for(Strand strand : StandList) {
					cboStrand.addItem(strand.getStrand());
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//DATABASE TO COMBO BOX
		public static  void comboCourse() {
			Session session = Database.getSession();
			try {
				String sql ="SELECT CourseID AS courseid,"
						+ "Course AS course FROM Course";
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(Course.class));
				List<Course> CourseList = query.list();
				for(Course course : CourseList) {
					cboCourse.addItem(course.getCourse());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//REFRESH TABLE
		public void tblRefresh() {
			Session session = Database.getSession();
			try {
				String sql ="SELECT StudentID AS studentid,"
						+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
						+ "Section AS section, Year AS year FROM Students";
				DefaultTableModel model = (DefaultTableModel)tblStudents.getModel();
				model.setRowCount(0);
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(Students.class));
				Students students = new Students();
			    List<Students> bookList = query.list();
			    for(Students books: bookList) {
			    	model.addRow(new Object[] {
						books.getStudentid(),
						books.getFirstname(),
						books.getMiddlename(),
						books.getLastname(),
						books.getAge(),
						books.getContact(),
						books.getDob(),
						books.getCourse(),
						books.getStrand(),
						books.getSection(),
						books.getYear()
					});
			    }
			}catch(Exception e) {
				
			}
		}
	 //UPDATE TABLE
		public void updateTable() {
			//SHOW SQL DATA TO JAVA TABLE
			Session session = Database.getSession();
			try {
				String sql ="SELECT StudentID AS studentid,"
						+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
						+ "Section AS section, Year AS year FROM Students";
				DefaultTableModel model = (DefaultTableModel)tblStudents.getModel();
				model.setRowCount(0);
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(Students.class));
				Students students = new Students();
			    List<Students> bookList = query.list();
			    for(Students books: bookList) {
			    	model.addRow(new Object[] {
						books.getStudentid(),
						books.getFirstname(),
						books.getMiddlename(),
						books.getLastname(),
						books.getAge(),
						books.getContact(),
						books.getDob(),
						books.getCourse(),
						books.getStrand(),
						books.getSection(),
						books.getYear()
					});
			    }
			}catch(Exception e) {
				
			}
		}
	public AddStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Page:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 101, 18);
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
		btnBack.setBounds(703, 11, 103, 33);
		contentPane.add(btnBack);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 794, 475);
		contentPane.add(tabbedPane);
		
		JPanel AddStudent = new JPanel();
		tabbedPane.addTab("Add A Student", null, AddStudent, null);
		AddStudent.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student List:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 103, 14);
		AddStudent.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 769, 179);
		AddStudent.add(scrollPane);
		
		tblStudents = new JTable();
		scrollPane.setViewportView(tblStudents);
		tblStudents.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"StudentID", "Firstname", "Middlename", "Lastname", "Age", "Contact", "Date of Birth", "Course", "Strand", "Section", "Year"
			}
		));
		//SHOW SQL DATA TO JAVA TABLE
		Session session = Database.getSession();
		try {
			String sql ="SELECT StudentID AS studentid,"
					+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
					+ "Section AS section, Year AS year FROM Students";
			DefaultTableModel model = (DefaultTableModel)tblStudents.getModel();
			model.setRowCount(0);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.aliasToBean(Students.class));
		    List<Students> bookList = query.list();
		    for(Students books: bookList) {
		    	model.addRow(new Object[] {
					books.getStudentid(),
					books.getFirstname(),
					books.getMiddlename(),
					books.getLastname(),
					books.getAge(),
					books.getContact(),
					books.getDob(),
					books.getCourse(),
					books.getStrand(),
					books.getSection(),
					books.getYear()
				});
		    }
		}catch(Exception e) {
			
		}
		
		JLabel lblStudentDetails = new JLabel("Student Details:");
		lblStudentDetails.setFont(new Font("Arial", Font.BOLD, 15));
		lblStudentDetails.setBounds(10, 227, 124, 14);
		AddStudent.add(lblStudentDetails);
		
		JLabel lblNewLabel_2_1_1_1_1_1_2 = new JLabel("* If SHS ");
		lblNewLabel_2_1_1_1_1_1_2.setForeground(Color.RED);
		lblNewLabel_2_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_2.setBounds(247, 228, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_1_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 262, 77, 14);
		AddStudent.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setEnabled(false);
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(113, 262, 96, 20);
		AddStudent.add(txtStudentID);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Age:");
		lblNewLabel_2_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2.setBounds(244, 262, 93, 20);
		AddStudent.add(lblNewLabel_2_1_1_1_2);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(347, 262, 96, 20);
		AddStudent.add(txtAge);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(347, 289, 96, 20);
		AddStudent.add(txtContact);
		
		JLabel lblNewLabel_2_1_1_1_2_3 = new JLabel("Contact:");
		lblNewLabel_2_1_1_1_2_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_3.setBounds(244, 295, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_2_3);
		
		txtFirstname = new JTextField();
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(113, 290, 96, 20);
		AddStudent.add(txtFirstname);
		
		JLabel lblNewLabel_2_1 = new JLabel("Firstname:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 292, 77, 14);
		AddStudent.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Middlename:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(10, 323, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1);
		
		txtMiddlename = new JTextField();
		txtMiddlename.setColumns(10);
		txtMiddlename.setBounds(113, 321, 96, 20);
		AddStudent.add(txtMiddlename);
		
		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Date Of Birth:");
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1.setBounds(244, 320, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_2_1);
		
		JDateChooser dateDOB = new JDateChooser();
		dateDOB.setBounds(347, 314, 103, 20);
		AddStudent.add(dateDOB);
		
		txtLastname = new JTextField();
		txtLastname.setColumns(10);
		txtLastname.setBounds(113, 348, 96, 20);
		AddStudent.add(txtLastname);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Lastname:");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(10, 350, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Course:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(481, 257, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_1);
		
		
		cboCourse.addActionListener(new ActionListener() {
			Transaction tx = null;
			public void actionPerformed(ActionEvent e) {
				try {
					Session session = Database.getSession();
					String sql ="SELECT StrandID AS strandid,"
							+ "Strand AS strand FROM Strand";
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(Transformers.aliasToBean(Strand.class));
					List<Strand> StandList = query.list();
					for(Strand strand : StandList) {
						cboCourse.addItem(strand.getStrand());
					}
					String selectedCourse = cboCourse.getSelectedItem().toString();
					txtCourse.setText(selectedCourse);
				}catch(Exception ex) {
					
				}
			}
		});
		cboCourse.setBounds(555, 254, 96, 22);
		AddStudent.add(cboCourse);
		
		txtCourse = new JTextField();
		txtCourse.setEditable(false);
		txtCourse.setColumns(10);
		txtCourse.setBounds(659, 255, 96, 20);
		AddStudent.add(txtCourse);
		
		txtStrand = new JTextField();
		txtStrand.setEditable(false);
		txtStrand.setColumns(10);
		txtStrand.setBounds(659, 286, 96, 20);
		AddStudent.add(txtStrand);
		
		cboStrand.addActionListener(new ActionListener() {
			Transaction tx = null;
			public void actionPerformed(ActionEvent e) {
				try {
					Session session = Database.getSession();
					String sql ="SELECT StrandID AS strandid,"
							+ "Strand AS strand FROM Strand";
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(Transformers.aliasToBean(Strand.class));
					List<Strand> StandList = query.list();
					for(Strand strand : StandList) {
						cboStrand.addItem(strand.getStrand());
					}
					String selectedStrand = cboStrand.getSelectedItem().toString();
					txtStrand.setText(selectedStrand);
				}catch(Exception ex) {
					
				}
			}
		});
		cboStrand.setBounds(555, 287, 94, 22);
		AddStudent.add(cboStrand);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Strand*:");
		lblNewLabel_2_1_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1.setBounds(481, 290, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Section:");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(481, 321, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_1_1_1);
		
		txtSection = new JTextField();
		txtSection.setColumns(10);
		txtSection.setBounds(555, 317, 96, 20);
		AddStudent.add(txtSection);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(555, 348, 96, 20);
		AddStudent.add(txtYear);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Year:");
		lblNewLabel_2_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(481, 352, 93, 14);
		AddStudent.add(lblNewLabel_2_1_1_1_1_1_1_1);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				//VALIDATION 
				if(txtStudentID.getText().trim().isEmpty() && txtFirstname.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Input the textfields");
				}else {
					try {
						tx = session.beginTransaction();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String Firstname = txtFirstname.getText();
						String Middlename = txtMiddlename.getText();
						String Lastname = txtLastname.getText();
						String Fullname = txtFirstname.getText() + txtMiddlename.getText() + txtLastname.getText();
						int Age = Integer.parseInt(txtAge.getText());
						int Contact = Integer.parseInt(txtContact.getText());
						String DOB = dateFormat.format(dateDOB.getDate());
						String Course = txtCourse.getText();
						String Strand = txtStrand.getText();
						String Section = txtSection.getText();
						Section = String.valueOf(Section);
						int Year = Integer.parseInt(txtYear.getText());
						//
						Students student = new Students();
						student.setFirstname(Firstname);
						student.setMiddlename(Middlename);
						student.setLastname(Lastname);
						student.setFullname(Fullname);
						student.setAge(Age);
						student.setContact(Contact);
						student.setDob(DOB);
						student.setContact(Contact);
						student.setCourse(Course);
						student.setStrand(Strand);
						student.setSection(Section);
						student.setYear(Year);
						Integer id = (Integer)session.save(student);
						tx.commit();
						JOptionPane.showMessageDialog(null, "Student Successfully Added");
						clearTextbox();
						updateTable();
					}catch(Exception ex)
					{
						if (tx != null) {
							tx.rollback();
						}
						ex.printStackTrace();
					}
					finally {
						session.close();
					}
				}
				
			}
		});
		btnAddStudent.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAddStudent.setBounds(10, 392, 103, 33);
		AddStudent.add(btnAddStudent);
	}

}
