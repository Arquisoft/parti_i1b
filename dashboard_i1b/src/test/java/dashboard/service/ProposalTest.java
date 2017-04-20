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
public class ProposalTest {

	@Autowired
	private CitizenRepositoy citizen;
	@Autowired
	private ProposalRepository proposal;
	
	private Citizen c1, c2, c3;
	private Proposal p1, p2, p3;

	@Before
	public void init() {
		proposal.deleteAll();
		citizen.deleteAll();
		
		c1 = new Citizen("Pablo","Roncero","password",new Date(),"Roncero@email.com","minif1","miaddress","spain",1);
		c2 = new Citizen("David","Villamil","password",new Date(),"Villamil@email.com","minif2","miaddress","spain",1);
		c3 = new Citizen("Alvalo","Suarez","password",new Date(),"Suarez@email.com","minif3","miaddress","spain",1);
		citizen.save(c1);
		citizen.save(c2);
		citizen.save(c3);

		p1 = new Proposal("p1", c1);
		p2 = new Proposal("p2", c2);
		p3 = new Proposal("p3", c3);
	}

	@Test
	public void test() {
		p1 = proposal.save(p1);
		assertTrue(proposal.findAll().size()==1);
		p2 = proposal.save(p2);
		assertTrue(proposal.findAll().size()==2);
		p3 = proposal.save(p3);
		assertTrue(proposal.findAll().size()==3);
		
		proposal.delete(p1.getId());
		assertTrue(proposal.findAll().size()==2);
		proposal.delete(p2.getId());
		assertTrue(proposal.findAll().size()==1);
		proposal.delete(p3.getId());
		assertTrue(proposal.findAll().size()==0);
		
		proposal.deleteAll();
		citizen.deleteAll();
	}
	
	
}
