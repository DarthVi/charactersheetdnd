package enums;

public enum Background {
	SOLDATO("soldato", new Abilita[] {Abilita.ATLETICA, Abilita.INTIMIDIRE}, new String[] {}, 
			"un tipo di gaming set, veicoli", "An insignia of rank, a trophy taken from a fallen enemy (a dagger, broken blade, or piece of a "
					+ "banner), a set of bone dice or deck of cards, a set of common clothes, and a pouch containing 10 gp"),
	SAGGIO("saggio", new Abilita[] {Abilita.ARCANO, Abilita.STORIA}, new String[] {"scelta", "scelta"}, "", "A bottle "
			+ "of black ink, a quill, a small knife, a letter from a dead colleague posing a question you have "
			+ "not yet been able to answer, a set of common clothes, and a pouch containing 10 gp");
	
	private String nome;
	private Abilita[] abilita;
	private String[] linguaggi;
	private String bonusAttrezzi;
	private String equipaggiamento;
	
	private Background(String nome, Abilita[] abilita, String[] linguaggi, String bonusAttrezzi, String equipaggiamento) {
		this.nome = nome;
		this.abilita = abilita;
		this.linguaggi = linguaggi;
		this.bonusAttrezzi = bonusAttrezzi;
		this.equipaggiamento = equipaggiamento;
	}
	
	public String getNome() {
		return nome;
	}

	public Abilita[] getAbilita() {
		return abilita;
	}

	public String[] getLinguaggi() {
		return linguaggi;
	}

	public String getEquipaggiamento() {
		return equipaggiamento;
	}
	
	public String bonusAttrezzi() {
		return bonusAttrezzi;
	}
	
	public static Background getBackgroundByName(String nome) {
		switch(nome) {
			case "soldato":
				return SOLDATO;
			case "saggio":
				return SAGGIO;
			default:
				return SOLDATO;
		}
	}
}
