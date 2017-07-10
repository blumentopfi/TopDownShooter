package shooter.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import framework.input.InputManager;
import framework.main.GameObject;
import framework.main.SceneManager;
import shooter.UI.HealthBar;

import static shooter.objects.Enemy.random;

public class GameManager extends GameObject {
	JLabel ScoreLabel ;
	JLabel FPSLabel ;

	JProgressBar m_healthbar = new JProgressBar() ;
	JProgressBar m_bossbar = new JProgressBar() ;
	JLabel Stats ;
	JLabel BossLabel ; 
	Boolean boss = false;
	Boolean bossKilled = false;
	Player main_player  ;
	JButton PlayButton = new JButton() ; 
	JLabel PauseLabel1 = new JLabel() ; 
	JLabel PauseLabel2 = new JLabel() ; 
	JPanel gameView ; 
	int Score = 0 ;
	int killedEnemies = 0;
	int Wave = 1;
	private boolean waves;
	Boss m_boss ; 
	int i = 6 ; 
	int j = 6 ; 
	JPanel[][] panelHolder = new JPanel[i][j] ; 
	public ObjectPool ExplosionsSciFi = new ObjectPool() ; 
	public ObjectPool ExplosionsPlane = new ObjectPool() ;

	/**
	 * Returns the player of the game.
	 * @return The player.
	 */
	public Player getPlayer(){
		return this.main_player ;
	}

	//Maximum number of enemies that are alive at the same time.
	public static int MAX_ENEMY_NUMBER = 5;

	/**
	 * Constructor for GameManager
	 * @param Name The name of this game-object.
	 */
	public GameManager(String Name){
		super(Name) ;
		SceneManager.getInstance().getMainCamera().OverrideLayout(new GridLayout(i,j,0,0));
		for(int m = 0; m < i; m++) {
			   for(int n = 0; n < j; n++) {
			      panelHolder[m][n] = new JPanel();
			      panelHolder[m][n].setOpaque(false);
			      SceneManager.getInstance().getMainCamera().AddGUIElement(panelHolder[m][n]);
			   }
			}
		new Thread(() -> {
			ExplosionsSciFi.init(true);  
		}).start();
		new Thread(() -> {
			ExplosionsPlane.init(false);  
		}).start();
		main_player = 	new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
		ScoreLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		ScoreLabel.setText(ScoreLabel.getText() + Score);
		BossLabel = new JLabel("Score: ",JLabel.LEFT);
		BossLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		BossLabel.setText("BOSS:");
		m_healthbar.setUI(new HealthBar());
		m_bossbar.setUI(new HealthBar());
		Stats = new JLabel() ; 
		AddToPanel(0,5,ScoreLabel);
		AddToPanel(0,0,Stats) ;
		AddToPanel(0,1,m_healthbar) ;
		AddToPanel(0,3,BossLabel);
		AddToPanel(0,4,m_bossbar) ;
		Stats.setFont(new Font("Verdana", Font.PLAIN, 25));
		Stats.setText("HP:");
		m_healthbar.setMaximum(main_player.getHealth());
		m_healthbar.setValue(main_player.getHealth());
		m_bossbar.setVisible(false);
		BossLabel.setVisible(false);
		gameView = SceneManager.getInstance().getMainCamera().getGameView() ;
		initPause() ; 
	}

	/**
	 * Add a component to the main Panel.
	 * @param x Coordinate X.
	 * @param y Coordinate Y.
	 * @param c The component to add to the panel.
	 */
	private void AddToPanel(int x, int y,JComponent c){
		this.panelHolder[x][y].add(c);
	}

	/**
	 * Initiate the pause of the complete game.
	 */
	public void initPause(){
		this.PauseLabel1.setForeground(Color.CYAN);
		this.PauseLabel1.setText("PAU");
		PauseLabel1.setFont(new Font("Verdana", Font.PLAIN, 79));
		AddToPanel(2,2,PauseLabel1);
		this.PauseLabel2.setForeground(Color.CYAN);
		this.PauseLabel2.setText("SED");
		PauseLabel2.setFont(new Font("Verdana", Font.PLAIN, 79));
		AddToPanel(2,3,PauseLabel2);
		Pause() ; 
	
	}

	/**
	 * Initiate the start of a new Wave.
	 */
	public void StartWaves(){
		waves = true ;
	}

	/**
	 * Pause the complete game.
	 */
	public void Pause(){
		if (PauseLabel1.isVisible()){
			InputManager.UnPause();
		}else{
			InputManager.Pause();  
		}
		PauseLabel1.setVisible(!PauseLabel1.isVisible());
		PauseLabel2.setVisible(!PauseLabel2.isVisible());
		
	}


	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update();
		if (waves) setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ;		
		drawHealth() ; 
		if (killedEnemies >= (Wave * 10)) {
			if (boss != null){
			if (!boss) {
				System.out.println("Killed Enemies: " + killedEnemies);
				System.out.println("Boss created");
				m_boss = new Boss("Assets/PlaneSprites/Enemy B-17.png", "Boss", (3000 * 2), 5, -3);
				boss = true;
				m_bossbar.setMaximum(m_boss.getHealth());
				m_bossbar.setVisible(true);
				m_bossbar.setValue(m_boss.getHealth());
				BossLabel.setVisible(true);
			}
			}

		}
	}

	/**
	 * Dislay the health of the player and of bosses as bars.
	 */
	public void drawHealth(){
		if (main_player != null) m_healthbar.setValue(this.main_player.getHealth()) ; 
		if (m_boss != null) m_bossbar.setValue(this.m_boss.getHealth());
	}

	/**
	 * Returns the current score of the player.
	 * @return The current score.
	 */
	public int getScore(){
		return Score ;
	}

	/**
	 * Add points to the score of the player and update the score-label.
	 * @param points The points to add to the score.
	 */
	public void AddScore(int points){
		Score += points ; 
		ScoreLabel.setText("Score: " + Score);
		
	}

	/**
	 * Generate Enemies and position them at random coordinates above the viewport.
	 * @param gos The whole list of game-objects.
	 */
	public void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (int i = 0 ; i< gos.size() ; i++) {
			GameObject enemies = gos.get(i) ;
			if (enemies.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < MAX_ENEMY_NUMBER && !boss) {
			System.out.println(MAX_ENEMY_NUMBER);
			Random random = new Random(System.nanoTime());

			double a = Wave;
			double chance = Math.pow(a, 3.00);
			double check = random.nextInt(100) + 1;
			if (chance <= check) {
				float pos_X = random.nextInt(9 - 1 + 1) + 1;
				float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
				new SimpleEnemy("Assets/PlaneSprites/Biploar_Y.png", "Enemy", pos_X, pos_Y);
				Meteorit m = new Meteorit("Meteo");
				m.setPosition(new Point2D.Float(pos_X, pos_Y));
			}
			if (chance > check) {
				float pos_X = random.nextInt(9 - 1 + 1) + 1;
				float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
				new AdvancedEnemy("Assets/PlaneSprites/Advanced/JU87B2.png", "Enemy", pos_X, pos_Y);
				Meteorit m = new Meteorit("Meteo");
				m.setPosition(new Point2D.Float(pos_X, pos_Y));
			}


		}
	}

	/**
	 * Increase the kill-counter of the player by one.
	 */
	public void addKilledEnemy() {
		killedEnemies++;
	}

	/**
	 * The player killed the current boss.
	 */
	public void killedBoss() {
		bossKilled = true;
		boss = false;
		Wave++;
		killedEnemies = 0;
		m_bossbar.setVisible(false);
		BossLabel.setVisible(false);
	}
}
