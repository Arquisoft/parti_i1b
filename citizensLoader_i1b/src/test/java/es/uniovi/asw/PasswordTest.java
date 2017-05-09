package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.password.PasswordGenerator;

public class PasswordTest {

	private Citizen citizen;

	@Before
	public void setUp() throws Exception {

		citizen = new Citizen("Gabriel", "García", new Date(), "email@test.com", "55433455B", "cc", "cc", 1);
		citizen.setUnhashedPassword("test");

	}

	@Test
	public void testGeneratedPassword() {

		PasswordGenerator.generatePasswords(citizen);
		assertEquals(citizen.getUnhashedPassword().length(), 10);

	}

}
