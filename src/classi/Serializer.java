package classi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import enums.Abilita;

public class Serializer {
	public static void serialize(String path, Personaggio pg) throws IOException {
		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("Nome: " + pg.getNome());
		bw.write("		Livello: " + pg.getLivello());
		bw.write("		Allineamento: " + pg.getAllineamento() + "\n");
		bw.write("Punti vita: " + pg.getHp());
		bw.write("		dado vita: " + pg.getDadiVita());
		bw.write("		iniziativa: " + pg.getIniziativa());
		bw.write("		velocità: " + pg.getVelocita() + "m");
		bw.write("		CA: " + pg.getCA() + "\n");
		bw.write("Bonus competenza: " + pg.getBonusCompetenza());
		bw.write("		Razza: " + pg.getRazza().getNome());
		bw.write("		Classe: " + pg.getClasse().getNome() + "\n");
		bw.flush();
		
		for(String car : Personaggio.caratteristicheDisponibili) {
			bw.write(car + ": " + pg.getCaratteristiche().get(car) + " (" + pg.getModifier(car) + ")\n");
		}
		
		bw.flush();
		
		bw.write("\nABILITÀ\n");
		List<Pair<Abilita, Integer>> abilita = pg.getAbilita();
		
		for(Pair<Abilita, Integer> pair : abilita) {
			if(pair.isCompetente())
				bw.write("* ");
			bw.write(pair.getFirst().getDesc() + " " + pair.getSecond() + " (" + pair.getFirst().getStat() + ")\n");
		}
		bw.flush();
		
		bw.write("\nTIRI SALVEZZA\n");
		List<Pair<String, Integer>> tiriSalvezza = pg.getTiriSalvezza();
		
		for(Pair<String, Integer> tiro : tiriSalvezza) {
			if(tiro.isCompetente())
				bw.write("* ");
			bw.write(tiro.getFirst() + " " + tiro.getSecond() + "\n");
		}
		bw.flush();
		
		bw.write("\nLINGUAGGI E COMPETENZE\n");
		String[] linguaggi = pg.getLinguaggi();
		for(String lang : linguaggi) {
			bw.write(lang + "\n");
		}
		bw.write(pg.getAltreCompetenze());
		bw.flush();
		
		if(pg.getClasse().isCaster()) {
			int[] spellSlots = pg.getSpellSlots();
			bw.write("\nSPELL SLOTS DISPONIBILI\n");
			for(int i=0; i<spellSlots.length; i++) {
				if(i==0)
					bw.write("LIVELLO 0: "  + spellSlots[i]);
				else
					bw.write("		LIVELLO " + i + ": " + spellSlots[i]);
			}
			bw.flush();
			
			List<Map<String, Incantesimo>> incantesimi = pg.getIncantesimi();
			
			bw.write("\nINCANTESIMI DISPONIBILI\n");
			
			for(int i=0; i<incantesimi.size(); i++) {
				Map<String, Incantesimo> incLivelloI = incantesimi.get(i);
				bw.write("LIVELLO " + i + ":\n");
				for(String key : incLivelloI.keySet()) {
					bw.write("-		" + key + " " + incLivelloI.get(key).getTipo() + " " + incLivelloI.get(key).getTiroSalvezza() + " " + incLivelloI.get(key).getComponenti() + " " + incLivelloI.get(key).getScuola() + "\n");
				}
			}
			bw.flush();
		}
		
		
		//tratti caratteriali, ideali, difetti e legami
		writeBGStuff(bw, pg.getTrattiCaratteriali(), "TRATTI CARATTERIALI");
		writeBGStuff(bw, pg.getIdeali(), "IDEALI");
		writeBGStuff(bw, pg.getDifetti(), "DIFETTI");
		writeBGStuff(bw, pg.getLegami(), "LEGAMI");
		
		
		bw.close();
	}
	
	public static void writeBGStuff(BufferedWriter bw, String bg, String title) throws IOException {
		if(!bg.isEmpty()) {
			bw.write("\n" + title + "\n");
			bw.write(bg + "\n");
		}
	}
}
