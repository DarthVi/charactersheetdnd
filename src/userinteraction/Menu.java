package userinteraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import classi.AbilityChoice;
import classi.DiceRoller;
import classi.Personaggio;
import coompunds.Score;
import eccezioni.InvalidChoiceException;
import enums.Abilita;
import enums.Classe;
import enums.Razza;

public class Menu {
	public static int sceltaMenu(Scanner in, int min, byte exit, String error) {
		int scelta = in.nextInt();
		
		if(scelta == exit)
			System.out.println("Uscita dal programma");
		else if(scelta < min)
			System.out.println(error);
		
		return scelta;
	}
	
	public static int sceltaMenu(Scanner in, int min, String error) {
		int scelta = in.nextInt();
		
		if(scelta < min)
			System.out.println(error);
		
		return scelta;
	}
	
	public static byte sceltaMenu(Scanner in, byte min, byte max, String error) {
		byte scelta = in.nextByte();
		
		if(scelta < min || scelta > max)
			System.out.println("Scelta errata, riprovare");
		
		return scelta;
	}
	
	public static int sceltaMenu(Scanner in, int min, int max, String error) {
		int scelta = in.nextByte();
		
		if(scelta < min || scelta > max)
			System.out.println("Scelta errata, riprovare");
		
		return scelta;
	}
	
	public static boolean sceltaMenu(Scanner in) {
		boolean scelta = in.nextBoolean();
		return scelta;
	}
	
	public static String inserisciStringaEChiudiScanner(Scanner in, String regex) {
		String line = in.nextLine();
		in.close();
		if(Pattern.matches(regex, line))
		{
			return line;
		}
		throw new InvalidChoiceException("Input invalido");
		
	}
	
	public static String inserisciStringa(Scanner in, String regex) {
		String line = in.nextLine();
		if(Pattern.matches(regex, line))
		{
			return line;
		}
		
		throw new InvalidChoiceException("Input invalido");
		
	}
	
	public static String inserisciStringa(Scanner in) {
		return in.nextLine();
	}
	
	public static void interagisci(Personaggio pg, Scanner input) {
		String inputStr = "";
		boolean error = true;
		Razza razza;
		Classe classe;
		int livello = 1;
		
		do {
			System.out.println("Inserisci il nome del personaggio:");
			inputStr = inserisciStringa(input);
		}while(inputStr.isEmpty());
		
		pg.setNome(inputStr);
		
		do {
			System.out.println("Scegliere il livello: ");
			livello = Menu.sceltaMenu(input, 1, 20,  "Errore, riprovare");
		}while(livello < 1 || livello > 20);
		
		input.nextLine(); //clean buffer
		
		pg.setLivello(livello);		
		
		// Roll di dadi e piazzamento delle caratteristiche
		System.out.println("Lancio dei dadi per determinare il set di score da usare");
		int[] scores = DiceRoller.chooseArrayWithHighestScore();
		List<String> caratteristiche = new ArrayList<>(Arrays.asList(Personaggio.caratteristicheDisponibili));
		
		for(int score : scores) {
			System.out.println("Scegliere dove piazzare questo numero: " + score);
			System.out.println("Scegliere fra " + String.join(", ", caratteristiche));
			do{
				try {
					inputStr = inserisciStringa(input, String.join("|", caratteristiche));
					error = false;
				} catch(InvalidChoiceException e) {
					System.err.println("Scelta invalida, riprovare");
					error = true;
				}
			}while(error == true);
			caratteristiche.remove(inputStr);
			pg.setCaratteristica(inputStr, score);
		}
		
		//set robe by razza
		setStuffByRazza(input, pg);
		
		//set robe by classe
		setStuffByClasse(input, pg);
		pg.setCA();
		
	}
	
	public static void setStuffByRazza(Scanner input, Personaggio pg) {
		String inputStr = "";
		boolean error = true;
		Razza razza;
		
		do{
			System.out.println("Inserisci razza fra le seguenti: " + String.join(", ", Razza.razzeDisponibili));
			try {
				inputStr = inserisciStringa(input, String.join("|", Razza.razzeDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		razza = Razza.getRazzaByName(inputStr);
		
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
				inputStr = inserisciStringa(input, String.join("|", Classe.classiDisponibili));
				error = false;
			} catch(InvalidChoiceException e) {
				System.err.println("Scelta invalida, riprovare");
				error = true;
			}
		}while(error == true);
		
		classe = Classe.getClasseByName(inputStr);
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
					inputStr = inserisciStringa(input, String.join("|", abilita));
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
}
