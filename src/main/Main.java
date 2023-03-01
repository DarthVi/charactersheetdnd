package main;

import java.util.Map;
import java.util.Scanner;

import classi.Personaggio;
import userinteraction.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Personaggio personaggio = new Personaggio();
		Menu.interagisci(personaggio, scanner);
		scanner.close();
		
		Map<String, Integer> caratteristiche = personaggio.getCaratteristiche();
		
		for(String key: caratteristiche.keySet())
			System.out.println(key + " : " + caratteristiche.get(key));

	}

}
