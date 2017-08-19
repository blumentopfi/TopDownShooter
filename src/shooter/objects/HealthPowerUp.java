package shooter.objects;

import framework.components.RectangleCollider;
import framework.components.Sprite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class HealthPowerUp extends PowerUp {
	private final int healthstrength ;

	/**
	 * Constructor for the PowerUp "Health"
	 * @param SpawnPoint The position where the PowerUp is created.
	 *
	 */
	HealthPowerUp(Point2D.Float SpawnPoint){
		super("HealthPowerUp") ; 
		this.healthstrength = 1000;
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("/Assets/PowerUps/Health.png",this));
        this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}

	/**
	 * Add the increase of health to the health of the player.
	 * @param Player The player that receives the increase.
	 */
	@Override
	void PowerUpUse(Player Player) {
		Player.setHealth(Player.getHealth()+healthstrength);
		this.Destroy();

	}
}
