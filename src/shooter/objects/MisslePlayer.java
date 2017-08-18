package shooter.objects;

import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * Player version of a missile.
 */
public class MisslePlayer extends Missle {
	private GameManager manager = (GameManager)SceneManager.getInstance().getGameObjectByName("Manager") ;

	/**
	 * Constructor for the missile.
 	 * @param damage The damage of the missile.
	 * @param movement The movement-vector of the missile.
	 */
	MisslePlayer(int damage, Point2D.Float movement){
		super(damage,movement);
		this.movement = movement ; 
		this.addComponent(new Sprite("/Assets/ProjectileSprite/Bullet.png",this));
	}

	/**
	 * Manages collision with enemies.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (Objects.equals(collidingObject.getName(), "Enemy")){
		Enemy collidingEnemy = (Enemy)collidingObject ; 
		collidingEnemy.addDamage(damage);
		Explosion e = manager.ExplosionsSciFi.getExplosion() ; 
		if (e != null) e.setPosition(this.getPosition());
		
		this.Destroy();
		}
		if (Objects.equals(collidingObject.getName(), "Boss")){
			Boss collidingEnemy = (Boss)collidingObject ;
			collidingEnemy.addDamage(damage);
			Explosion e = manager.ExplosionsSciFi.getExplosion() ; 
			e.setPosition(this.getPosition());
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
