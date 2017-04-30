package objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Components.Collider;
import Components.Sprite;
import main.SceneManager;
import main.GameObject;

public class Missle extends GameObject {
	public Missle(){
		super("Missle") ; 
		this.addComponent(new Sprite("Assets/ProjectileSprite/Bullet.png",this));
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,0.3f,0.6f),this));
		
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		collidingObject.Destroy(); 
		this.Destroy();
		}
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x,this.getPosition().y - 0.1f)) ; 
		if (this.getPosition().y < SceneManager.getInstance().getMainCamera().getViewRect().getMinY() ){
			this.Destroy();
		}
	}
}
