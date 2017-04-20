package dashboard.model;

import java.io.Serializable;

public class VoteKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Long citizen;
	Long votable;
	
	VoteKey() {}

	public VoteKey(Long citizen, Long votable) {
		this.citizen = citizen;
		this.votable = votable;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((votable == null) ? 0 : votable.hashCode());
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
		VoteKey other = (VoteKey) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (votable == null) {
			if (other.votable != null)
				return false;
		} else if (!votable.equals(other.votable))
			return false;
		return true;
	}
	
	

}
