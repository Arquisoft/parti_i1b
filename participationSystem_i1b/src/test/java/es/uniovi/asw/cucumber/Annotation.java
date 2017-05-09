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

	@Given("^I have open the browser$")
	public void openBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@When("^I open the ParticipationSystem website$")
	public void goToParticipation() {
		driver.navigate().to(baseUrl);
	}

	@Then("^login form should exist$")
	public void loginForm() {
		if (driver.getTitle().equals("Citizens Loader Login")) {
			System.out.println("Login functionality exists");
		} else {
			System.out.println("Login functionality doesn't exist");
		}
		driver.close();
	}

	@Then("^I login with the admin user$")
	public void loginAdmin() {
		driver.get(baseUrl + "index.xhtml");

		driver.findElement(By.id("loginForm:userTxt")).clear();
		driver.findElement(By.id("loginForm:userTxt")).sendKeys("admin");
		driver.findElement(By.id("loginForm:passTxt")).clear();
		driver.findElement(By.id("loginForm:passTxt")).sendKeys("admin");
		driver.findElement(By.id("loginForm:loginButton")).click();
		if (driver.getTitle().equals("Citizens Loader - Admin Proposal List")) {
			System.out.println("Admin login functionality works");
		} else {
			System.out.println("Admin login functionality fails");
		}
		driver.close();
	}
}