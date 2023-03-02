package main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import classi.Pair;
import classi.Personaggio;
import enums.Abilita;
import userinteraction.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Personaggio personaggio = new Personaggio();
		Menu.interagisci(personaggio, scanner);
		try {
			personaggio.writeToTextFile("./dnd_sheet.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		
		Map<String, Integer> caratteristiche = personaggio.getCaratteristiche();
		List<Pair<Abilita, Integer>> abilita = personaggio.getAbilita();
		String[] linguaggi = personaggio.getLinguaggi();
		
		for(String key: caratteristiche.keySet())
			System.out.println(key + " : " + caratteristiche.get(key));
		
		System.out.println();
		
		for(Pair<Abilita, Integer> abl: abilita)
			System.out.println(abl.getFirst().getDesc() + " : " + abl.getSecond());
		
		System.out.println();
		
		for(String lang : linguaggi)
			System.out.println(lang);

	}

}
