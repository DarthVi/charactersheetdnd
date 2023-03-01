package coompunds;

public class Score {
	private String caratteristica;
	private int score;
	
	
	public Score(String caratteristica, int bonus) {
		this.caratteristica = caratteristica;
		this.score = bonus;
	}
	
	public String getCaratteristica() {
		return caratteristica;
	}
	public void setCaratteristica(String caratteristica) {
		this.caratteristica = caratteristica;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int bonus) {
		this.score = bonus;
	}
	
	
}
