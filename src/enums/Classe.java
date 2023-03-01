package enums;

import classi.AbilityChoice;

public enum Classe {
	GUERRIERO("1d10", 10, 6, new String[] {"forza", "costituzione"}, 
			new AbilityChoice(2, new Abilita[] {Abilita.ACROBAZIA, Abilita.ADDESTRARE_ANIMALI, Abilita.ATLETICA, Abilita.INTIMIDIRE,
					Abilita.INTUIZIONE, Abilita.PERCEZIONE, Abilita.SOPRAVVIVENZA, Abilita.STORIA}), false),
	MAGO("1d6", 6, 4, new String[] {"intelligenza", "saggezza"}, new AbilityChoice(2, new Abilita[] {Abilita.ARCANO, 
					Abilita.INDAGARE, Abilita.INTUIZIONE, Abilita.MEDICINA, Abilita.RELIGIONE, Abilita.STORIA}), true);
	
	public static final String[] classiDisponibili = {"guerriero", "mago"};
	private String dadoVita;
	private int baseHP;
	private int hpLevelUP;
	private String[] savingThrows;
	private AbilityChoice sceltaAbilita;
	private boolean caster;
	
	private Classe(String dadoVita, int baseHP, int hpLevelUP, String[] savingThrows, AbilityChoice sceltaAbilita, boolean caster) {
		this.dadoVita = dadoVita;
		this.baseHP = baseHP;
		this.hpLevelUP = hpLevelUP;
		this.savingThrows = savingThrows;
		this.sceltaAbilita = sceltaAbilita;
		this.caster = caster;
	}

	public String getDadoVita() {
		return dadoVita;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public int getHpLevelUP() {
		return hpLevelUP;
	}

	public String[] getSavingThrows() {
		return savingThrows;
	}

	public AbilityChoice getSceltaAbilita() {
		return sceltaAbilita;
	}
	
	public boolean isCaster() {
		return caster;
	}
	
	public static Classe getClasseByName(String nome) {
		switch(nome) {
		case "guerriero":
			return Classe.GUERRIERO;
		case "mago":
			return Classe.MAGO;
		default:
			return Classe.GUERRIERO;
		}
	}
}
