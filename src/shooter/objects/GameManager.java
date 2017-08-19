package shooter.objects;

import framework.input.InputManager;
import framework.main.GameObject;
import framework.main.SceneManager;
import shooter.UI.HealthBar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

public class GameManager extends GameObject {
	private final JLabel ScoreLabel ;

	private final JProgressBar m_healthbar = new JProgressBar() ;
	private final JProgressBar m_bossbar = new JProgressBar() ;
	private final JLabel BossLabel ;
	private Boolean boss = false;
	private final Player main_player  ;
	private final JLabel PauseLabel1 = new JLabel() ;
	private final JLabel PauseLabel2 = new JLabel() ;
	private int Score = 0 ;
	private int killedEnemies = 0;
	int Wave = 1;
	private int num_threads = 0 ;
	private boolean waves;
	private Boss m_boss ;
	private final int i = 6 ;
	private final int j = 6 ;
	private final JPanel[][] panelHolder = new JPanel[i][j] ;
	final ObjectPool ExplosionsSciFi = new ObjectPool() ;
	final ObjectPool ExplosionsPlane = new ObjectPool() ;

	/**
	 * Returns the player of the game.
	 * @return The player.
	 */
	public Player getPlayer(){
		return this.main_player ;
	}

	//Maximum number of enemies that are alive at the same time.
	private static final int MAX_ENEMY_NUMBER = 5;

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
		new Thread(() -> ExplosionsSciFi.init(true)).start();
		new Thread(() -> ExplosionsPlane.init(false)).start();
		main_player = 	new Player("/Assets/PlaneSprites/1.png","MainPlayer") ;
		ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
		ScoreLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		ScoreLabel.setText(ScoreLabel.getText() + Score);
		BossLabel = new JLabel("Score: ",JLabel.LEFT);
		BossLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		BossLabel.setText("BOSS:");
		m_healthbar.setUI(new HealthBar());
		m_bossbar.setUI(new HealthBar());
		JLabel stats = new JLabel();
		AddToPanel(0,5,ScoreLabel);
		AddToPanel(0,0, stats) ;
		AddToPanel(0,1,m_healthbar) ;
		AddToPanel(0,3,BossLabel);
		AddToPanel(0,4,m_bossbar) ;
		stats.setFont(new Font("Verdana", Font.PLAIN, 25));
		stats.setText("HP:");
		m_healthbar.setMaximum(main_player.getHealth());
		m_healthbar.setValue(main_player.getHealth());
		m_bossbar.setVisible(false);
		BossLabel.setVisible(false);
		JPanel gameView ;
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
	private void initPause(){
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
	void Pause(){
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
		if (Thread.activeCount() > this.num_threads){
			this.num_threads = Thread.activeCount() ; 
			System.out.print("Thread: ");
			System.out.println(this.num_threads);
		}
		if (waves) setupEnemies(SceneManager.getInstance().GetAllGameObjectsInScene()) ;		
		drawHealth() ; 
		if (killedEnemies >= (Wave * 10) && waves) {
			if (boss != null){
			if (!boss) {
				System.out.println("Boss created");
				m_boss = new Boss((3000 * 2), -3);
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
	private void drawHealth(){
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
	void AddScore(int points){
		Score += points ; 
		ScoreLabel.setText("Score: " + Score);
		
	}

	/**
	 * Generate Enemies and position them at random coordinates above the viewport.
	 * @param gos The whole list of game-objects.
	 */
	private void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (GameObject enemies : gos) {
			if (enemies.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < MAX_ENEMY_NUMBER && !boss) {
			Random random = new Random(System.nanoTime());

			double a = Wave;
			double chance = Math.pow(a, 3.00);
			double check = random.nextInt(100) + 1;
			if (chance <= check) {
				float pos_X = random.nextInt(9 - 1 + 1) + 1;
				float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
				new SimpleEnemy("/Assets/PlaneSprites/Biploar_Y.png", "Enemy", pos_X, pos_Y);
				Meteorit m = new Meteorit();
				m.setPosition(new Point2D.Float(pos_X, pos_Y));
			}
			if (chance > check) {
				float pos_X = random.nextInt(9 - 1 + 1) + 1;
				float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
				new AdvancedEnemy(pos_X, pos_Y);
				Meteorit m = new Meteorit();
				m.setPosition(new Point2D.Float(pos_X, pos_Y));
			}


		}
	}

	/**
	 * Increase the kill-counter of the player by one.
	 */
	void addKilledEnemy() {
		killedEnemies++;
	}

	/**
	 * The player killed the current boss.
	 */
	void killedBoss() {
		boss = false;
		Wave++;
		killedEnemies = 0;
		m_bossbar.setVisible(false);
		BossLabel.setVisible(false);
	}
}
