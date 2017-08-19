package shooter.objects;


import framework.components.Animator;
import framework.components.Audio;
import framework.components.OvalCollider;
import framework.components.Sprite;
import framework.input.InputManager;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.math.MyMath;
import framework.rendering.Time;
import shooter.scenes.GameOverScene;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends GameObject {
	private float dx ;
	private float dy ;

	private final Audio a ;

	private final long FireRate = 100;
	private long NextFire = 0  ;

	private int health = 10000 ;
	private int damage = 50 ;

	enum Weapon{DOUBLE,SINGLE,TRIPLE,LASER}

    private Weapon my_weapon = Weapon.SINGLE ;

	private final GameManager manager;

	/**
	 * Constructor for the player.
	 * @param PathToSprite The path to the sprite.
	 * @param Name The name of this game-object.
	 */
	public Player(String PathToSprite, String Name){
		super(Name) ;
		manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ;
		List<BufferedImage>myAnimation = new ArrayList<>() ;
		try {
		myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/1.png")));		
		myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/2.png")));
		myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/3.png")));
		myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/4.png")));
		myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/5.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}    
		this.addComponent(new Sprite(PathToSprite,this));
		//this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,1,1),this));
		this.addComponent(new OvalCollider(this, 2.0));
		this.setPosition(new Point2D.Float(5,5));	
		this.setDimension(new Dimension((int)this.getWidth()/4,(int)this.getHeight()/4));
		this.ActionMapInputMapInitialize(); 
		this.addComponent(new Animator(myAnimation,this,50));
		a = new Audio() ;
		try {
			a.AddSound("Pew", "/Assets/Sound/laser1.wav");
			a.AddSound("Pew1", "/Assets/Sound/laser1.wav");
			a.AddSound("Pew2", "/Assets/Sound/laser1.wav");
			a.AddSound("Pew3", "/Assets/Sound/laser1.wav");
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addComponent(a);
	}

	/**
	 * Get the current damage of the player.
	 * @return The damage of the player.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Listener for mouse-pressed events.
	 * @param e The MouseEvent.
	 */
	public void MousePressed(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		System.out.println(SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(e.getPoint()));
		//if (e.getButton() == MouseEvent.BUTTON1) InputManager.Pause();
		//if (e.getButton() == MouseEvent.BUTTON2) InputManager.UnPause();
	}

	/**
	 * Set the damage of the player.
	 * @param damage The damage to set to.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Get the current health of the player.
	 * @return The current health.
	 */
	public int getHealth(){
		return health ; 
	}

	/**
	 * Set the health of the player.
	 * @param toset The health to set to.
	 */
	public void setHealth(int toset){
		this.health = toset ; 
	}

	/**
	 * Increase the type of weapon of the player.
	 */
	void UpgradeWeapon(){
		
		switch(this.my_weapon){
		case DOUBLE:
			this.my_weapon = Weapon.TRIPLE ; 
			break;
		case SINGLE:
			this.my_weapon = Weapon.DOUBLE ; 
			break;
		case TRIPLE:
			break;
		default:
			break;
		}
	}

	/**
	 * Manage collisions.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (Objects.equals(collidingObject.getName(), "Enemy")){
		collidingObject.Destroy(); 
		}
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(MyMath.Clamp(9.5f, 0,this.getPosition().x + (dx*(float)Time.deltaTime)), MyMath.Clamp(9.5f, 0,this.getPosition().y +(dy*(float)Time.deltaTime))));
		if (health <= 0){
			int final_Score = manager.getScore();
			try (FileWriter fw = new FileWriter("highscore.txt", true)) {
				fw.write(final_Score + System.lineSeparator() );
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.Destroy();
			SceneManager.getInstance().SetScene(new GameOverScene());
		}
 
	}

	/**
	 * Shoot a double-shot.
	 */
	private void shootDouble(){
		GameObject MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y -0.5f));
		MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}

	/**
	 * Shoot a single-shot.
	 */
	private void shootSingle(){
		GameObject MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}

	/**
	 * Shoot a triple-shot.
	 */
	private void shootTriple(){
		GameObject MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = new MisslePlayer(damage,new Point2D.Float(-1, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.3f, this.getPosition().y -0.5f));
		MyBullet = null ; 
		MyBullet = new MisslePlayer(damage,new Point2D.Float(+1, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.3f, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}

	/**
	 * Deduct damage from the players health.
	 * @param damage The damage to deduct.
	 */
	void addDamage(int damage){
		this.health-=damage ; 
	}

	/**
	 * Shoot bullets.
	 */
	private void shoot(){
		if (NextFire < System.currentTimeMillis() &&!InputManager.isPause()){
			
				if (!a.PlaySound("Pew")){
					if (!a.PlaySound("Pew1")){
						if (!a.PlaySound("Pew2")){
							if (!a.PlaySound("Pew3")) ;
						}
					}
				}
			
			
		switch(my_weapon){
		case DOUBLE:
			shootDouble() ; 
			break;
		case LASER:
			break;
		case SINGLE:
			shootSingle() ; 
			break;
		case TRIPLE:
			shootTriple() ; 
			break;
		default:
			break;
		}
		NextFire = System.currentTimeMillis() + FireRate ; 
		}
		
	}

	/**
	 * Initialize the Input-map for controlling the plane.
	 */
	private void ActionMapInputMapInitialize(){
		ActionMap actionMap = SceneManager.getInstance().getActionMap() ; 
		InputMap inputMap = SceneManager.getInstance().getInputMap() ; 
		Action shooting = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        shoot()  ;        
		    }
		};
		Action left = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dx = -3f ;
		    }
		};
		Action right = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	 dx = 3f ;
		    }
		};
		Action up = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dy = -3f ;
		    }
		};
		Action down = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        dy = 3f ;
		    }
		};
		Action leftr = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dx = 0f ;  
	
		    }
		};
		Action rightr = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	 dx = 0f ;     
		    	 
		    }
		};
		Action upr = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dy = 0f ;    
		    	
		    }
		};
		Action downr = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        dy = 0f ;   
		        
		    }
		};
		Action pause = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        manager.Pause();  
		        
		    }
		};
		inputMap.put(KeyStroke.getKeyStroke("SPACE"),"shoot") ; 
		actionMap.put("shoot",shooting) ;
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"),"pause") ; 
		actionMap.put("pause",pause) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left") ; 
		actionMap.put("left",left) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right") ; 
		actionMap.put("right",right) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false),"up") ; 
		actionMap.put("up",up) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false),"down") ; 
		actionMap.put("down",down) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true),"leftr") ; 
		actionMap.put("leftr",leftr) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true),"rightr") ; 
		actionMap.put("rightr",rightr) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true),"upr") ; 
		actionMap.put("upr",upr) ;
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true),"downr") ; 
		actionMap.put("downr",downr) ;
	
	}
	
}
