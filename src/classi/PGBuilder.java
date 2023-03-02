package classi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import coompunds.AbilityChoice;
import coompunds.Score;
import eccezioni.InvalidChoiceException;
import enums.Abilita;
import enums.Background;
import enums.Classe;
import enums.Razza;
import userinteraction.Menu;

public class PGBuilder {
	
	public static void setLivello(Scanner input, Personaggio pg) {
		int livello = 1;
		
		do {
			System.out.println("Scegliere il livello: ");
			livello = Menu.sceltaMenu(input, 1, 20,  "Errore, riprovare");
		}while(livello < 1 || livello > 20);
		
		input.nextLine(); //clean buffer
		
		pg.setLivello(livello);
	}
	public static void scegliNome(Scanner input, Personaggio pg) {
		String inputStr = "";
		do {
			System.out.println("Inserisci il nome del personaggio:");
			inputStr = Menu.inserisciStringa(input);
		}while(inputStr.isEmpty());
		
		pg.setNome(inputStr);
	}
	
	public static void rollCaratteristiche(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		System.out.println("Lancio dei dadi per determinare il set di score da usare");
		int[] scores = DiceRoller.chooseArrayWithHighestScore();
		List<String> caratteristiche = new ArrayList<>(Arrays.asList(Personaggio.caratteristicheDisponibili));
		
		System.out.println("Questi sono i punteggi disponibili");
		System.out.print("[");
		for(int i=0; i<scores.length; i++)
		{
			if(i==0)
				System.out.print(scores[i]);
			else
				System.out.print(" " + scores[i]);
		}
		System.out.print("]\n");
		
		for(int score : scores) {
			System.out.println("Scegliere dove piazzare questo numero: " + score);
			System.out.println("Scegliere fra " + String.join(", ", caratteristiche));
			do{
				try {
					inputStr = Menu.inserisciStringa(input, String.join("|", caratteristiche));
					error = false;
				} catch(InvalidChoiceException e) {
					System.err.println("Scelta invalida, riprovare");
					error = true;
				}
			}while(error == true);
			caratteristiche.remove(inputStr);
			pg.setCaratteristica(inputStr, score);
		}
	}
	
