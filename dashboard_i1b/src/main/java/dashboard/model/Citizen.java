package dashboard.model;

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
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
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
	
	public Citizen(String firstName, String lastName, String password,Date birthday, String email, String nif, String address,
			String nationality, int pollingStationCode) {
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		setBirthday(birthday);
		setEmail(email);
		setNif(nif);
		setAddress(address);
		setNationality(nationality);
		setPollingStationCode(pollingStationCode);
	}
	
	
	public int getPollingStationCode() {
		return pollingStationCode;
	}

	public void setPollingStationCode(int pollingStationCode) {
		this.pollingStationCode = pollingStationCode;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Set<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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
}