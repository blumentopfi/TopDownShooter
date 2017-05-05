package objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import objects.Missle;
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
	public Player(String PathToSprite, String Name){
		super(Name) ;
		this.addComponent(new Sprite(PathToSprite,this));
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
		this.setPosition(new Point2D.Float(5,5));	
		this.setDimension(new Dimension((int)this.getWidth()/2,(int)this.getHeight()/2));
		
	}
	
	public int getHealth(){
		return health ; 
	}
	public void setHealth(int toset){
		this.health = toset ; 
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		collidingObject.Destroy(); 
		}
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + dx, this.getPosition().y +dy));
		if (health <= 0){
			this.Destroy();
		}
	}
	
	public void shoot(){
		GameObject MyBullet = new Missle() ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y -0.5f));
		MyBullet = null ; 
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
	            dx = -0.1f;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 0.1f;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = -0.1f;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 0.1f;
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
