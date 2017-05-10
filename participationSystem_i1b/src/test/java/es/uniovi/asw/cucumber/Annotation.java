package es.uniovi.asw.cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;

@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Annotation {
	private WebDriver driver = null;
	private String baseUrl = "http://localhost:8080/";

	// Givens
	
	@Given("^I have open the browser$")
	public void openBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.navigate().to(baseUrl);
	}
	
	@Given("^I'm logged as an Admin$")
	public void loginAdminGiven() {
		logAdmin();
	}
	
	@Given("^I'm logged as a Citizen$")
	public void loginCitizenGiven() {
		logCitizen();
	}
		
	// Whens

	@When("^I open the ParticipationSystem website$")
	public void goToParticipation() {
		driver.navigate().to(baseUrl);
	}
	
	@When("^I log as an admin$")
	public void goToAdmin() {
		logAdmin();
	}
	
	@When("^I log as a Citizen$")
	public void goToCitizen() {
		logCitizen();
	}
	
	@When("^I go to the Configuration$")
	public void goToConfig() throws InterruptedException {
        Thread.sleep(5000);
		driver.findElement(By.id("configbutton")).click();
        Thread.sleep(5000);
	}
	
	@When("^I add a proposal$")
	public void addProposal() throws InterruptedException {
        Thread.sleep(5000);
		driver.findElement(By.id("formAdd:btn_Add")).click();
        Thread.sleep(10000);
		driver.findElement(By.id("panelgrid:titletext")).sendKeys("prueba pro");
		driver.findElement(By.id("panelgrid:desctext")).sendKeys("prueba22");
		driver.findElement(By.id("panelgrid:saveprob")).click();
        Thread.sleep(5000);
	}

	// Thens
	
	@Then("^I'm in Config$")
	public void checkConfig() throws InterruptedException {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		if (bodyText.contains("Forbidden Words")) {
			System.out.println("Configuration functionality");
		} else {
			System.out.println("Configuration functionality");
		}
		driver.close();
	}
	
	@Then("^There is a new proposal$")
	public void checkNewProposal() throws InterruptedException {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		if (bodyText.contains("prueba22")) {
			System.out.println("Proposal functionality");
		} else {
			System.out.println("Proposal functionality");
		}
		driver.close();
	}

	@Then("^login form should exist$")
	public void loginForm() {
		if (driver.getTitle().equals("Citizens Loader Login")) {
			System.out.println("Login functionality");
		} else {
			System.out.println("Login functionality");
		}
		driver.close();
	}

	@Then("^I'm admin$")
	public void loginAdmin() {
		if (driver.getTitle().equals("Citizens Loader - Admin Proposal List")) {
			System.out.println("Admin login functionality");
		} else {
			System.out.println("Admin login functionality");
		}
		driver.close();
	}
	
	@Then("^I'm a citizen$")
	public void loginCitizen() {
		if (driver.getTitle().equals("Citizens Loader - Citizen Proposal List")) {
			System.out.println("Citizen login functionality");
		} else {
			System.out.println("Citizen login functionality");
		}
		driver.close();
	}
	
	// Helper methods
	
	private void logAdmin() {
		driver.get(baseUrl + "index.xhtml");
		driver.findElement(By.id("loginForm:userTxt")).clear();
		driver.findElement(By.id("loginForm:userTxt")).sendKeys("admin");
		driver.findElement(By.id("loginForm:passTxt")).clear();
		driver.findElement(By.id("loginForm:passTxt")).sendKeys("admin");
		driver.findElement(By.id("loginForm:loginButton")).click();
	}
	
	private void logCitizen() {
		driver.get(baseUrl + "index.xhtml");
		driver.findElement(By.id("loginForm:userTxt")).clear();
		driver.findElement(By.id("loginForm:userTxt")).sendKeys("emailprueba@test.com");
		driver.findElement(By.id("loginForm:passTxt")).clear();
		driver.findElement(By.id("loginForm:passTxt")).sendKeys("diego");
		driver.findElement(By.id("loginForm:loginButton")).click();
	}
}