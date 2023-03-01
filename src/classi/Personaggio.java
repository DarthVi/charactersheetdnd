package classi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import coompunds.Score;
import eccezioni.InvalidChoiceException;
import enums.Abilita;
import enums.Classe;
import enums.Razza;
import userinteraction.Menu;

public class Personaggio {

	public static final String[] caratteristicheDisponibili = {"forza", "destrezza", "costituzione", "intelligenza", "saggezza", "carisma"};
	
	private String nome;
	private Classe classe;
	private Razza razza;
	private int livello;
	private String allineamento;
	private HashMap<String, Integer> caratteristiche;
	private List<Pair<Abilita, Integer>> abilita;
	private List<Pair<String, Integer>> tiriSalvezza;
	private int CA;
	private String[] equipaggiamento;
	private String trattiCaratteriali;
	private String ideali;
	private String legami;
	private String difetti;
	private String[] privilegi;
	private String[] altreCompetenze;
	private String[] linguaggi;
	private String dadiVita;
	private int hp;
	private int iniziativa;
	private int bonusCompetenza;
	private int velocita;
	
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
	
	public void incrementaCaratteristica(String caratteristica, int incremento) {
		caratteristiche.put(caratteristica, caratteristiche.get(caratteristica) + incremento);
	}
	
	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCaratteristiche() {
		return (Map<String, Integer>) this.caratteristiche.clone();
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
	
	public void setHp(int baseHp, int incremento) {
		hp = baseHp + getModifier("costituzione");
		
		//TODO: da rivedere e verificare che aggiusta retroattivamente il modifier di costituzione
		if(livello!=1) {
			for(int i = 0; i<livello; i++)
				hp += incremento + getModifier("costituzione");
		}
			
	}
	
	public void setAbilita(Abilita[] abilita) {
		List<Abilita> abilList = Arrays.asList(abilita);
		for(Abilita abl : Abilita.values()) {
			int index = this.abilita.indexOf(new Pair<Abilita, Integer>(abl, 0));
			if(abilList.contains(abl))
			{
				this.abilita.get(index).setSecond(getModifier(abl.getStat()) + bonusCompetenza);
				this.abilita.get(index).setCompetente(true);
			}
			else
				this.abilita.get(index).setSecond(getModifier(abl.getStat()));
		}
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

}
