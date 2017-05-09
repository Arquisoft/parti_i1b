package model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Proposal extends Commentable {

	private String description;

	@ManyToOne
	private Citizen citizen;
	@OneToMany(mappedBy = "proposal")
	private Set<Vote> votes;

	Proposal() {
	}

	public Proposal(String desription, Citizen citizen) {
		this.description = desription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Vote> _getVotes() {
		return votes;
	}

	public void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposal other = (Proposal) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
