package objects;

import java.awt.Font;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import main.GameObject;
import main.SceneManager;

public class GameManager extends GameObject {
	JLabel ScoreLabel ;
	JLabel FPSLabel ; 

	JLabel Stats ; 

	int Score = 0 ;
	public static int MAX_ENEMY_NUMBER = 5;

	public GameManager(String Name){
		super(Name) ;
		ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
		FPSLabel = new JLabel("FPS: ") ; 
		main.SceneManager.getInstance().getMainCamera().AddGUIElement(ScoreLabel);
		ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
		FPSLabel.setFont(new Font("Serif", Font.PLAIN, 35));
		ScoreLabel.setText(ScoreLabel.getText() + Score);
		main.SceneManager.getInstance().getMainCamera().AddGUIElement(FPSLabel);
		Stats = new JLabel() ; 
		main.SceneManager.getInstance().getMainCamera().AddGUIElement(Stats);
		Stats.setFont(new Font("Serif", Font.PLAIN, 35));
	}
	public void Update(){
		super.Update();
		setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ; 
		FPSLabel.setText("FPS: " + (int)main.SceneManager.getInstance().getMainCamera().m_fpscounter.fps()) ; 
		Player Player = (Player)main.SceneManager.getInstance().getGameObjectByName("MainPlayer") ; 
		Stats.setText("HP: " + Player.getHealth() + "\n" + "DMG: " + Player.getDamage());
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
		if (enemyCounter < MAX_ENEMY_NUMBER) {
			Random random = new Random(System.nanoTime());
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			//float pos_X = 5;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			//float pos_Y = 1;
			new Enemy("Assets/PlaneSprites/Enemy Bipolar.png", "Enemy", pos_X, pos_Y);
		}

	}
}
