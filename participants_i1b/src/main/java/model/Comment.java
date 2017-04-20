package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Commentable {

	private String comment;
	private Date date;

	@ManyToOne
	private Citizen citizen;
	@ManyToOne
	private Commentable commentable;

	Comment() {
	}

	public Comment(Citizen citizen, Commentable commentable, String comment) {
		this.citizen = citizen;
		this.comment = comment;
		this.date = new Date();
	}

	public void _setCommentable(Commentable commentable) {
		this.commentable = commentable;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((commentable == null) ? 0 : commentable.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (commentable == null) {
			if (other.commentable != null)
				return false;
		} else if (!commentable.equals(other.commentable))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

}
