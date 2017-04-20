package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Citizen {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, nullable = false)
	private String firstName;
	@Column(unique = true, nullable = false)
	private String lastName;
	@Column(nullable = false)
	private Date birthday;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String nif;
	@Column
	private String address;
	@Column
	private String nationality;
	@Column
	private int pollingStationCode;
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "citizen")
	private Set<Vote> votes = new HashSet<>();
	@OneToMany(mappedBy = "citizen")
	private Set<Proposal> proposals = new HashSet<>();
	@OneToMany(mappedBy="citizen")
	private Set<Comment> comments = new HashSet<>();

	public Citizen(){}
	
	public Citizen(String firstName, String lastName, Date birthday, String email, String nif, String address,
			String nationality, int pollingStationCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		this.nif = nif;
		this.address = address;
		this.nationality = nationality;
		this.pollingStationCode = pollingStationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getNif() {
		return nif;
	}

	public String getAddress() {
		return address;
	}

	public String getNationality() {
		return nationality;
	}

	public int getpollingStationCode() {
		return pollingStationCode;
	}

	public Set<Proposal> _getProposals() {
		return proposals;
	}

	public Set<Comment> _getComments() {
		return comments;
	}
	
	public Set<Vote> _getVotes() {
		return votes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", email="
				+ email + ", nif=" + nif + ", address=" + address + ", nationality=" + nationality
				+ ", pollingStationCode=" + pollingStationCode + ", password=" + password + "]";
	}
	

}