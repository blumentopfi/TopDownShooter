package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;

public class MissleEnemy extends Missle {
		
	public MissleEnemy(int damage,Point2D.Float movement ){
		super(damage);
		this.movement = movement ; 
		
		this.addComponent(new Sprite("Assets/ProjectileSprite/E_Bullet.png",this));
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer"){
		Player collidingPlayer = (Player)collidingObject ; 
		collidingPlayer.addDamage(damage);
		this.Destroy();
		}
	}
	public void Update(){
		super.Update();  
	}
	
}
