package dashboard.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import dashboard.model.Voter;

public class VoterTest {

	Voter v1 = new Voter();
	Voter v2 = new Voter();
	Voter v3 = new Voter();
	Voter v4 = new Voter("AA","dislike","ee","comment");
	
	@Before
	public void before() {
		v1.setName("DD");
		v2.setName("BB");
		v3.setName("RR");
		
		v1.setVote("like");
		v2.setVote("like");
		v3.setVote("like");
		
		v1.setProposal("cc");
		v2.setProposal("cc");
		v3.setProposal("ee");
		
		v1.setComment("comment");
		v2.setComment("comment");
		v3.setComment("comment");
	}

	@Test
	public void testName() {
		assertNotEquals(v1.getName(), v2.getName());
		assertNotEquals(v2.getName(), v3.getName());
		assertNotEquals(v3.getName(), v4.getName());
	}
	
	@Test
	public void testVote() {
		assertEquals(v1.getVote(), v2.getVote());
		assertEquals(v2.getVote(), v3.getVote());
		assertNotEquals(v1.getVote(), v4.getVote());
	}
	
	@Test
	public void testProposal() {
		assertEquals(v1.getProposal(), v2.getProposal());
		assertNotEquals(v2.getProposal(), v3.getProposal());
		assertEquals(v3.getProposal(), v4.getProposal());
	}
	
	@Test
	public void testComment() {
		assertEquals(v1.getComment(), v2.getComment());
		assertEquals(v2.getComment(), v3.getComment());
		assertEquals(v3.getComment(), v4.getComment());
	}

}
