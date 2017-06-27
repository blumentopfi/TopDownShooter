package shooter.objects;

import java.awt.Font;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import framework.main.GameObject;
import framework.main.SceneManager;

public class GameManager extends GameObject {
	JLabel ScoreLabel ;
	JLabel FPSLabel ;


	JLabel Stats ;
	Boolean boss = false;
	Boolean bossKilled = false;

	int Score = 0 ;
	int killedEnemies = 0;
	int Wave = 1;
	private boolean waves;


	public static int MAX_ENEMY_NUMBER = 5;

	public GameManager(String Name){
		super(Name) ;
		ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
		FPSLabel = new JLabel("FPS: ") ; 
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(ScoreLabel);
		ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
		FPSLabel.setFont(new Font("Serif", Font.PLAIN, 35));
		ScoreLabel.setText(ScoreLabel.getText() + Score);
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(FPSLabel);
		Stats = new JLabel() ; 
		framework.main.SceneManager.getInstance().getMainCamera().AddGUIElement(Stats);
		Stats.setFont(new Font("Serif", Font.PLAIN, 35));
	}
	public void StartWaves(){
		waves = true ;
	}
	public void Update(){
		super.Update();
		if (waves) setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ;
		//SceneManager.getInstance().PrintAllGameObjectsByName();
		FPSLabel.setText("FPS: " + (int)framework.main.SceneManager.getInstance().getMainCamera().m_fpscounter.fps()) ; 
		Player Player = (Player)framework.main.SceneManager.getInstance().getGameObjectByName("MainPlayer") ;
		if (Player != null) Stats.setText("HP: " + Player.getHealth() + "\n" + "DMG: " + Player.getDamage());


		if (killedEnemies >= (Wave * 10)) {
			if (!boss) {
				System.out.println("Killed Enemies: " + killedEnemies);
				System.out.println("Boss created");
				new Boss("Assets/PlaneSprites/Enemy B-17.png", "Boss", 5, -3);
				boss = true;
			}

		}
	}
	public int getScore(){
		return Score ;
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
		if (enemyCounter < MAX_ENEMY_NUMBER && !boss) {
			Random random = new Random(System.nanoTime());
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			new AdvancedEnemy("Assets/PlaneSprites/Advanced/JU87B2.png", "Enemy", pos_X, pos_Y);
			Meteorit m = new Meteorit("Meteo") ;
			m.setPosition(new Point2D.Float(pos_X, pos_Y));
		}

	}
	public void addKilledEnemy() {
		killedEnemies++;
	}
	public void killedBoss() {
		bossKilled = true;
		boss = false;
		Wave++;
		killedEnemies = 0;
	}
}
