package cucumberTests; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When; 


public class Annotation { 
   WebDriver driver = null; 
   private String baseUrl = "http://localhost:8080/";
	
   @Given("^I have open the browser$") 
   public void openBrowser() { 
      driver = new FirefoxDriver(); 
   } 
	
   @When("^I open the ParticipationSystem website$") 
   public void goToParticipation() { 
      driver.navigate().to(baseUrl); 
   } 
	
   @Then("^login form should exist$") 
   public void loginForm() { 
      if(driver.findElement(By.id("j_idt4")).isEnabled()) { 
         System.out.println("Login functionality exists"); 
      } else { 
         System.out.println("Login functionality doesn't exist"); 
      } 
      driver.close(); 
   } 
   
	@Then("^I login with the admin user$")
	public void loginAdmin() {
		driver.get(baseUrl + "/login.xhtml");
		driver.findElement(By.id("j_idt4:j_idt7")).clear();
		driver.findElement(By.id("j_idt4:j_idt7")).sendKeys("admin");
		driver.findElement(By.id("j_idt4:j_idt9")).clear();
		driver.findElement(By.id("j_idt4:j_idt9")).sendKeys("admin");
		driver.findElement(By.id("j_idt4:j_idt10")).click();
		if (driver.findElement(By.id("j_idt7")).isEnabled()) {
			System.out.println("Admin login functionality works");
		} else {
			System.out.println("Admin login functionality fails");
		}
		driver.close();
	}
}