package dashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(VoteKey.class)
public class Vote {

	@Id
	@ManyToOne
	private Citizen citizen;

	@Id
	@ManyToOne
	private Votable votable;

	private boolean value;

	Vote() {
	}

	public Vote(Citizen citizen, Votable votable, boolean value) {
		setValue(value);
		Association.Voting.link(citizen, this, votable);
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void _setVotable(Votable votable) {
		this.votable = votable;
	}

	public void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public Votable getVotable() {
		return votable;
	}
	
	public VoteKey getID(){
		return new VoteKey(citizen.getId(),votable.getId());
	}
}
