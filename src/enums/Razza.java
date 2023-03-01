package enums;

import coompunds.Score;

public enum Razza {
	DRAGONIDE(new Score[] {new Score("forza", 2), new Score("carisma", 1)}, new String[] {"draconico"}, 9),
	ELFO(new Score[] {new Score("destrezza", 2)}, new String[] {"elfico"}, 9),
	GNOMO(new Score[] {new Score("intelligenza", 2)}, new String[] {"gnomesco"}, 7),
	HALFLING(new Score[] {new Score("destrezza", 2)}, new String[] {"halfling"}, 7),
	MEZZELFO(new Score[] {new Score("carisma", 2), new Score("scelta", 1), new Score("scelta", 1)}, new String[] {"elfico", "scelta"}, 9),
	UMANO(new Score[] {new Score("tutti", 1)}, new String[] {"scelta"}, 9);
	
	public static final String[] razzeDisponibili = {"dragonide", "elfo", "gnomo", "halfling", "mezzelfo", "umano"};
	public static final String[] linguaggiDisponibili = {"comune", "draconico", "elfico", "gnomesco", "halfling"};
	private Score[] bonuses;
	private String[] linguaggi;
	private int velocita;
	
	private Razza(Score[] bonuses, String[] linguaggi, int velocita) {
		this.bonuses = bonuses;
		this.linguaggi = linguaggi;
		this.velocita = velocita;
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
