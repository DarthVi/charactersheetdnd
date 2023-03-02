package classi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Abilita;
import enums.Background;
import enums.Classe;
import enums.Razza;
import interfaces.CustomSerializable;

public class Personaggio implements CustomSerializable {
	
	public static final String[] caratteristicheDisponibili = {"forza", "destrezza", "costituzione", "intelligenza", "saggezza", "carisma"};
	
	private String nome;
	private Classe classe;
	private Razza razza;
	private Background background;
	private int livello;
	private String allineamento;
	private Map<String, Integer> caratteristiche;
	private List<Pair<Abilita, Integer>> abilita;
	private List<Pair<String, Integer>> tiriSalvezza;
	private int CA;
	private String[] equipaggiamento;
	private String trattiCaratteriali;
	private String ideali;
	private String legami;
	private String difetti;
//	private String[] privilegi;
	private String altreCompetenze;
	private String[] linguaggi;
	private String dadiVita;
	private int hp;
	private int iniziativa;
	private int bonusCompetenza;
	private int velocita;
	private List<Map<String, Incantesimo>> incantesimi;
	private int[] spellSlots;
	
	public Personaggio(String nome) {
		this.nome = nome;
		init();
	}

	public Personaggio() {
		init();
	}
	
	private void init() {
		abilita = new ArrayList<>(0);
		tiriSalvezza = new ArrayList<>(0);
		
		for(Abilita abl : Abilita.values())
			abilita.add(new Pair<>(abl, 0));
		
		caratteristiche = new HashMap<>();
		for(String car: caratteristicheDisponibili)
		{
			tiriSalvezza.add(new Pair<>(car, 0));
			caratteristiche.put(car, 0);
		}
		
		incantesimi = new ArrayList<>(0);
		
		for(int i=0; i<10; i++) {
			incantesimi.add(new HashMap<>());
		}
		
		spellSlots = new int[10];
		
		altreCompetenze = "";
		ideali = "";
		difetti = "";
		trattiCaratteriali = "";
		legami = "";
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCaratteristica(String caratteristica, int valore) {
		caratteristiche.put(caratteristica, valore);
	}
	
	public int getModifier(String caratteristica) {
		return (int) Math.floor((caratteristiche.get(caratteristica) - 10) / 2); 
	}
	
	public void setRazza(Razza razza) {
		this.razza = razza;
	}
	
	public void setAttributiByRazza(String[] linguaggi, int velocita) {	
		this.linguaggi = new String[linguaggi.length + 1];
		this.linguaggi[0] = "comune";
		for(int i=0; i<linguaggi.length; i++) {
			this.linguaggi[i + 1] = linguaggi[i];
		}
		setVelocita(velocita);
	}
	
	public void aggiungiEquipaggiamento(String equip) {
		if(this.equipaggiamento == null)
		{
			this.equipaggiamento = new String[1];
			this.equipaggiamento[0] = equip;
		}
		else
		{
			String[] tempCopy = this.equipaggiamento.clone();
			this.equipaggiamento = new String[tempCopy.length + 1];
			for(int i=0; i<tempCopy.length; i++)
				this.equipaggiamento[i] = tempCopy[i];
			this.equipaggiamento[tempCopy.length] = equip;
		}
	}
	
	public void aggiungiLinguaggio(String linguaggio) {
		String[] tempCopy = this.linguaggi.clone();
		this.linguaggi = new String[tempCopy.length + 1];
		for(int i=0; i<tempCopy.length; i++)
			this.linguaggi[i] = tempCopy[i];
		this.linguaggi[tempCopy.length] = linguaggio;
	}
	
	public void incrementaCaratteristica(String caratteristica, int incremento) {
		caratteristiche.put(caratteristica, caratteristiche.get(caratteristica) + incremento);
	}
	
	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}
	
	public Map<String, Integer> getCaratteristiche() {
		return this.caratteristiche;
	}
	
	public void setDadiVita(String dado) {
		this.dadiVita = dado;
	}
	
	public String getDadiVita() {
		return this.dadiVita;
	}
	
	public void setLivello(int livello) {
		this.livello = livello;
		if(livello <=4)
			bonusCompetenza = 2;
		else if(livello <=8)
			bonusCompetenza = 3;
		else if(livello <= 12)
			bonusCompetenza = 4;
		else if(livello <= 16)
			bonusCompetenza = 5;
		else
			bonusCompetenza = 6;
	}
	
	public int getLivello() {
		return livello;
	}
	
	public void setHp(int baseHp, int incremento) {
		hp = baseHp + getModifier("costituzione");
		
		//TODO: da rivedere e verificare che aggiusta retroattivamente il modifier di costituzione
		if(livello!=1) {
			for(int i = 0; i<livello; i++)
				hp += incremento + getModifier("costituzione");
		}
			
	}
	
