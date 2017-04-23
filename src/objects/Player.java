package objects;

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

import Components.Collider;
public class Player extends GameObject {
	Point direction ; 
	float dx ; 
	float dy ; 
	long FireRate = 100 ; 
	long NextFire = 0  ; 
	public Player(String PathToSprite, String Name){
		super(PathToSprite,Name) ;
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		collidingObject.Destroy(); 
		}
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + dx, this.getPosition().y +dy));
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
