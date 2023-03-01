package enums;

public enum Background {
	SOLDATO(new Abilita[] {Abilita.ATLETICA, Abilita.INTIMIDIRE}, new String[] {}, 
			"un tipo di gaming set, veicoli", "An insignia of rank, a trophy taken from a fallen enemy (a dagger, broken blade, or piece of a "
					+ "banner), a set of bone dice or deck of cards, a set of common clothes, and a pouch containing 10 gp"),
	SAGGIO(new Abilita[] {Abilita.ARCANO, Abilita.STORIA}, new String[] {"scelta", "scelta"}, "", "A bottle "
			+ "of black ink, a quill, a small knife, a letter from a dead colleague posing a question you have "
			+ "not yet been able to answer, a set of common clothes, and a pouch containing 10 gp");
	
	private Abilita[] abilita;
	private String[] linguaggi;
	private String bonusAttrezzi;
	private String equipaggiamento;
	
	private Background(Abilita[] abilita, String[] linguaggi, String bonusAttrezzi, String equipaggiamento) {
		this.abilita = abilita;
		this.linguaggi = linguaggi;
		this.bonusAttrezzi = bonusAttrezzi;
		this.equipaggiamento = equipaggiamento;
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
	
}
