package es.uniovi.asw.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class Configuration {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy = "conf", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ForbiddenWords> forbiddenWords = new HashSet<ForbiddenWords>();
	private int deadline; // tiempo de vida de las propuestas en dias
	// the categories that are in the table categories are the only existing
	// ones
	private int minVotesToAcceptProposal;

	public Configuration() {
	}

	public Configuration(Set<ForbiddenWords> forbiddenWords, int deadline, int minVotes) {
		setForbiddenWords(forbiddenWords);
		this.deadline = deadline;
		this.minVotesToAcceptProposal = minVotes;
	}

	public Set<ForbiddenWords> getForbiddenWords() {
		return new HashSet<ForbiddenWords>(forbiddenWords);
	}

	public Set<ForbiddenWords> _getForbiddenWords() {
		return forbiddenWords;
	}

	public void setForbiddenWords(Set<ForbiddenWords> forbiddenWords) {
		for (ForbiddenWords w : forbiddenWords) {
			Association.ConfigureForbiddenWords.link(this, w);
		}
	}

	public void _setForbiddenWords(Set<ForbiddenWords> forbiddenWords) {
		this.forbiddenWords = forbiddenWords;
	}

	public void addWord(ForbiddenWords word) {
		Association.ConfigureForbiddenWords.link(this, word);
	}

	public void removeWord(ForbiddenWords word) {
		Association.ConfigureForbiddenWords.unlink(this, word);
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getMinVotesToAcceptProposal() {
		return minVotesToAcceptProposal;
	}

	public void setMinVotesToAcceptProposal(int minVotesToAcceptProposal) {
		this.minVotesToAcceptProposal = minVotesToAcceptProposal;
	}
}
