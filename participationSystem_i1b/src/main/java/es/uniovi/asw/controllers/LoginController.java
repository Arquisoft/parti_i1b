package es.uniovi.asw.controllers;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Citizen;

@Component("loginController")
@Scope("request")
public class LoginController {

	private String user;
	private String pass;
	@Autowired
	private Factories factoria;

	public String logIn() {

		Citizen cit = factoria.getServicesFactory().getCitizenService().checkLogin(user, pass);
		if (factoria.getServicesFactory().getAdministratorService().checkLogin(user, pass) != null) {
			return "admin";
		} else if (cit != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", cit);
			System.out.println("-------------------------------" + cit.getFirstName());
			return "citizen";
		} else {
			return "error";
		}
	}
	
	public String logOut() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", null);
		return "logOut";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
