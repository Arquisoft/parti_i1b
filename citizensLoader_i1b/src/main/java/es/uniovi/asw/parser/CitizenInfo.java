package es.uniovi.asw.parser;

public class CitizenInfo {
	private String firstName;
	private String lastName;
	private String email;
	private String birthday;
	private String address;
	private String nationality;
	private String NIF;
	private String pollingStationCode;

	public CitizenInfo(String firstName, String lastName, String birthday, String email, String NIF, String address,
			String nationality, String pollingStationCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		this.NIF = NIF;
		this.address = address;
		this.nationality = nationality;
		this.pollingStationCode = pollingStationCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getAddress() {
		return address;
	}

	public String getNationality() {
		return nationality;
	}

	public String getNIF() {
		return NIF;
	}

	public String getPollingStationCode() {
		return pollingStationCode;
	}

	public boolean isEmpty() {
		return (firstName == null || firstName.isEmpty()) && (NIF == null || NIF.isEmpty())
				&& (email == null || email.isEmpty()) && (pollingStationCode == null || pollingStationCode.isEmpty());
	}

	@Override
	public String toString() {
		return "CitizenInfo [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", birthday="
				+ birthday + ", address=" + address + ", nationality=" + nationality + ", NIF=" + NIF
				+ ", pollingStationCode=" + pollingStationCode + "]";
	}

	public void setBirthday(String object) {
		birthday = object;
	}

}
