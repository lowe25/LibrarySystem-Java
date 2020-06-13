package Hibernate.model;
import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="Status")
public class BookStatus implements Serializable {
	private static final long serialVersionUID = -5302830493777198637L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="StatusID")
 private int statusid;
 
@Column(name="Status")
 private String status;
 
public BookStatus() {}

public BookStatus(int StatusID, String Status) {
	this.statusid = statusid;
	this.status = status;
}

public int getStatusid() {
	return statusid;
}

public void setStatusid(int statusid) {
	this.statusid = statusid;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
 
}
