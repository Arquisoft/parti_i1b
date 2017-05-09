package es.uniovi.asw.persistence.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance // BY DEFAULT IS SINGLE TABLE
@DiscriminatorColumn(name = "VOTE_TYPE")
@Table(name = "Vote")
public abstract class Vote {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Citizen citizen;

	public Vote() {
	}

	public Vote(Citizen citizen) {
		this.citizen = citizen;
	}

	public Long getId() {
		return id;
	}

	void _setCitizen(Citizen citizen2) {
		this.citizen = citizen2;
	}

	public abstract void setCitizen(Citizen citizen2);

	public Citizen getCitizen() {
		return citizen;
	}

	@Override
	public abstract boolean equals(Object obj);

}
