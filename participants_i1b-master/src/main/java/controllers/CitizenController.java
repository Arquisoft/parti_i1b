package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Citizen;
import repository.CitizenDao;
@Service
public class CitizenController { 
  @Autowired
  private CitizenDao citizenDao;
  
  
  @RequestMapping("/get-by-email")
  @ResponseBody
  public Citizen getParticipant(String email, String password){
    Citizen user= new Citizen();
    user = citizenDao.findByEmailAndPassword(email, password);
    return user;
  } 
} // class UserController
