package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;

public class MissleEnemy extends Missle {
		
	public MissleEnemy(int damage,Point2D.Float movement ){
		super(damage,movement) ; 
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer"){
		Player collidingPlayer = (Player)collidingObject ; 
		collidingPlayer.addDamage(damage);
		this.Destroy();
		}
	}
	
}
