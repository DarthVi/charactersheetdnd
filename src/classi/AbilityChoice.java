package classi;

import enums.Abilita;

public class AbilityChoice {
	private int numChoice;
	private Abilita[] availableChoices;
	
	public AbilityChoice(int numChoice, Abilita[] availableChoices) {
		this.numChoice = numChoice;
		this.availableChoices = availableChoices;
	}

	public int getNumChoice() {
		return numChoice;
	}

	public void setNumChoice(int numChoice) {
		this.numChoice = numChoice;
	}

	public Abilita[] getAvailableChoices() {
		return availableChoices;
	}

	public void setAvailableChoices(Abilita[] availableChoices) {
		this.availableChoices = availableChoices;
	}

}
