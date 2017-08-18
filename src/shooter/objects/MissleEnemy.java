package shooter.objects;

import framework.components.Sprite;
import framework.main.GameObject;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * Enemy version of a missile.
 */
public class MissleEnemy extends Missle {

	/**
	 * Constructor for enemy-missiles.
	 * @param damage The damage of the missile.
	 * @param movement The movement-vector of the missile.
	 */
	MissleEnemy(int damage, Point2D.Float movement){
		super(damage,movement);
		this.addComponent(new Sprite("/Assets/ProjectileSprite/E_Bullet.png",this));
	}

	/**
	 * Manages collision with the player.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (Objects.equals(collidingObject.getName(), "MainPlayer")){
		Player collidingPlayer = (Player)collidingObject ; 
		collidingPlayer.addDamage(damage);
		this.Destroy();
		}
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update();  
	}
	
}
