package enums;

import coompunds.Score;

public enum Razza {
	DRAGONIDE("dragonide", new Score[] {new Score("forza", 2), new Score("carisma", 1)}, new String[] {"draconico"}, 9),
	ELFO("elfo", new Score[] {new Score("destrezza", 2)}, new String[] {"elfico"}, 9),
	GNOMO("gnomo", new Score[] {new Score("intelligenza", 2)}, new String[] {"gnomesco"}, 7),
	HALFLING("halfling", new Score[] {new Score("destrezza", 2)}, new String[] {"halfling"}, 7),
	MEZZELFO("mezzelfo", new Score[] {new Score("carisma", 2), new Score("scelta", 1), new Score("scelta", 1)}, new String[] {"elfico", "scelta"}, 9),
	UMANO("umano", new Score[] {new Score("tutti", 1)}, new String[] {"scelta"}, 9);
	
//	public static final String[] razzeDisponibili = {"dragonide", "elfo", "gnomo", "halfling", "mezzelfo", "umano"};
	public static final String[] linguaggiDisponibili = {"comune", "draconico", "elfico", "gnomesco", "halfling"};
	private String nome;
	private Score[] bonuses;
	private String[] linguaggi;
	private int velocita;
	
	private Razza(String nome, Score[] bonuses, String[] linguaggi, int velocita) {
		this.nome = nome;
		this.bonuses = bonuses;
		this.linguaggi = linguaggi;
		this.velocita = velocita;
	}
	
	public String getNome() {
		return nome;
	}

	public Score[] getBonuses() {
		return bonuses;
	}

	public String[] getLinguaggi() {
		return linguaggi;
	}

	public int getVelocita() {
		return velocita;
	}
	
	public static Razza getRazzaByName(String nomeRazza) {
		switch(nomeRazza) {
		case "dragonide":
			return Razza.DRAGONIDE;
		case "elfo":
			return Razza.ELFO;
		case "gnomo":
			return Razza.GNOMO;
		case "halfling":
			return Razza.HALFLING;
		case "mezzelfo":
			return Razza.MEZZELFO;
		case "umano":
			return Razza.UMANO;
		default:
			return Razza.UMANO;
		}
	}
	
}
