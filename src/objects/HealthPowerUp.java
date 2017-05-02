package objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Components.Collider;
import Components.Sprite;

public class HealthPowerUp extends PowerUp {
	private int healthstrength ; 
	public HealthPowerUp(float posX, float posY, int healthstrength){
		super("HealthPowerUp") ; 
		this.healthstrength = healthstrength ; 
		this.setPosition(new Point2D.Float(posX, posY));
		this.addComponent(new Sprite("Assets/PowerUps/heart.png",this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}
	@Override
	void PowerUpUse(Player Player) {
		Player.setHealth(Player.getHealth()+healthstrength);
		System.out.println(Player.getHealth());
		this.Destroy();

	}

}
