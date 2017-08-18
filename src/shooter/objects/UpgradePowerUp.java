package shooter.objects;

import framework.components.RectangleCollider;
import framework.components.Sprite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class UpgradePowerUp extends PowerUp {
	/**
	 * Constructor for the PowerUp "Upgrade".
	 * @param SpawnPoint The position where the PowerUp is created.
	 */
	UpgradePowerUp(Point2D.Float SpawnPoint){
		super("UpgradePowerUp") ;  
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("/Assets/PowerUps/Star.png",this));
        this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}

	/**
	 * Upgrade the weapon of the player.
	 * @param Player The player that uses the PowerUp.
	 */
	@Override
	void PowerUpUse(Player Player) {
		Player.UpgradeWeapon(); 
		this.Destroy();
	}

}
