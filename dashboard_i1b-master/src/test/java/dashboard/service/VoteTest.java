package dashboard.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

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
public class VoteTest {

	@Autowired
	private VoteRepository votes;
	@Autowired
	private CitizenRepositoy citizen;
	@Autowired
	private ProposalRepository proposal;
	
	private Citizen c1, c2, c3;
	private Proposal p1, p2, p3;
	private Vote v1, v2, v3;

	@Before
	public void init() {
		votes.deleteAll();
		proposal.deleteAll();
		citizen.deleteAll();
		
		c1 = new Citizen("Pablo","Roncero","password",new Date(),"Roncero@email.com","minif1","miaddress","spain",1);
		c2 = new Citizen("David","Villamil","password",new Date(),"Villamil@email.com","minif2","miaddress","spain",1);
		c3 = new Citizen("Alvalo","Suarez","password",new Date(),"Suarez@email.com","minif3","miaddress","spain",1);
		p1 = new Proposal("p1", c1);
		p2 = new Proposal("p2", c2);
		p3 = new Proposal("p3", c3);
		v1 = new Vote(c1, p1, true);
		v2 = new Vote(c2, p2, true);
		v3 = new Vote(c3, p3, false);
	}

	@Test
	public void test() {
		v1 = votes.save(v1);
		assertTrue(votes.findAll().size()==1);
		v2 = votes.save(v2);
		assertTrue(votes.findAll().size()==2);
		v3 = votes.save(v3);
		assertTrue(votes.findAll().size()==3);
		
		votes.delete(v1.getID());
		assertTrue(votes.findAll().size()==2);
		votes.delete(v2.getID());
		assertTrue(votes.findAll().size()==1);
		votes.delete(v3.getID());
		assertTrue(votes.findAll().size()==0);
		
		votes.deleteAll();
		proposal.deleteAll();
		citizen.deleteAll();
	}
	
	
}
