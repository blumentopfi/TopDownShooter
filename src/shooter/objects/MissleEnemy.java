package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;

/**
 * Enemy version of a missile.
 */
public class MissleEnemy extends Missle {

	/**
	 * Constructor for enemy-missiles.
	 * @param damage The damage of the missile.
	 * @param movement The movement-vector of the missile.
	 */
	public MissleEnemy(int damage,Point2D.Float movement ){
		super(damage,movement);
		Player main_Player = (Player)SceneManager.getInstance().getGameObjectByName("MainPlayer") ; 
		
		this.addComponent(new Sprite("Assets/ProjectileSprite/E_Bullet.png",this));
	}

	/**
	 * Manages collision with the player.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer"){
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
