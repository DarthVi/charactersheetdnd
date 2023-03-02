package classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoIncantesimi {
	private List<Map<String,Incantesimo>> incantesimi;
	
	public CatalogoIncantesimi() {
		incantesimi = new ArrayList<>();
		for(int i=0; i<9; i++) {
			incantesimi.add(new HashMap<>());
		}
		
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
	}

	public List<Map<String, Incantesimo>> getIncantesimi() {
		return incantesimi;
	}
	
	
}
