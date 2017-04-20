package es.uniovi.asw.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.log.LogManager;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.password.*;

public class InsertP implements Insert{
	
	private LogManager logm=new LogManager();

	@Override
	public List<Citizen> insert(List<CitizenInfo> citizenValues) {
		List<Citizen> citizens = new ArrayList<Citizen>();
		Citizen citizen;		
		
		// Inserta y verifica en la base de datos
		for (CitizenInfo info : citizenValues) {
			//se procesa y transforma la fecha
			Date date= new Date();
			try{
				DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
				date= format.parse(info.getBirthday());
			}
			catch (ParseException e) {
				date = null;
				info.setBirthday(null);			}			
			
		
			citizen = new Citizen(info.getFirstName(),info.getLastName(),date, info.getEmail(), info.getNIF(),info.getAddress(),info.getNationality(),
					Integer.parseInt((info.getPollingStationCode().replace(".0", ""))));
			PasswordGenerator.generatePasswords(citizen);
			
			//check if contains errors and then f the citizen is already created
			if(logm.checkData(info) && !logm.checkRepetitionUser(citizen, citizens))
			{
				try {
					Parser.citizenRepository.save(citizen);
					citizens.add(citizen);
				} catch (DataIntegrityViolationException e) {
					citizen = Parser.citizenRepository.findByEmail(citizen.getEmail());
					citizens.add(citizen);
				}
			}
		}

		System.out.println("Se han registrado " + citizens.size());

		// Devuelve los votantes insertados 
		return citizens;
	}

}