	public void initAbilita(Abilita[] abilita) {
		List<Abilita> abilList = Arrays.asList(abilita);
		for(Abilita abl : Abilita.values()) {
			int index = this.abilita.indexOf(new Pair<Abilita, Integer>(abl, 0));
			if(abilList.contains(abl))
				setAbilitaAggiungendoCompetenza(abl);
			else
				this.abilita.get(index).setSecond(getModifier(abl.getStat()));
		}
	}
	
	public List<Abilita> getAbilitaConCompetenza() {
		return abilita.stream().filter(abPair -> abPair.isCompetente()).map(abPair -> abPair.getFirst()).toList();
	}
	
	public List<Abilita> getAbilitaSenzaCompetenza() {
		return abilita.stream().filter(abPair -> !abPair.isCompetente()).map(abPair -> abPair.getFirst()).toList();
	}
	
	public void setAbilitaAggiungendoCompetenza(Abilita abilita) {
		int index = this.abilita.indexOf(new Pair<Abilita, Integer>(abilita, 0));
		this.abilita.get(index).setSecond(getModifier(abilita.getStat()) + bonusCompetenza);
		this.abilita.get(index).setCompetente(true);
	}
	
	public void setTiriSalvezza(String[] tiriSalvezza) {
		List<String> salvList = Arrays.asList(tiriSalvezza);
		for(String str : Personaggio.caratteristicheDisponibili) {
			int index = this.tiriSalvezza.indexOf(new Pair<String, Integer>(str, 0));
			if(salvList.contains(str))
			{
				this.tiriSalvezza.get(index).setSecond(getModifier(str) + bonusCompetenza);
				this.tiriSalvezza.get(index).setCompetente(true);
			}
			else
				this.tiriSalvezza.get(index).setSecond(getModifier(str));
		}
	}
	
	public void setCA() {
		this.CA = 10 + getModifier("destrezza");
	}
	
	public void setIniziativa() {
		this.iniziativa = getModifier("destrezza");
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Razza getRazza() {
		return razza;
	}

	public int getCA() {
		return CA;
	}

	public int getHp() {
		return hp;
	}

	public int getIniziativa() {
		return iniziativa;
	}

	public int getVelocita() {
		return velocita;
	}

	public String getAltreCompetenze() {
		return altreCompetenze;
	}
	
	public List<Pair<Abilita, Integer>> getAbilita() {
		return abilita;
	}
	
	public String[] getLinguaggi() {
		return linguaggi;
	}
	
	public String[] getEquipaggiamento() {
		return equipaggiamento;
	}

	public void aggiungiAltreCompetenze(String altreCompetenze) {
		if(this.altreCompetenze.isEmpty())
			this.altreCompetenze = altreCompetenze;
		else
			this.altreCompetenze += " - " + altreCompetenze;
	}
	
	public void setSpellSlot() {
		if(classe.isCaster())
			switch(classe) {
				case MAGO:
					setMago();
					break;
				default:
			}
	}
	
	private void setMago() {
		if(livello==1) {
			spellSlots[0] = 3; //tre trucchetti
			spellSlots[1] = 2; //due incantesimi di primo livello		
		}
	}
	
	public List<Integer> getLearnableSpells() {
		List<Integer> learnable = new ArrayList<>(0);
		
		switch(livello) {
			case 1:
				switch(classe) {
					case MAGO:
						learnable.add(3);
						learnable.add(2);
						return learnable;
					default:
				}
				default:
		}
		
		return learnable;
	}
	
	public void setIncantesimi(List<Map<String, Incantesimo>> incantesimi) {
		this.incantesimi = incantesimi;
	}
	

	public String getAllineamento() {
		return allineamento;
	}

	public void setAllineamento(String allineamento) {
		this.allineamento = allineamento;
	}
	
	public List<Pair<String, Integer>> getTiriSalvezza() {
		return tiriSalvezza;
	}
	
	public int getBonusCompetenza() {
		return bonusCompetenza;
	}
	
	public int[] getSpellSlots() {
		return spellSlots;
	}

	public List<Map<String, Incantesimo>> getIncantesimi() {
		return incantesimi;
	}

	public String getTrattiCaratteriali() {
		return trattiCaratteriali;
	}

	public void setTrattiCaratteriali(String trattiCaratteriali) {
		this.trattiCaratteriali = trattiCaratteriali;
	}

	public String getIdeali() {
		return ideali;
	}

	public void setIdeali(String ideali) {
		this.ideali = ideali;
	}

	public String getLegami() {
		return legami;
	}

	public void setLegami(String legami) {
		this.legami = legami;
	}

	public String getDifetti() {
		return difetti;
	}

	public void setDifetti(String difetti) {
		this.difetti = difetti;
	}

	public void calcolaAllineamento(String law, String moral) {
		if(law.equals("neutral") && moral.equals("neutral"))
			setAllineamento("neutral");
		else
			setAllineamento(law + " " + moral);
	}

	@Override
	public void writeToTextFile(String path) throws IOException {
		Serializer.serialize(path, this);
		
	}

}
