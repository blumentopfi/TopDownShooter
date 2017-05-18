package objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Components.Collider;
import Components.Sprite;
import main.SceneManager;
import rendering.Time;
import main.GameObject;

public class Missle extends GameObject {
	int damage ; 
	Point2D.Float movement ; 
	
	public Missle(int damage,Point2D.Float movement ){
		super("Missle") ; 
		this.addComponent(new Sprite("Assets/ProjectileSprite/Bullet.png",this));
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,0.3f,0.6f),this));
		this.damage = damage ; 
		this.movement = movement ; 
		this.Rotate(360 - (int)Math.toDegrees(Math.tan(movement.x/movement.y)));
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		Enemy collidingEnemy = (Enemy)collidingObject ; 
		
		collidingEnemy.addDamage(damage);
		this.Destroy();
		}
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + (movement.x * Time.deltaTime),this.getPosition().y + (movement.y * Time.deltaTime))) ; 
		if (this.getPosition().y < SceneManager.getInstance().getMainCamera().getViewRect().getMinY() ){
			this.Destroy();
		}
	}
}
