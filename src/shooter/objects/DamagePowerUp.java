package shooter.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;

public class DamagePowerUp extends PowerUp {
	private int damageUp ; 
	public DamagePowerUp(Point2D.Float SpawnPoint, int damageUp){
		super("DamagePowerUp") ; 
		this.damageUp = damageUp ; 
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("Assets/PowerUps/Damage.png",this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}
	@Override
	void PowerUpUse(Player Player) {
		Player.setDamage(Player.getDamage()+damageUp);
		this.Destroy();
	}

}
