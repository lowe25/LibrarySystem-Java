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
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import Hibernate.controller.Database;
import Hibernate.model.BookStatus;
import Hibernate.model.Course;
import Hibernate.model.Strand;
import Hibernate.model.Students;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class ViewStudents extends JFrame {

	private JPanel contentPane;
	private JTable tblStudentList;
	private JTextField txtStudentID;
	private JTextField txtFirstname;
	private JTextField txtMiddlename;
	private JTextField txtLastname;
	static JTextField txtCourse;
	private JTextField txtAge;
	private JTextField txtStrand;
	private JTextField txtSection;
	private JTextField txtYear;
	private JTextField txtContact;
 static JComboBox cboCourse = new JComboBox();
	static JComboBox cboStrand = new JComboBox();

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
					ViewStudents frame = new ViewStudents();
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
	//METHOD FOR FUNCTIONS
	public void tblRefresh() {
		Session session = Database.getSession();
		try {
			String sql ="SELECT StudentID AS studentid,"
					+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
					+ "Section AS section, Year AS year FROM Students";
			DefaultTableModel model = (DefaultTableModel)tblStudentList.getModel();
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
	//TO CLEAR TEXTBOX AFTER BUTTON ACTION
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
	//TO UPDATE TABLE REAL TIME
	public void updateTable() {
		//SHOW SQL DATA TO JAVA TABLE
		Session session = Database.getSession();
		try {
			String sql ="SELECT StudentID AS studentid,"
					+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
					+ "Section AS section, Year AS year FROM Students";
			DefaultTableModel model = (DefaultTableModel)tblStudentList.getModel();
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
  //============================================================================
	public ViewStudents() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Page:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 101, 18);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 796, 466);
		contentPane.add(tabbedPane);
		
		JPanel StudentList = new JPanel();
		tabbedPane.addTab("Student List", null, StudentList, null);
		StudentList.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student List:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 103, 14);
		StudentList.add(lblNewLabel_1);
		
		JDateChooser dateDOB = new JDateChooser();
		dateDOB.setBounds(347, 299, 103, 20);
		StudentList.add(dateDOB);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 36, 781, 157);
		StudentList.add(scrollPane);
		
		tblStudentList = new JTable();
		tblStudentList.addMouseListener(new MouseAdapter() {
			@Override
			//TABLE TO TEXTBOX
			public void mouseClicked(MouseEvent e) {
				try {
					 DefaultTableModel model = (DefaultTableModel)tblStudentList.getModel();
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      txtStudentID.setText(model.getValueAt(row, 0).toString());
				      txtFirstname.setText(model.getValueAt(row, 1).toString());
				      txtMiddlename.setText(model.getValueAt(row, 2).toString());
				      txtLastname.setText(model.getValueAt(row, 3).toString());
				      txtAge.setText(model.getValueAt(row, 4).toString());
				      txtContact.setText(model.getValueAt(row, 5).toString());
				      Date dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 6));
				      dateDOB.setDate(dateFormat);
				      txtCourse.setText(model.getValueAt(row, 7).toString());
				      txtStrand.setText(model.getValueAt(row, 8).toString());
				      txtSection.setText(model.getValueAt(row, 9).toString());
				      txtYear.setText(model.getValueAt(row, 10).toString());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(tblStudentList);
		tblStudentList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"StudentID", "Firstname", "Middlename", "Lastname", "Age", "Contact", "Date Of Birth", "Course", "Strand", "Section", "Year"
			}
		));
		//SHOW SQL DATA TO JAVA TABLE
				Session session = Database.getSession();
				try {
					String sql ="SELECT StudentID AS studentid,"
							+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
							+ "Section AS section, Year AS year FROM Students";
					DefaultTableModel model = (DefaultTableModel)tblStudentList.getModel();
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
		lblStudentDetails.setBounds(10, 212, 124, 14);
		StudentList.add(lblStudentDetails);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 247, 77, 14);
		StudentList.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setEnabled(false);
		txtStudentID.setBounds(113, 247, 96, 20);
		StudentList.add(txtStudentID);
		txtStudentID.setColumns(10);
		
		//COMBOBOX TO TEXTBOX
		cboCourse.addActionListener(new ActionListener() {
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
		cboCourse.setBounds(555, 239, 96, 22);
		StudentList.add(cboCourse);
		
		JLabel lblNewLabel_2_1 = new JLabel("Firstname:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 277, 77, 14);
		StudentList.add(lblNewLabel_2_1);
		
		txtFirstname = new JTextField();
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(113, 275, 96, 20);
		StudentList.add(txtFirstname);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Middlename:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(10, 308, 93, 14);
		StudentList.add(lblNewLabel_2_1_1);
		
		txtMiddlename = new JTextField();
		txtMiddlename.setColumns(10);
		txtMiddlename.setBounds(113, 306, 96, 20);
		StudentList.add(txtMiddlename);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Lastname:");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(10, 335, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1);
		
		txtLastname = new JTextField();
		txtLastname.setColumns(10);
		txtLastname.setBounds(113, 333, 96, 20);
		StudentList.add(txtLastname);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Course:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(481, 242, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_1);
		
		txtCourse = new JTextField();
		txtCourse.setEditable(false);
		txtCourse.setColumns(10);
		txtCourse.setBounds(659, 240, 96, 20);
		StudentList.add(txtCourse);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Age:");
		lblNewLabel_2_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2.setBounds(244, 247, 93, 20);
		StudentList.add(lblNewLabel_2_1_1_1_2);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(347, 247, 96, 20);
		StudentList.add(txtAge);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Strand*:");
		lblNewLabel_2_1_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1.setBounds(481, 275, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_1_1);
		
		txtStrand = new JTextField();
		txtStrand.setEditable(false);
		txtStrand.setColumns(10);
		txtStrand.setBounds(659, 271, 96, 20);
		StudentList.add(txtStrand);
		
		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Date Of Birth:");
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1.setBounds(244, 305, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Section:");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(481, 306, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_1_1_1);
		
		txtSection = new JTextField();
		txtSection.setColumns(10);
		txtSection.setBounds(555, 302, 96, 20);
		StudentList.add(txtSection);
		
		JLabel lblNewLabel_2_1_1_1_1_1_2 = new JLabel("* If SHS ");
		lblNewLabel_2_1_1_1_1_1_2.setForeground(Color.RED);
		lblNewLabel_2_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_2.setBounds(247, 213, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_1_1_2);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Year:");
		lblNewLabel_2_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(481, 337, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_1_1_1_1);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(555, 333, 96, 20);
		StudentList.add(txtYear);
	   //=====================================================================
		//UPDATES STUDENT DETAILS
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				if(txtStudentID.getText().trim().isEmpty() && txtFirstname.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Select a Student to Edit");
				}else {
					try {
						//
						String sql ="SELECT StudentID AS studentid,"
								+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
								+ "Section AS section, Year AS year, Fullname AS fullname FROM Students "
								+ "WHERE StudentID = :id";
						int StudentID = Integer.parseInt(txtStudentID.getText());
						Query query = session.createSQLQuery(sql);
						query.setParameter("id", StudentID);
						query.setResultTransformer(Transformers.aliasToBean(Students.class));
						Students students = (Students) query.uniqueResult();
						//
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
						int Year = Integer.parseInt(txtYear.getText());
						//
						tx = session.beginTransaction();
						//
						students.setFirstname(Firstname);
						students.setMiddlename(Middlename);
						students.setLastname(Lastname);
						students.setFullname(Fullname);
						students.setAge(Age);
						students.setContact(Contact);
						students.setDob(DOB);
						students.setCourse(Course);
						students.setStrand(Strand);
						students.setSection(Section);
						students.setYear(Year);
						session.merge(students);
						tx.commit();
						JOptionPane.showMessageDialog(null, "Student Updated");
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
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEdit.setBounds(150, 379, 144, 33);
		StudentList.add(btnEdit);
	//================================================================================
		//DELETE TABLE
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = Database.getSession();
				Transaction tx = null;
				
				if(txtStudentID.getText().trim().isEmpty() && txtFirstname.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Select a Student to Delete");
				}else {
					try {
						//
						int StudentID = Integer.parseInt(txtStudentID.getText());
						//
						String sql ="SELECT StudentID AS studentid,"
								+ "Firstname AS firstname, Middlename AS middlename, Lastname AS lastname, Age AS age, Contact AS contact, DOB AS dob, Course AS course, Strand AS strand,"
								+ "Section AS section, Year AS year FROM Students "
								+ "WHERE StudentID = :id";
						Query query = session.createSQLQuery(sql);
						query.setParameter("id", StudentID);
						query.setResultTransformer(Transformers.aliasToBean(Students.class));
						Students students = (Students) query.uniqueResult();
						tx = session.beginTransaction();
				        int choice = JOptionPane.showConfirmDialog(null, "Are You Sure you want to Delete this Student?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
						//
						if(choice == JOptionPane.YES_OPTION) {
							session.delete(students);
							tx.commit();
							JOptionPane.showMessageDialog(null, "Student Deleted");
							updateTable();
						}else if(choice == JOptionPane.NO_OPTION){
							JOptionPane.showMessageDialog(null, "nvm");
							clearTextbox();
						}
						else if(choice == JOptionPane.CANCEL_OPTION);{
							JOptionPane.showMessageDialog(null, "Cancel");
							clearTextbox();
						}
	                    session.close();
					}catch(Exception ex)
					{
						if (tx != null) {
							tx.rollback();
						}
						ex.printStackTrace();
					}
					finally {
						//session.close();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDelete.setBounds(334, 379, 144, 33);
		StudentList.add(btnDelete);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(347, 274, 96, 20);
		StudentList.add(txtContact);
		
		JLabel lblNewLabel_2_1_1_1_2_3 = new JLabel("Contact:");
		lblNewLabel_2_1_1_1_2_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_3.setBounds(244, 280, 93, 14);
		StudentList.add(lblNewLabel_2_1_1_1_2_3);
		
		cboStrand.addActionListener(new ActionListener() {
			Session session = Database.getSession();
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
		cboStrand.setBounds(555, 272, 94, 22);
		StudentList.add(cboStrand);
		
	
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
		btnBack.setBounds(703, 11, 103, 33);
		contentPane.add(btnBack);
	}
}
