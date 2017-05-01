package objects;

import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import main.GameObject;
import main.SceneManager;

public class GameManager extends GameObject {
	JLabel ScoreLabel ;
	int Score = 0 ; 
	public GameManager(String Name){
		super(Name) ;
		ScoreLabel = main.SceneManager.getInstance().getMainCamera().getScoreLabel() ; 
		ScoreLabel.setText(ScoreLabel.getText() + Score);
	}
	public void Update(){
		super.Update();
		setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ; 
	}
	
	public void AddScore(int points){
		Score += points ; 
		ScoreLabel.setText("Score: " + Score);
		
	}
	
	public void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (GameObject enemies : gos) {
			if (enemies.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < 5) {
			Random random = new Random();
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			new Enemy("Assets/PlaneSprites/Enemy Bipolar.png", "Enemy", pos_X, pos_Y);
		}

	}
}
