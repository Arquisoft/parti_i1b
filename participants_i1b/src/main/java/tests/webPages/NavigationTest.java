package tests.webPages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URL;
import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import model.Citizen;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class NavigationTest {

	private WebEnvironment port = WebEnvironment.RANDOM_PORT;
	private TestRestTemplate rt;
	private Citizen ct1 = new Citizen("user8", "asw8", new Date(), "user8@gmail.com", "6845R", "calle hij","GER", 13);
	private URL base;

	@Autowired
	private WebApplicationContext webapp;

	@Before(value = "")
	public void setUp() {
		MockMvcBuilders.webAppContextSetup(this.webapp).build();
	}

	@Test
	public void loginPageTest() throws Exception {
		this.base = new URL("http://localhost:" + 8080 + "/");
		rt = new TestRestTemplate();
		ResponseEntity<String> response = rt.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), containsString("Login page"));
	}

	@Test
	public void testLoginActionRIGHT() {
		RequestBuilder requestLogin = 
				post("/login").param("login", ct1.getEmail()).param("password", ct1.getPassword());
		rt = new TestRestTemplate();
		assertThat(rt.postForObject(
				"http://localhost:"
		        		+ port
		        		+ "/login",
		        		requestLogin, 
		        		String.class)
		        .contains(ct1.getFirstName()));
		assertThat(rt
		        .postForObject("http://localhost:" + port + "/login", requestLogin, String.class)
		        .contains(ct1.getLastName()));
		assertThat(rt
		        .postForObject("http://localhost:" + port + "/login", requestLogin, String.class)
		        .contains(ct1.getEmail()));
		assertThat(rt
		        .postForObject("http://localhost:" + port + "/login", requestLogin, String.class)
		        .contains(ct1.getNationality()));
	}

	@Test
	public void testLoginActionWRONG() {
		rt = new TestRestTemplate();
		RequestBuilder requestLogin = 
				post("/login").param("login", "errormail").param("password", "errorPass");

		assertThat(rt
		        .postForObject("http://localhost:" + port + "/login", requestLogin, String.class)
		        .contains("Error 404 user not found!"));
	}
}