package objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import objects.Missle;
import rendering.Time;
import main.SceneManager;
import main.GameObject;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import Components.Collider;
import Components.Sprite;
public class Player extends GameObject {
	Point direction ; 
	float dx ; 
	float dy ; 
	long FireRate = 100  ; 
	long NextFire = 0  ;
	int health = 200 ; 
	int damage = 50 ; 
	enum Weapon{DOUBLE,SINGLE,TRIPLE,LASER} ; 
	Weapon my_weapon = Weapon.SINGLE ; 
	public Player(String PathToSprite, String Name){
		super(Name) ;
		this.addComponent(new Sprite(PathToSprite,this));
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
		
		this.setPosition(new Point2D.Float(5,5));	
		this.setDimension(new Dimension((int)this.getWidth()/2,(int)this.getHeight()/2));
		
	}
	public int getDamage() {
		return damage;
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
		}
	}
	private void shootDouble(){
		GameObject MyBullet = new Missle(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y -0.5f));
		MyBullet = new Missle(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}
	private void shootSingle(){
		GameObject MyBullet = new Missle(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}
	private void shootTriple(){
		GameObject MyBullet = new Missle(damage,new Point2D.Float(0, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = new Missle(damage,new Point2D.Float(-2, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.3f, this.getPosition().y -0.5f));
		MyBullet = null ; 
		MyBullet = new Missle(damage,new Point2D.Float(+2, -4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.3f, this.getPosition().y -0.5f));
		MyBullet = null ; 
	}
	public void shoot(){
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
		
	}
	  public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        
	        if (key == KeyEvent.VK_SPACE){
	        	if (NextFire < System.currentTimeMillis() ){
	        	shoot() ;   
	        	NextFire = System.currentTimeMillis() + FireRate ; 
	        	}
	        }
	        if (key == KeyEvent.VK_LEFT) {
	            dx = -2f ;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 2f ; 
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = -2f ; 
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 2f ; 
	        }
	    }
	  public void keyReleased(KeyEvent e) {
	        
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = 0;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 0;
	        }
	    }
	
}
