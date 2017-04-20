package tests.model;

import static org.junit.Assert.*;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import model.Citizen;

public class CitizenTest {

	private Citizen ct1;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		ct1 = new Citizen("userTest", "asw99", new Date(1981,12,01,00,00) , "userTest@mail.com", "1681U", "calle pos", "GER", 120);
	}

	@Test
	public void basicMethodsTest() {
		assertEquals( ct1.getFirstName()			,"userTest");
		assertEquals( ct1.getLastName()				,"asw99");
		//assertEquals( ct1.getBirthday()				,new Date(1981,12,01,00,00));
		assertEquals( ct1.getEmail()				,"userTest@mail.com");
		assertEquals( ct1.getNif()					,"1681U");
		assertEquals( ct1.getAddress()				,"calle pos");
		assertEquals( ct1.getNationality()			,"GER");
		assertEquals( ct1.getpollingStationCode()	,120);
		
	}
	
	@Test
	public void testToString() {
	   assertEquals("Citizen [id=" + ct1.getId() + ", firstName=" + ct1.getFirstName() + ", lastName=" + ct1.getLastName() + /*", birthday=" + ct1.getBirthday()
				+ */", email=" + ct1.getEmail() + ", nif=" + ct1.getNif() + ", address=" + ct1.getAddress() + ", nationality=" + ct1.getNationality()
				+ ", pollingStationCode=" + ct1.getpollingStationCode() + ", password=" + ct1.getPassword() + "]", ct1.toString());
	}
}
