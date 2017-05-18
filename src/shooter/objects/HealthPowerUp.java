package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;

public class HealthPowerUp extends PowerUp {
	private int healthstrength ; 
	public HealthPowerUp(Point2D.Float SpawnPoint, int healthstrength){
		super("HealthPowerUp") ; 
		this.healthstrength = healthstrength ; 
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("Assets/PowerUps/Health.png",this));
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
