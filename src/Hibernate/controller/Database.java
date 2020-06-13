package Hibernate.controller;

import org.hibernate.Session;




import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import Hibernate.model.BookType;
import Hibernate.model.Books;
import Hibernate.model.Borrowers;
import Hibernate.model.Course;
import Hibernate.model.Reserves;
import Hibernate.model.Strand;
import Hibernate.model.Students;

public class Database {
	private static SessionFactory factory;
	public static Session getSession() {
		factory = new AnnotationConfiguration()
				.configure()
				.addAnnotatedClass(Books.class)
				.addAnnotatedClass(Borrowers.class)
				.addAnnotatedClass(Reserves.class)
				.addAnnotatedClass(Students.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Strand.class)
				.addAnnotatedClass(Reserves.class)
				.addAnnotatedClass(BookType.class)
				.buildSessionFactory();
		
		return factory.openSession();
	}
}
