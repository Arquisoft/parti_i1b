package db.management;

import db.management.impl.GetParticipantImpl;

public interface GetParticipant {

	static GetParticipantImpl getParticipantInfo() throws Exception {
		return new GetParticipantImpl();
	}
}
