package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

	@Id
	@GeneratedValue
	public Long id;

	private boolean value;

	@ManyToOne
	private Citizen citizen;
	@ManyToOne
	private Proposal proposal;

	Vote() {
	}

	public Vote(Citizen citizen, Proposal proposal, boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void _setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	
	

}
