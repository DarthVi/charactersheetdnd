package classi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import coompunds.Score;
import eccezioni.InvalidChoiceException;
import enums.Abilita;
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
		
		do{
			System.out.println("Inserisci razza fra le seguenti: " + String.join(", ", Razza.razzeDisponibili));
			try {
				inputStr = Menu.inserisciStringa(input, String.join("|", Razza.razzeDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		razza = Razza.getRazzaByName(inputStr);
		pg.setRazza(razza);
		
		//set caratteristiche by razza
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
						scelta = Menu.inserisciStringa(input, String.join("|", Personaggio.caratteristicheDisponibili));
						error = false;
						pg.incrementaCaratteristica(scelta, bonus.getScore());						
					}catch(InvalidChoiceException e) {
						System.err.println(e.getMessage());
						error = true;
					}
				}while(error == true);
			}
		}
		
		//set linguaggi e velocita
		String[] linguaggi = razza.getLinguaggi();
		List<String> langDisp = new ArrayList<>(Arrays.asList(Razza.linguaggiDisponibili));
		langDisp.remove("comune");
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
		pg.setAttributiByRazza(linguaggi, razza.getVelocita());
	}
	
	public static void setStuffByClasse(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		Classe classe;
		do{
			System.out.println("Inserisci una classe fra le seguenti: " + String.join(", ", Classe.classiDisponibili));
			try {
				inputStr = Menu.inserisciStringa(input, String.join("|", Classe.classiDisponibili));
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
		pg.setAbilita(abilitaScelte);
		pg.setTiriSalvezza(classe.getSavingThrows());
	}
	
	public static void setInitiativeAndCA(Personaggio pg) {
		pg.setCA();
		pg.setIniziativa();
	}
}
