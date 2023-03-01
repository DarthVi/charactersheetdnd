package classi;

public class Incantesimo {
	private String nome;
	private String scuola;
	private String tipo;
	private String tiroSalvezza;
	private String componenti;
	private String descrizione;
	
	public Incantesimo(String nome, String scuola, String tipo, String tiroSalvezza, String componenti,
			String descrizione) {
		this.nome = nome;
		this.scuola = scuola;
		this.tipo = tipo;
		this.tiroSalvezza = tiroSalvezza;
		this.componenti = componenti;
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getScuola() {
		return scuola;
	}

	public void setScuola(String scuola) {
		this.scuola = scuola;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTiroSalvezza() {
		return tiroSalvezza;
	}

	public void setTiroSalvezza(String tiroSalvezza) {
		this.tiroSalvezza = tiroSalvezza;
	}

	public String getComponenti() {
		return componenti;
	}

	public void setComponenti(String componenti) {
		this.componenti = componenti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Incantesimo other = (Incantesimo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
