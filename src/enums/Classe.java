package enums;

import coompunds.AbilityChoice;

public enum Classe {
	GUERRIERO("guerriero", "1d10", 10, 6, new String[] {"forza", "costituzione"}, 
			new AbilityChoice(2, new Abilita[] {Abilita.ACROBAZIA, Abilita.ADDESTRARE_ANIMALI, Abilita.ATLETICA, Abilita.INTIMIDIRE,
					Abilita.INTUIZIONE, Abilita.PERCEZIONE, Abilita.SOPRAVVIVENZA, Abilita.STORIA}), false),
	MAGO("mago", "1d6", 6, 4, new String[] {"intelligenza", "saggezza"}, new AbilityChoice(2, new Abilita[] {Abilita.ARCANO, 
					Abilita.INDAGARE, Abilita.INTUIZIONE, Abilita.MEDICINA, Abilita.RELIGIONE, Abilita.STORIA}), true),
	CHIERICO("chierico", "1d8", 8, 5, new String[] {"saggezza", "carisma"}, new AbilityChoice(2, new Abilita[] {Abilita.MEDICINA, Abilita.PERSUASIONE,
			Abilita.INTUIZIONE, Abilita.RELIGIONE, Abilita.STORIA}), true);
	
//	public static final String[] classiDisponibili = {"guerriero", "mago"};
	private String nome;
	private String dadoVita;
	private int baseHP;
	private int hpLevelUP;
	private String[] savingThrows;
	private AbilityChoice sceltaAbilita;
	private boolean caster;
	
	private Classe(String nome, String dadoVita, int baseHP, int hpLevelUP, String[] savingThrows, AbilityChoice sceltaAbilita, boolean caster) {
		this.nome = nome;
		this.dadoVita = dadoVita;
		this.baseHP = baseHP;
		this.hpLevelUP = hpLevelUP;
		this.savingThrows = savingThrows;
		this.sceltaAbilita = sceltaAbilita;
		this.caster = caster;
	}
	
	public String getNome() {
		return nome;
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
		case "chierico":
			return Classe.CHIERICO;
		default:
			return Classe.GUERRIERO;
		}
	}
}
