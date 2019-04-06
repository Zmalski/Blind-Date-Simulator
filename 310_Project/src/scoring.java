
public class scoring {
	
private int score;

	public scoring() {
		score = 0;
	}


	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public void updateScore(int score) {
		this.score = this.score+score;
	}
	
	
	
}
