/*package participants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Citizen;
import model.CitizenDao;
import participants.GetParticipantInfo;

@Service("getParticipantInfo")
public class GetParticipantInfoImpl implements GetParticipantInfo{

	@Autowired
	CitizenDao citizenDao;

	@Override
	@RequestMapping(value = "/citizen", 
			method = RequestMethod.POST)
	public Citizen getParticipantInfo(@RequestBody String email, @RequestBody String password) {		
		return null;
	}
}*/