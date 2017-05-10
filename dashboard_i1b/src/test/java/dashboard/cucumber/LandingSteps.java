package dashboard.cucumber;

import static org.junit.Assert.assertNotNull;

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
import dashboard.kafkaListener.KafkaVoterSender;
import dashboard.model.Voter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LandingSteps {

	protected WebDriver driver = new HtmlUnitDriver();
	protected String baseUrl = "http://localhost:8090/";

	@Autowired
	private KafkaVoterSender sender;

	@Given("^a list of voters:$")
	public void aListOfProposals(List<Voter> voters) throws Throwable {
		driver.get(baseUrl);
		Thread.sleep(2000);
		assertNotNull(driver.findElement(By.id("myTableLike")));

		for (Voter v : voters) {
			sender.sendTestVoter(v);
		}
	}

	@Then("^the webpage contains that list:$")
	public void databaseContainsAtLeastOneProposal(DataTable voters) throws Throwable {
		driver.get(baseUrl);
		Thread.sleep(2000);
		for (Voter v : voters.asList(Voter.class))
			assertNotNull(driver.findElements(By.xpath("//*[contains(text(),'" + v.getName() + "')]")));

	}

}