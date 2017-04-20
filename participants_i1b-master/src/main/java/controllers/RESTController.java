package controllers;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Citizen;

@RestController
public class RESTController {

    @Autowired
	private CitizenController citizenController;
    
    @RequestMapping("/JSONlogin")
	public Citizen login(@RequestParam(value="login") String login
			, @RequestParam(value="password") String password) {
		Citizen citizen = citizenController.getParticipant(login, password);
		return citizen;
		
	}
    
}