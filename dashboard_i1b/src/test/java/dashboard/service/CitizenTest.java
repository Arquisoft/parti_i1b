package dashboard.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dashboard.Application;
import dashboard.model.Citizen;
import dashboard.model.Proposal;
import dashboard.model.Vote;
import dashboard.persistence.CitizenRepositoy;
import dashboard.persistence.ProposalRepository;
import dashboard.persistence.VoteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Component
public class CitizenTest {

	@Autowired
	private CitizenRepositoy citizen;

	private Citizen c1, c2, c3;

	@Before
	public void init() {
		c1 = new Citizen("Pablo", "Roncero", "password", new Date(), "roncero@email.com", "minif1", "miaddress", "spain", 1);
		c2 = new Citizen("David", "Villamil", "password", new Date(), "villamil@email.com", "minif2", "miaddress", "spain", 1);
		c3 = new Citizen("Alvalo", "Suarez", "password", new Date(), "suarez@email.com", "minif3", "miaddress", "spain", 1);
	}

	@Test
	public void test() {
		citizen.deleteAll();

		c1 = citizen.save(c1);
		assertTrue(citizen.findAll().size() == 1);
		assertTrue(c1.equals(citizen.findByEmailAndPassword("roncero@email.com", "password")));
		c2 = citizen.save(c2);
		assertTrue(citizen.findAll().size() == 2);
		assertTrue(c2.equals(citizen.findByEmailAndPassword("villamil@email.com", "password")));
		c3 = citizen.save(c3);
		assertTrue(citizen.findAll().size() == 3);
		assertTrue(c3.equals(citizen.findByEmailAndPassword("suarez@email.com", "password")));

		citizen.delete(c1.getId());
		assertTrue(citizen.findAll().size() == 2);
		citizen.delete(c2.getId());
		assertTrue(citizen.findAll().size() == 1);
		citizen.delete(c3.getId());
		assertTrue(citizen.findAll().size() == 0);

		citizen.deleteAll();
	}

}
