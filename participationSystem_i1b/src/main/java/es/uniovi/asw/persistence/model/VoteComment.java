package es.uniovi.asw.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("C")
@Table(name = "VoteComment")
public class VoteComment extends Vote {

	@ManyToOne
	private Comment comment;

	public VoteComment() {

	}

	public VoteComment(Citizen citizen, Comment comment, int value) {
		super(citizen, value);
		this.comment = comment;
		Association.Voting.linkComment(super.getCitizen(), this, comment);
	}

	public void setComment(Comment comment) {
		Association.Voting.linkComment(super.getCitizen(), this, comment);
	}

	void _setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}

	@Override
	public void setCitizen(Citizen citizen2) {
		Association.Voting.linkComment(citizen2, this, comment);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
		VoteComment other = (VoteComment) obj;
		if (super.getCitizen().equals(other.getCitizen())) {
			if (comment == null) {
				if (other.comment != null)
					return false;
			} else if (!comment.equals(other.comment))
				return false;
			return true;
		}
		return false;
	}

}