	public static void setStuffByRazza(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		Razza razza;
		
		List<String> razzeDisponibili = Arrays.asList(Razza.values()).stream().map(rz -> rz.getNome()).toList();
		do{
			System.out.println("Inserisci razza fra le seguenti: " + String.join(", ", razzeDisponibili));
			try {
				inputStr = Menu.inserisciStringa(input, String.join("|", razzeDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		razza = Razza.getRazzaByName(inputStr);
		pg.setRazza(razza);
		
		//set caratteristiche by razza
		List<String> caratteristicheDisp = new ArrayList<>(Arrays.asList(Personaggio.caratteristicheDisponibili));
		for(Score bonus : razza.getBonuses()) {
			if(bonus.getCaratteristica().equals("tutti"))
				for(String car : Personaggio.caratteristicheDisponibili)
					pg.incrementaCaratteristica(car, bonus.getScore());
			else if(!bonus.getCaratteristica().equals("scelta"))
				pg.incrementaCaratteristica(bonus.getCaratteristica(), bonus.getScore());
			else
			{
				System.out.println("Scegli una caratteristica da incrementare:");
				String scelta = "";
				error = true;
				do {
					try
					{
						scelta = Menu.inserisciStringa(input, String.join("|", caratteristicheDisp));
						error = false;
						pg.incrementaCaratteristica(scelta, bonus.getScore());
						caratteristicheDisp.remove(scelta);
					}catch(InvalidChoiceException e) {
						System.err.println(e.getMessage());
						error = true;
					}
				}while(error == true);
			}
		}
		
		//set linguaggi e velocita
		String[] linguaggi = razza.getLinguaggi();
		String[] nuoviLinguaggiDaAggiungere = scegliLinguaggi(input, linguaggi, null);
		pg.setAttributiByRazza(nuoviLinguaggiDaAggiungere, razza.getVelocita());
	}
	
	public static String[] scegliLinguaggi(Scanner input, String[] linguaggi, String[] linguaggiGiaImparati) {
		String inputStr = "";
		boolean error = true;
		List<String> langDisp = new ArrayList<>(Arrays.asList(Razza.linguaggiDisponibili));
		//rimuovo il comune (lo imparano tutti di default)
		langDisp.remove("comune");
		//rimuovo i linguaggi gia imparati
		if(linguaggiGiaImparati != null) {
			for(String lang : linguaggiGiaImparati)
				langDisp.remove(lang);
		}
		for(String lang : linguaggi) {
			langDisp.remove(lang);
		}
		for(int i=0; i<linguaggi.length; i++) {
			if(linguaggi[i].equals("scelta")) {
				do {
					System.out.println("Scegli un liguaggio fra i seguenti: " + String.join(", ", langDisp));
					try
					{
						inputStr = Menu.inserisciStringa(input, String.join("|", langDisp));
						linguaggi[i] = inputStr;
						error = false;						
					}catch(InvalidChoiceException e) {
						System.err.println(e.getMessage());
						error = true;
					}
				}while(error == true);
				langDisp.remove(inputStr);
			}
		}
		return linguaggi;
	}
	
	public static void setStuffByClasse(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		Classe classe;
		List<String> classiDisponibili = Arrays.asList(Classe.values()).stream().map(cl -> cl.getNome()).toList();
		do{
			System.out.println("Inserisci una classe fra le seguenti: " + String.join(", ", classiDisponibili));
			try {
				inputStr = Menu.inserisciStringa(input, String.join("|", classiDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		classe = Classe.getClasseByName(inputStr);
		pg.setClasse(classe);
		pg.setDadiVita(classe.getDadoVita());
		pg.setHp(classe.getBaseHP(), classe.getHpLevelUP());
		
		AbilityChoice ablChoice = classe.getSceltaAbilita();
		Abilita[] abilitaScelte = new Abilita[ablChoice.getNumChoice()]; // memorizzo qui le scelte
		//usata per rimuovere le opzioni disponibili
		List<String> abilita = new ArrayList<>(Arrays.asList(ablChoice.getAvailableChoices()).stream().map(item -> item.getDesc()).toList());
		//usata come copia per ottenere líndex della scelta
		List<String> ablChoiceCopia = new ArrayList<>(Arrays.asList(ablChoice.getAvailableChoices()).stream().map(item -> item.getDesc()).toList());
		
		for(int i=0; i<ablChoice.getNumChoice(); i++) {
			System.out.println("Scegliere fra le seguenti abilita: " + String.join(", ", abilita));
			do {
				try {
					inputStr = Menu.inserisciStringa(input, String.join("|", abilita));
					error = false;
				}catch(InvalidChoiceException e) {
					System.err.println("Scelta invalida, riprovare");
					error = true;
				}
				abilita.remove(inputStr);
				int index = ablChoiceCopia.indexOf(inputStr);
				abilitaScelte[i] = ablChoice.getAvailableChoices()[index];
			}while(error == true);
		}
		pg.initAbilita(abilitaScelte);
		pg.setTiriSalvezza(classe.getSavingThrows());
	}
	
	/*
	 * Se l'abilita del background e' gia' stata selezionata, allora chiede all'utente di sceglierne un'altra
	 */
	public static void setBackground(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		Background background;
		List<String> backgroundDisponibili = Arrays.asList(Background.values()).stream().map(cl -> cl.getNome()).toList();
		do{
			System.out.println("Scegli uno fra i seguenti background: " + String.join(", ", backgroundDisponibili));
			try {
				inputStr = Menu.inserisciStringa(input, String.join("|", backgroundDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		background = Background.getBackgroundByName(inputStr);
		pg.setBackground(background);
		//numero di abilita a scelta
		int numAbil = background.getAbilita().length;
		//abilita dal background
		
		//abilita gia' presenti nel pg con e senza competenza
		List<Abilita> abilitaConCompetenza = pg.getAbilitaConCompetenza();
		List<String> abilitaSenzaCompetenza = new ArrayList<>(pg.getAbilitaSenzaCompetenza().stream().map(a -> a.getDesc()).toList());
		
		for(int i=0; i<numAbil; i++) {
			if(abilitaConCompetenza.contains(background.getAbilita()[i]))
			{
				do{
					System.out.println("Scegli una fra le seguenti abilita: " + String.join(", ", abilitaSenzaCompetenza));
					try {
						inputStr = Menu.inserisciStringa(input, String.join("|", abilitaSenzaCompetenza));
						error = false;
					} catch(InvalidChoiceException e) {
						System.err.println("Scelta invalida, riprovare");
						error = true;
					}
				}while(error == true);
				
				Abilita nuovaAbilita = Abilita.getAbilitaByString(inputStr);
				pg.setAbilitaAggiungendoCompetenza(nuovaAbilita);
				abilitaSenzaCompetenza.remove(inputStr);
			}
			else
				pg.setAbilitaAggiungendoCompetenza(background.getAbilita()[i]);
		}
		
		String[] linguaggiGiaImparati = pg.getLinguaggi();
		//set linguaggi aggiuntivi
		String[] linguaggiAggiuntivi = background.getLinguaggi();
		linguaggiAggiuntivi = scegliLinguaggi(input, linguaggiAggiuntivi, linguaggiGiaImparati);
		
		for(String lang : linguaggiAggiuntivi)
			pg.aggiungiLinguaggio(lang);
		
		pg.aggiungiAltreCompetenze(background.bonusAttrezzi());
		pg.aggiungiEquipaggiamento(background.getEquipaggiamento());
	}
	
	public static void setCaster(Scanner input, Personaggio pg) {
		if(pg.getClasse().isCaster())
		{
			String inputStr = "";
			boolean error = true;
			pg.setSpellSlot();
			CatalogoIncantesimi catalogo = new CatalogoIncantesimi();
			
			List<Integer> numSpell = pg.getLearnableSpells();
			List<Map<String, Incantesimo>> incantesimiAcquisiti = new ArrayList<>();
			
			for(@SuppressWarnings("unused") Integer _i : numSpell) {
				incantesimiAcquisiti.add(new HashMap<>());
			}
			
			switch(pg.getClasse()) {
				case MAGO:
					//per ogni livello di incantesimo che posso imparare
					for(int i=0; i<numSpell.size(); i++) {
						Map<String, Incantesimo> mappa = catalogo.getIncantesimi().get(i);
						int learnable = numSpell.get(i);
						List<String> inc = new ArrayList<>(mappa.keySet());
						
						//per ogni incantesimo in singolo livello
						for(int j=0; j<learnable; j++) {
							do{
								System.out.println("Scegli uno fra i seguenti incantesimi di livello " + i + ": " + String.join(", ", inc));
								try {
									inputStr = Menu.inserisciStringa(input, String.join("|", inc));
									error = false;
								} catch(InvalidChoiceException e) {
									System.err.println("Scelta invalida, riprovare");
									error = true;
								}
							}while(error == true);
							
							incantesimiAcquisiti.get(i).put(inputStr, mappa.get(inputStr));
							inc.remove(inputStr);
						}
					}
					break;
				case CHIERICO:
					incantesimiAcquisiti = catalogo.getIncantesimiChierico();
					break;
				default:
					break;
			}
			
			
			pg.setIncantesimi(incantesimiAcquisiti);
		}
		
	}
	
	public static void setAllineamento(Scanner input, Personaggio pg) {
		String[] regexLaw = {"lawful", "neutral", "chaotic"};
		String[] regexMoral = {"good", "neutral", "evil"};
		String stringLaw = "";
		String stringMoral = "";
		boolean error;
		
		do{
			System.out.println("Inserisci il tuo allineamento sulla legge: " + String.join(" ", regexLaw));
			try {
				stringLaw = Menu.inserisciStringa(input, String.join("|", regexLaw));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		do{
			System.out.println("Inserisci il tuo allineamento sulla morale: " + String.join(" ", regexMoral));
			try {
				stringMoral = Menu.inserisciStringa(input, String.join("|", regexMoral));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		pg.calcolaAllineamento(stringLaw, stringMoral);
	}
	
	public static void setBGStory(Scanner input, Personaggio pg) {
		String trattiCaratteriali = getStringFromPrompt("Inserisci i tratti caratteriali e dai invio per confermare", input);
		String ideali = getStringFromPrompt("Inserisci gli ideali e dai invio", input);
		String legami = getStringFromPrompt("Inserisci i legami e dai invio", input);
		String difetti = getStringFromPrompt("Inserisci i difetti e dai invio", input);
		
		pg.setTrattiCaratteriali(trattiCaratteriali);
		pg.setIdeali(ideali);
		pg.setLegami(legami);
		pg.setDifetti(difetti);
	}
	
	public static void salvaSuFile(Scanner input, Personaggio pg) {
		String filepath = getStringFromPrompt("Inserisci il path in cui memorizzare il character sheet (es. ./dnd_sheet.txt)", input);
		
		try {
			pg.writeToTextFile(filepath);
		} catch (IOException e) {
			System.err.println("errore sul salvataggio del file: " + e.getMessage() + " " + e.getCause());
			System.out.println("Tentativo di salvarlo in posizione di default");
			try {
				pg.writeToTextFile("./dnd_sheet.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static String getStringFromPrompt(String prompt, Scanner input) {
		System.out.println(prompt);
		return input.nextLine();		
	}
	
	public static void setInitiativeAndCA(Personaggio pg) {
		pg.setCA();
		pg.setIniziativa();
	}
}
