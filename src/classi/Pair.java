package classi;

public class Pair<T, U> {
	private T first;
	private U second;
	boolean competente;
	
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
		this.competente = false;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public U getSecond() {
		return second;
	}

	public void setSecond(U second) {
		this.second = second;
	}
	
	public void setCompetente(boolean competenza) {
		this.competente = competenza;
	}
	
	public boolean isCompetente() {
		return competente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
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
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		return true;
	}
	
	
	
}
