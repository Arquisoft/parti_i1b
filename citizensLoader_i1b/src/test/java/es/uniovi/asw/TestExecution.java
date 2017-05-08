package es.uniovi.asw;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.LoadUsers;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;
import es.uniovi.asw.parser.InsertR;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.persistence.Insert;

public class TestExecution {
	
	private List<CitizenInfo> citizensInfo;
	private Insert inserter; 

	@Before
	public void init() throws Exception
	{
		citizensInfo = new ArrayList<CitizenInfo>();
		inserter = new InsertR();
		LoadUsers.main("-f src/main/resources/ejemplo.xlsx", "-f src/main/resources/ejemplo.xlsx");
	}
	
	private Date createDate(String dateStr)
	{
		Date date = null;
		DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		try {
			date= format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	@Test
	public void testCheckUsers() {
		
		Citizen gabriel = new Citizen("Gabriel","Reguero",createDate("31/12/1995"), "emailGabriel@test.com","55433455B", "dd","dd", 2);
		Citizen nacho = new Citizen("Nacho", "Fernandez", createDate("08/01/1995"), "emailNacho@test.com", "71729768J", "mi casa", "española", 47);		
		
		CitizenInfo citizenGabriel = new CitizenInfo("Gabriel","Reguero","31/12/1995", "emailGabriel@test.com","55433455B", "dd","dd", "2");
		CitizenInfo citizenNacho = new CitizenInfo("Nacho", "Fernandez", "08/01/1995", "emailNacho@test.com", "71729768J", "mi casa", "española", "47");
		CitizenInfo citizenFail = new CitizenInfo("fail", "fail", "fail", "fail", "fail", "fail", null, "59");
		CitizenInfo citizenRepeated = new CitizenInfo("Nacho", "Fernandez", "08/01/1995", "emailNacho@test.com", "71729768J", "mi casa", "española", "47");
				
		citizensInfo.add(citizenGabriel);
		citizensInfo.add(citizenNacho);
		citizensInfo.add(citizenFail);
		citizensInfo.add(citizenRepeated);
		
		List<Citizen> insertedCitizens = inserter.insert(citizensInfo);
		
		assertEquals(2, insertedCitizens.size());
		assertEquals(nacho, Parser.citizenRepository.findByNif("71729768J"));
		assertEquals(gabriel, Parser.citizenRepository.findByNif("55433455B"));
		
		assertEquals(true, nacho.equals(Parser.citizenRepository.findByEmail("emailNacho@test.com")));
		assertEquals(gabriel, Parser.citizenRepository.findByEmail("emailGabriel@test.com"));
	}

}
