package participants;

import model.Citizen;

public interface GetParticipantInfo {

	public Citizen getParticipantInfo(String email, String password);
}
