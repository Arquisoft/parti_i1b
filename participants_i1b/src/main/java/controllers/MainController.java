package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import model.Citizen;

import java.util.Date;

@Controller
public class MainController {

	@Autowired
	private CitizenController citizenController;

	@RequestMapping("/")
	public String landing() {
		return "index";
	}

	@PostMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Citizen citizen = citizenController.getParticipant(login, password);
		if (citizen == null) {
			model.addAttribute("citizen", new Citizen(" ", " ", new Date(), login, " ", " ", " ", 5));
			return "error";
		} else {
			model.addAttribute("citizen", citizen);
		}

		return "info";

	}

	@PostMapping(value = "/info", produces = "application/json")
	public String mostrarInfo(@RequestBody Model model) {
		return "info";
	}

}