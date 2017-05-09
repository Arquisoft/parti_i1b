package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.uniovi.asw.business.AdministratorService;
import es.uniovi.asw.business.CategoryService;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.CommentService;
import es.uniovi.asw.business.ConfigurationService;
import es.uniovi.asw.business.ForbiddenWordsService;
import es.uniovi.asw.business.ProposalService;
import es.uniovi.asw.business.ServicesFactory;
import es.uniovi.asw.business.VoteService;
import es.uniovi.asw.persistence.model.Administrator;
import es.uniovi.asw.persistence.model.Category;
import es.uniovi.asw.persistence.model.Citizen;
import es.uniovi.asw.persistence.model.Comment;
import es.uniovi.asw.persistence.model.Configuration;
import es.uniovi.asw.persistence.model.ForbiddenWords;
import es.uniovi.asw.persistence.model.Proposal;
import es.uniovi.asw.persistence.model.Vote;
import es.uniovi.asw.persistence.model.VoteComment;
import es.uniovi.asw.persistence.model.VoteProposal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTest {

	@Autowired
	private ServicesFactory servicesFactory;

	private Date createDate(String dateStr) {
		Date date = null;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Test
	public void testCitizen() {

		CitizenService service = servicesFactory.getCitizenService();
		Citizen gabriel = new Citizen("Gabriel1", "Reguero", createDate("31/12/1995"), "emailGabriel1@test.com",
				"55433455B", "dd", "dd", 2);
		Citizen nacho = new Citizen("Nacho1", "Fernandez", createDate("08/01/1995"), "emailNacho1@test.com",
				"71724768J", "mi casa", "española", 47);

		service.save(gabriel);
		service.save(nacho);

		assertNotNull(service.findByEmail(gabriel.getEmail()));
		assertNotNull(service.findByEmail(nacho.getEmail()));

	}

	@Test
	public void testVote() {

		CitizenService service1 = servicesFactory.getCitizenService();

		Citizen oscar = new Citizen("Gabriasdael", "Reguasdero", createDate("31/12/1995"), "emadsilGabriel@test.com",
				"55433955B", "dd", "dd", 2);
		Citizen pedro = new Citizen("Naasdcho", "Fernasdandez", createDate("08/01/1995"), "emasdilNacho@test.com",
				"71769768J", "mi casa", "española", 47);

		service1.save(oscar);
		service1.save(pedro);

		CategoryService catService = servicesFactory.getCategoryService();
		Category sports = new Category("Sport");
		Category travels = new Category("Travels");
		catService.save(sports);
		catService.save(travels);

		ProposalService service3 = servicesFactory.getProposalService();

		Proposal proposal1 = new Proposal("Hacer que Ana deje becaria", "Ana no em cae bien y quiero que se pire",
				oscar, 0, createDate("31/12/1985"), sports);
		Proposal proposal2 = new Proposal("Hacer que Luiso vaya a Ingles",
				"Luiso nunca va a Ingles porque quiere verme", pedro, 0, createDate("31/12/1995"), travels);

		service3.save(proposal1);
		service3.save(proposal2);

		CommentService service2 = servicesFactory.getCommentService();

		Comment coment1 = new Comment("¡Qué feo eres!", proposal1, oscar, createDate("08/01/1995"), 0);
		Comment coment2 = new Comment("¡Qué guapo eres!", proposal2, pedro, createDate("08/05/1995"), 0);

		service2.save(coment1);
		service2.save(coment2);

		VoteService service = servicesFactory.getVoteService();

		Vote vote1 = new VoteComment(oscar, coment1);
		Vote vote2 = new VoteProposal(pedro, proposal2);

		service.save(vote1);
		service.save(vote2);

		assertEquals(1, service.findCommentVotesByCitizen(oscar).size());
		assertEquals(0, service.findProposalVotesByCitizen(oscar).size());

		assertEquals(0, service.findCommentVotesByCitizen(pedro).size());
		assertEquals(1, service.findProposalVotesByCitizen(pedro).size());

		// catService.delete(sports);
		// catService.delete(travels);

	}

	@Test
	public void testDeletAndListCitizen() {

		CitizenService service1 = servicesFactory.getCitizenService();

		Citizen juaaaan = new Citizen("Gabriasdael", "Reguasdero", createDate("31/12/1995"),
				"emadssddilGabriel@test.com", "5543325B", "dd", "dd", 2);
		Citizen matheusss = new Citizen("Naasdcho", "Fernasdandez", createDate("08/01/1995"),
				"emasdssssilNacho@test.com", "769768J", "mi casa", "española", 47);

		service1.save(juaaaan);
		service1.save(matheusss);

		assertNotNull(service1.findAll());

		service1.delete(juaaaan);
		service1.delete(matheusss);

		assertNull(service1.findByEmail(juaaaan.getEmail()));
		assertNull(service1.findByEmail(matheusss.getEmail()));

	}

	@Test
	public void testCategory() {

		CategoryService service = servicesFactory.getCategoryService();

		Category cat1 = new Category("social");
		Category cat2 = new Category("media");
		Category cat3 = new Category("sports");

		service.save(cat1);
		service.save(cat3);

		service.delete(cat1);
		service.delete(cat2);

		assertNotNull(service.findAll());

		service.delete(cat3);

		// assertNull(service.findAll()); si lo dejamos en el primer metodo hay
		// que quitar la otras 2 categorias
	}

	@Test
	public void adminTest() {

		AdministratorService service5 = servicesFactory.getAdministratorService();

		Administrator admin = new Administrator("admin2", "admin2");

		service5.save(admin);

		assertNotNull(service5.checkLogin(admin.getUsername(), admin.getPassword()));

		service5.delete(admin);

		assertNull(service5.checkLogin(admin.getUsername(), admin.getPassword()));

	}

	@Test
	public void proposalTest() {
		CitizenService citService = servicesFactory.getCitizenService();

		Citizen javier = new Citizen("Javier", "muhlach", createDate("23/06/1995"), "javer@javier.es", "324234j", "ds",
				"es", 1);

		citService.save(javier);

		CategoryService catService = servicesFactory.getCategoryService();

		Category categroy1 = new Category("cosasnasis");

		catService.save(categroy1);

		ProposalService serviceprop = servicesFactory.getProposalService();

		Proposal prop1 = new Proposal("dale moreno", "ummmmm", javier, 23, createDate("10/05/2015"), categroy1);

		serviceprop.save(prop1);
		assertNotNull(serviceprop.findAll());
		serviceprop.delete(prop1);

	}

	@Test
	public void configureTest() {

		ConfigurationService service = servicesFactory.getConfigurationService();

		Configuration config1 = new Configuration(new HashSet<ForbiddenWords>(), 10, 2000);
		Configuration config2 = new Configuration(new HashSet<ForbiddenWords>(), 8, 1000);
		Configuration config3 = new Configuration(new HashSet<ForbiddenWords>(), 5, 50000);

		service.save(config1);
		service.save(config2);
		service.save(config3);

		assertEquals(5, service.actualConfiguration().getDeadline());

		service.delete(config3);
		assertEquals(8, service.actualConfiguration().getDeadline());

		ForbiddenWordsService service2 = servicesFactory.getForbiddenWordsService();

		ForbiddenWords fw = new ForbiddenWords("beber", config1);
		ForbiddenWords fw1 = new ForbiddenWords("fumar", config1);
		ForbiddenWords fw2 = new ForbiddenWords("droga", config1);
		ForbiddenWords fw3 = new ForbiddenWords("alcohol", config1);
		ForbiddenWords fw4 = new ForbiddenWords("yonki", config1);
		ForbiddenWords fw5 = new ForbiddenWords("perro", config2);

		service2.save(fw);
		service2.save(fw1);
		service2.save(fw2);
		service2.save(fw3);
		service2.save(fw4);
		service2.save(fw5);

		assertEquals(5, service.getForbiddenWords(config1).size());
		assertEquals(1, service.getForbiddenWords(config2).size());

	}

}
