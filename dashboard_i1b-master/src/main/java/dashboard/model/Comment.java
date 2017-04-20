package dashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Votable {

	@Column(nullable = false)
	private String comment;
	@Column(nullable = false)
	private Date date;

	@ManyToOne
	private Citizen citizen;
	
	@ManyToOne
	private Proposal proposal;

	Comment() {
	}

	public Comment(Citizen citizen, Proposal commentable,String comment) {
		setComment(comment);
		this.date = new Date();
		Association.Commenting.link(citizen, this, commentable);
	}

	public void _setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
}
