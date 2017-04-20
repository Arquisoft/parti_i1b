package es.uniovi.asw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table (name="Citizen")
public class Citizen {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true,nullable = false)
	private String firstName;
	@Column(unique = true,nullable = false)
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
	@Transient
	private String unhashedPassword;

	protected Citizen() {}

	public Citizen(String firstName,String lastName,Date birthday, String email,String nif, String address,String nationality, int pollingStationCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday=birthday;
		this.email = email;
		this.nif = nif;
		this.address=address;
		this.nationality=nationality;
		this.pollingStationCode=pollingStationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public void setNationality(String nationality) {
//		this.nationality = nationality;
//	}

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

	public String getUnhashedPassword() {
		return unhashedPassword;
	}

	public void setUnhashedPassword(String unhashedPassword) {
		this.unhashedPassword = unhashedPassword;
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
		if (pollingStationCode != other.pollingStationCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", email=" + email + ", nif=" + nif + ", address=" + address + ", nationality=" + nationality
				+ ", pollingStationCode=" + pollingStationCode + ", password=" + password + "]";
	}

}
