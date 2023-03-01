package userinteraction;

import java.util.Scanner;
import java.util.regex.Pattern;

import classi.PGBuilder;
import classi.Personaggio;
import eccezioni.InvalidChoiceException;

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
		
		PGBuilder.scegliNome(input, pg);
		
		PGBuilder.setLivello(input, pg);		
		
		// Roll di dadi e piazzamento delle caratteristiche
		PGBuilder.rollCaratteristiche(input, pg);
		
		//set robe by razza
		PGBuilder.setStuffByRazza(input, pg);
		
		//set robe by classe
		PGBuilder.setStuffByClasse(input, pg);
		//altri set indipendenti dalla classe
		PGBuilder.setInitiativeAndCA(pg);
		
	}
}
