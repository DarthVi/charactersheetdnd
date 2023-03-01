package enums;

public enum Abilita {
	ACROBAZIA("acrobazia", "destrezza"),
	ADDESTRARE_ANIMALI("addestrare animali", "saggezza"),
	ARCANO("arcano", "intelligenza"),
	ATLETICA("atletica", "forza"),
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
	
//	public static Abilita getAbilitaByString(String nome) {
//		
//	}

}
