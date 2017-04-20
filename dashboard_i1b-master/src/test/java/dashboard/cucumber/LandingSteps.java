package dashboard.cucumber;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dashboard.Application;
import dashboard.listeners.KafkaVoterSender;
import dashboard.model.Citizen;
import dashboard.model.Proposal;
import dashboard.model.Vote;
import dashboard.model.Voter;
import dashboard.persistence.VoteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LandingSteps {
	
	
	protected WebDriver driver = new HtmlUnitDriver();
	protected String baseUrl = "http://localhost:8080/";
	
	@Autowired
	private KafkaVoterSender sender;
	
	@Autowired
	private VoteRepository repository;

	
	@Given("^a list of proposals:$")
	public void aListOfProposals(List<Voter> voters) throws Throwable {
		driver.get(baseUrl);
		Thread.sleep(2000);
		assertNotNull(driver.findElement(By.id("myTableLike")));
		
		Citizen c1 = new Citizen("Pablo","Roncero","password",new Date(),"Roncero@email.com","minif1","miaddress","spain",1);
		Citizen c3 = new Citizen("Alvalo","Suarez","password",new Date(),"Suarez@email.com","minif3","miaddress","spain",1);
		Proposal p1 = new Proposal("p1", c1);
		Proposal p3 = new Proposal("p3", c3);
		Vote v1 = new Vote(c1, p1, true);
		Vote v3 = new Vote(c3, p3, false);
		
		repository.save(v1);
		repository.save(v3);

		
		for(Voter v : voters){
			sender.sendTestVoter(v);
		}
	}

	@Then("^the webpage contains that list:$")
	public void databaseContainsAtLeastOneProposal(DataTable voters) throws Throwable {
		driver.get(baseUrl);
		Thread.sleep(2000);
		for(Voter v : voters.asList(Voter.class))
		assertNotNull(driver.findElements(By
				.xpath("//*[contains(text(),'" + v.getName() + "')]")));

	}

}