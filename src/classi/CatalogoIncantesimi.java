package classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoIncantesimi {
	private List<Map<String,Incantesimo>> incantesimi;
	private List<Map<String,Incantesimo>> chierico;
	
	public CatalogoIncantesimi() {
		incantesimi = initEnchantmentList();
		chierico = initEnchantmentList();
		
		incantesimi.get(0).put("Acid Splash", new Incantesimo("Acid Splash", "conjuration", "acid", "destrezza", "V,S", "You hurl a bubble of acid. "
				+ "Choose one creature you can see within range, or choose two creatures you can see within range that are within 5 feet of each other. "
				+ "A target must succeed on a Dexterity saving throw or take 1d6 acid damage."));
		
		incantesimi.get(0).put("Mage Hand", new Incantesimo("Mage Hand", "conjuration", "", "", "V,S", "A spectral, floating hand appears at a point you choose within range. The hand lasts for the duration or until you dismiss it as an action. The hand vanishes if it is ever more than 30 feet away from you or if you cast this spell again. "
				+ "You can use your action to control the hand. You can use the hand to manipulate an object, open an unlocked door or container, stow or retrieve an item from an open container, or pour the contents out of a vial. You can move the hand up to 30 feet each time you use it. "
				+ "The hand can’t attack, activate magical items, or carry more than 10 pounds."));
		incantesimi.get(0).put("Chill Touch", new Incantesimo("Chill Touch", "necromancy", "necrotic", "", "V,S", "You create a ghostly, skeletal hand in the space of a creature within range. "
				+ "Make a ranged spell attack against the creature to assail it with the chill of the grave. On a hit, the target takes 1d8 necrotic damage, and it can’t regain hit points until the start of your next turn. "
				+ "Until then, the hand clings to the target. If you hit an undead target, it also has disadvantage on attack rolls against you until the end of your next turn."));
		
		incantesimi.get(1).put("Alarm", new Incantesimo("Alarm", "abjuration", "", "", "V,S,M (a tiny bell and a piece of fine silver wire)", "You set an alarm against unwanted intrusion. Choose a door, a window, or an area within range that is no larger than a 20-foot cube. Until the spell ends, an alarm alerts you whenever a tiny or larger creature touches or enters the warded area. When you cast the spell, you can designate creatures that won’t set off the alarm. You also choose whether the alarm is mental or audible. "
				+ "A mental alarm alerts you with a ping in your mind if you are within 1 mile of the warded area. This ping awakens you if you are sleeping. An audible alarm produces the sound of a hand bell for 10 seconds within 60 feet."));
		
		incantesimi.get(1).put("Burning Hands", new Incantesimo("Burning Hands", "evocation", "fire", "destrezza", "V,S", "As you hold your hands with thumbs touching and fingers spread, a thin sheet of flames shoots forth from your outstretched fingertips. Each creature in a 15-foot cone must make a Dexterity saving throw. A creature takes 3d6 fire damage on a failed save, or half as much damage on a successful one. "
				+ "The fire ignites any flammable objects in the area that aren’t being worn or carried."));
		
		incantesimi.get(1).put("Frostbite", new Incantesimo("Frostbite", "evocation", "cold", "costituzione", "V,S", "You cause numbing frost to form on one creature that you can see within range. The target must make a Constitution saving throw. On a failed save, the target takes 1d6 cold damage, and it has disadvantage on the next weapon attack roll it makes before the end of its next turn."));
		
		chierico.get(0).put("Guidance", new Incantesimo("Guidance", "divination", "", "", "V,S", "You touch one willing creature. Once before the spell ends, the target can roll a d4 and add the number rolled to one ability check of its choice. It can roll the die before or after making the ability check. The spell then ends."));
		chierico.get(0).put("Sacred Flame", new Incantesimo("Sacred Flame", "evocation", "radiant", "destrezza", "V,S", "Flame-like radiance descends on a creature that you can see within range. The target must succeed on a Dexterity saving throw or take 1d8 radiant damage. The target gains no benefit from cover for this saving throw."));
		chierico.get(0).put("Mending", new Incantesimo("Mending", "transmutation", "", "", "V,S,M (two lodestones)", "This spell repairs a single break or tear in an object you touch, such as a broken chain link, two halves of a broken key, a torn cloak, or a leaking wineskin. As long as the break or tear is no larger than 1 foot in any dimension, you mend it, leaving no trace of the former damage."));
		
		chierico.get(1).put("Healing Word", new Incantesimo("Healing Word", "evocation", "", "", "V", "A creature of your choice that you can see within range regains hit points equal to 1d4 + your spellcasting ability modifier. This spell has no effect on undead or constructs."));
		chierico.get(1).put("Cure Wounds", new Incantesimo("Cure Wounds", "evocation", "", "", "V,S", "A creature you touch regains a number of hit points equal to 1d8 + your spellcasting ability modifier. This spell has no effect on undead or constructs."));
	}

	public List<Map<String, Incantesimo>> getIncantesimi() {
		return incantesimi;
	}
	
	public List<Map<String, Incantesimo>> getIncantesimiChierico() {
		return chierico;
	}
	
	
	private List<Map<String,Incantesimo>> initEnchantmentList() {
		List<Map<String,Incantesimo>> lista = new ArrayList<>();
		for(int i=0; i<9; i++) {
			lista.add(new HashMap<>());
		}
		return lista;
	}
	
}
