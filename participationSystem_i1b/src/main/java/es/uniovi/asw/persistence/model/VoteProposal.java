package es.uniovi.asw.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P")
@Table(name="Proposal")
public class VoteProposal extends Vote{

	@ManyToOne
	private Proposal proposal;
	
	public VoteProposal(){}
	
	public VoteProposal(Citizen citizen, Proposal prop) {
		super(citizen);
		this.proposal = prop;
		setProposal(prop);
	}
	
	public void setProposal(Proposal propuesta){
		Association.Voting.linkProposal(super.getCitizen(), this, propuesta);
	}
	
	void _setProposal(Proposal proposal2) {
		this.proposal=proposal2;
	}
	
	public Proposal getProposal() {
		return proposal;
	}

	@Override
	public void setCitizen(Citizen citizen2) {
			Association.Voting.linkProposal(citizen2, this, proposal);		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteProposal other = (VoteProposal) obj;
		if(super.getCitizen().equals(other.getCitizen())) {
			if (proposal == null)
			{
				if (other.proposal != null)
					return false;
			} 
			else if (!proposal.equals(other.proposal))
				return false;
			return true;
		}
		return false;
	}

}
