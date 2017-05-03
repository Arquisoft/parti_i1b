package es.uniovi.asw.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table (name="Comment")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	private String text;
	@ManyToOne
	private Proposal proposal;
	@ManyToOne
	private Citizen citizen;
	private Date creationDate;
	private int score;
	@OneToMany(mappedBy="comment", fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	private List<VoteComment> votes= new ArrayList<VoteComment>();
	
	public Comment(){}

	public Comment(String text, Proposal proposal, Citizen citizen, Date creationDate, int score) {
		this.text = text;
		this.proposal = proposal;
		this.citizen = citizen;
		this.creationDate = creationDate;
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((proposal == null) ? 0 : proposal.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
		Comment other = (Comment) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (proposal == null) {
			if (other.proposal != null)
				return false;
		} else if (!proposal.equals(other.proposal))
			return false;
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		Association.Commenting.link(this.citizen, this);
	}

	void _setCitizen(Citizen citizen) {
	
		this.citizen=citizen;

		
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public List<VoteComment> getVotes() {
		return new ArrayList<VoteComment>();
	}

	public List<VoteComment> _getVotes() {
		return votes;
	}

	@Override
	public String toString() {
		return text;
	}
	
}
