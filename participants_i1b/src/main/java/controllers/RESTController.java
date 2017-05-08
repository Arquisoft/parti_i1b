package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Citizen;

@RestController
public class RESTController {

    @Autowired
	private CitizenController citizenController;
    
    @PostMapping("/JSONlogin")
	public Citizen login(@RequestParam(value="login") String login
			, @RequestParam(value="password") String password) {
		Citizen citizen = citizenController.getParticipant(login, password);
		return citizen;
		
	}
    
}