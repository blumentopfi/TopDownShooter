package shooter.objects;

import java.awt.geom.Point2D;
import java.util.Objects;

import framework.main.GameObject;

public abstract class PowerUp extends GameObject {
	private final float fallspeed = 0.01f ;

	/**
	 * Constructor for the PowerUp base-class.
	 * @param Name Name of the PowerUp.
	 */
	PowerUp(String Name){
		super(Name) ; 
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update() ; 
		this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + fallspeed));
	}

	/**
	 * Manages collision with a player.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (Objects.equals(collidingObject.getName(), "MainPlayer")){
		PowerUpUse((Player)collidingObject) ; 
		}
	}

	/**
	 * Manages effect of the PowerUp.
	 * Implemented by the different PowerUps.
	 * @param Player The player that uses the PowerUp.
	 */
	abstract void PowerUpUse(Player Player) ; 
}
