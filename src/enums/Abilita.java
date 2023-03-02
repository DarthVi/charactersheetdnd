package enums;

public enum Abilita {
	ACROBAZIA("acrobazia", "destrezza"),
	ADDESTRARE_ANIMALI("addestrare animali", "saggezza"),
	ARCANO("arcano", "intelligenza"),
	ATLETICA("atletica", "forza"),
	FURTIVITA("furtivita'", "destrezza"),
	INGANNO("inganno", "carisma"),
	INTRATTENERE("intrattenere", "carisma"),
	NATURA("natura", "intelligenza"),
	PERSUASIONE("persuasione", "carisma"),
	RAPIDITA_MANO("rapidita' di mano", "destrezza"),
	INTIMIDIRE("intimidire", "carisma"),
	INTUIZIONE("intuizione", "saggezza"),
	PERCEZIONE("percezione", "saggezza"),
	SOPRAVVIVENZA("sopravvivenza", "saggezza"),
	STORIA("storia", "intelligenza"),
	INDAGARE("indagare", "intelligenza"),
	MEDICINA("medicina", "saggezza"),
	RELIGIONE("religione", "intelligenza");
	
	private String desc;
	private String stat;
//	private static final String[] abilitaDisponibili = {"acrobazia", "addestrare animali", "arcano", "atletica", }
	
	private Abilita(String desc, String stat) {
		this.desc = desc;
		this.stat = stat;
	}

	public String getDesc() {
		return desc;
	}

	public String getStat() {
		return stat;
	}
	
	public static Abilita getAbilitaByString(String nome) {
		switch(nome) {
			case "acrobazia":
				return ACROBAZIA;
			case "addestrare animali":
				return ADDESTRARE_ANIMALI;
			case "arcano":
				return ARCANO;
			case "atletica":
				return ATLETICA;
			case "intimidire":
				return INTIMIDIRE;
			case "intuizione":
				return INTUIZIONE;
			case "percezione":
				return PERCEZIONE;
			case "sopravvivenza":
				return SOPRAVVIVENZA;
			case "storia":
				return STORIA;
			case "indagare":
				return INDAGARE;
			case "medicina":
				return MEDICINA;
			case "religione":
				return RELIGIONE;
			default:
				return ACROBAZIA;
		}
	}

}
