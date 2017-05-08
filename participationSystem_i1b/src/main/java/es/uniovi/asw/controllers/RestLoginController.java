package es.uniovi.asw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Citizen;

@RestController
@RequestMapping("/rest")
public class RestLoginController {
	
	@Autowired
	private Factories factoria;
	
	@RequestMapping(value="/user", method= RequestMethod.POST)	
	public Citizen findCitizen(@RequestParam(value="login") String login, @RequestParam(value="password") String password){
		Citizen cit=factoria.getServicesFactory().getCitizenService().checkLogin(login, password);
		return cit;
	}

}
