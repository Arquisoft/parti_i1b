package dashboard.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Proposal extends Votable{
	
	private String description;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Citizen citizen;
	
	@OneToMany(mappedBy="proposal")
	private Set<Comment> comments = new HashSet<>();
	
	Proposal(){}
	
	public Proposal(String desription, Citizen citizen){
		setDescription(desription);
		Association.Propose.link(citizen, this);
		}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Comment> _getComments() {
		return comments;
	}

	public void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
}
