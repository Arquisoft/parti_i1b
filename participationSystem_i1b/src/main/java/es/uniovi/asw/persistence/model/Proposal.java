package es.uniovi.asw.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table (name="Proposal")
public class Proposal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String title;
	private String description;
	@ManyToOne
	private Citizen citizen; //the citizen that creates it
	private int score;
	private Date creationDate;
	@OneToMany(mappedBy="proposal", cascade={CascadeType.ALL})
	private List<Comment> comments= new ArrayList<Comment>();
	@OneToMany(mappedBy="proposal", cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<VoteProposal> votes= new ArrayList<VoteProposal>();
	@Transient
	private Boolean notified = false;
	@ManyToOne
	private Category category;

	protected Proposal() {}

	public Proposal(String title, String description, Citizen citizen, int score, Date creationDate, Category cat) {
		super();
		this.title = title;
		this.description = description;
		this.citizen = citizen;
		this.score = score;
		this.creationDate = creationDate;
		setCategory(cat);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Comment> getComments() {

		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public List<VoteProposal> getVotes() {
		return new ArrayList<VoteProposal>(votes);
	}

	List<VoteProposal> _getVotes() {
		return votes;
	}

	public void setVotes(List<VoteProposal> votes) {
		if(votes.isEmpty())
		{
			this.votes = new ArrayList<VoteProposal>();
		}
		else{
			for(VoteProposal vote: votes)
			{
				Association.Voting.linkProposal(citizen, vote, this);		
			}
		}
	}

	void _setVotes(List<VoteProposal> votes)
	{
		this.votes = votes;
	}

	void _setCitizen(Citizen citizen2) {
		this.citizen=citizen2;

	}
	public void setCitizen(Citizen citizen) {
		Association.Propose.link(citizen, this);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		Association.CategorizeProposal.link(category, this);
	}

	void _setCategory(Category category){
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public Boolean getNotified() {
		return notified;
	}

	public void setNotified(Boolean notified) {
		this.notified = notified;
	}

}
