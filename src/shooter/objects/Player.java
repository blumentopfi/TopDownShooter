package shooter.objects;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import shooter.objects.Missle;
import shooter.scenes.GameOverScene;
import shooter.scenes.MenuScene;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import framework.components.Animator;
import framework.components.Collider;
import framework.components.OvalCollider;
import framework.components.RectangleCollider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;
public class Player extends GameObject {
	Point direction ; 
	float dx ; 
	float dy ; 
	long FireRate = 100  ; 
	long NextFire = 0  ;
	int health = 20000 ; 
	int damage = 50 ;  
	enum Weapon{DOUBLE,SINGLE,TRIPLE,LASER} ; 
	Weapon my_weapon = Weapon.SINGLE ; 
	public Player(String PathToSprite, String Name){
		super(Name) ;
		List<BufferedImage>myAnimation = new ArrayList<BufferedImage>() ; 
		try {
		myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/1.png")));
		myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/2.png")));
		myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}    
		this.addComponent(new Sprite(PathToSprite,this));
		//this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,1,1),this));
		this.addComponent(new OvalCollider(this));
		//this.setPosition(new Point2D.Float(5,5));	
		this.setDimension(new Dimension((int)this.getWidth()/2,(int)this.getHeight()/2));
		this.ActionMapInputMapInitialize(); 
		this.addComponent(new Animator(myAnimation,this,50));
	}
	public int getDamage() {
		return damage;
	}
	public void MousePressed(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		System.out.println(SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(e.getPoint()));
	} 

	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getHealth(){
		return health ; 
	}
	public void setHealth(int toset){
		this.health = toset ; 
	}
	
	public void UpgradeWeapon(){
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
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		collidingObject.Destroy(); 
		}
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + (dx*Time.deltaTime), this.getPosition().y +(dy*Time.deltaTime)));
		if (health <= 0){
			this.Destroy();
			SceneManager.getInstance().SetScene(new GameOverScene());
		}
 
	}

	private void shootDouble(){
		GameObject MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y -0.5f));
		MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}
	private void shootSingle(){
		GameObject MyBullet = new MisslePlayer(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}
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
	
	public void addDamage(int damage){
		this.health-=damage ; 
	}
	public void shoot(){
		if (NextFire < System.currentTimeMillis() ){
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
	public void ActionMapInputMapInitialize(){
		ActionMap actionMap = SceneManager.getInstance().getActionMap() ; 
		InputMap inputMap = SceneManager.getInstance().getInputMap() ; 
		Action shooting = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        shoot()  ;        
		    }
		};
		Action left = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dx = -2f ;    
		    }
		};
		Action right = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	 dx = 2f ;         
		    }
		};
		Action up = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	  dy = -2f ;         
		    }
		};
		Action down = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        dy = 2f ;       
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
		inputMap.put(KeyStroke.getKeyStroke("SPACE"),"shoot") ; 
		actionMap.put("shoot",shooting) ;
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
