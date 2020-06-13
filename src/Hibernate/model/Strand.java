package Hibernate.model;
import java.io.Serializable;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Strand")
public class Strand implements Serializable{
	private static final long serialVersionUID = -5302830493777198637L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="StrandID")
	private int strandid;
	
	@Column(name="Strand")
	private String strand;
	
	public Strand(){}
	
	public Strand(int StrandID, String Strand) {
		this.strandid = strandid;
		this.strand = strand;
	}
	public int getStrandid() {
		return strandid;
	}

	public void setStrandid(int strandid) {
		this.strandid = strandid;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}
}
