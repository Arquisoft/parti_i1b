package es.uniovi.asw.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Citizen")
public class Citizen {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthday;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String nif;
	private String address;
	private String nationality;
	private int pollingStationCode;
	private String password;
	@OneToMany(mappedBy = "citizen")
	private List<Proposal> proposals = new ArrayList<Proposal>();
	@OneToMany(mappedBy = "citizen")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Vote> votes = new ArrayList<Vote>();
	@OneToMany(mappedBy = "citizen")
	private List<Comment> comments = new ArrayList<Comment>();

	protected Citizen() {
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return !(pollingStationCode != other.pollingStationCode);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	public List<Comment> _getComments() {
		return comments;
	}

	public List<Comment> getComments() {
		return new ArrayList<Comment>(comments);
	}

	public List<Vote> _getVotes() {
		return votes;
	}

	public List<Vote> getVotes() {
		return new ArrayList<Vote>(votes);
	}

	public List<Proposal> _getProposals() {
		return proposals;
	}

	public List<Proposal> getProposals() {
		return new ArrayList<Proposal>(proposals);
	}

}
