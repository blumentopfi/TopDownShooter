package objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Components.Collider;
import Components.Sprite;

public class UpgradePowerUp extends PowerUp {
	public UpgradePowerUp(Point2D.Float SpawnPoint){
		super("UpgradePowerUp") ;  
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("Assets/PowerUps/Star.png",this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}
	@Override
	void PowerUpUse(Player Player) {
		Player.UpgradeWeapon(); 
		this.Destroy();
	}

}
