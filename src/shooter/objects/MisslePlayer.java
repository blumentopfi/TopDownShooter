package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;

public class MisslePlayer extends Missle {
		
	public MisslePlayer(int damage,Point2D.Float movement ){
		super(damage,movement) ; 
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
	}
	
}
