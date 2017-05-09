package es.uniovi.asw.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ForbiddenWords")
public class ForbiddenWords {

	@Id
	@GeneratedValue
	private Long id;
	private String word;
	@ManyToOne
	private Configuration conf;

	public ForbiddenWords() {
	}

	public ForbiddenWords(String word, Configuration conf) {
		this.word = word;
		setConf(conf);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		ForbiddenWords other = (ForbiddenWords) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		Association.ConfigureForbiddenWords.link(conf, this);
	}

	void _setConf(Configuration conf) {
		this.conf = conf;
	}

}
