package shooter.objects;

import framework.components.RectangleCollider;
import framework.components.Sprite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * Damage Power Up if collected ups the damage of the player
 * @author Fin
 *
 */
class DamagePowerUp extends PowerUp {
	private final int damageUp ;

	/**
	 * Constructor of the PowerUp "Damage"
	 * @param SpawnPoint The Position where the PowerUp is created.
	 *
	 */
	DamagePowerUp(Point2D.Float SpawnPoint){
		super("DamagePowerUp") ; 
		this.damageUp = 50;
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("/Assets/PowerUps/Damage.png",this));
        this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,1,1),this));
        this.setDimension(new Dimension((int)this.getWidth()/3,(int)this.getHeight()/3));
	}

	/**
	 * Add the increase of damage to the players damage-output.
	 * @param Player The player that receives the PowerUp.
	 */
	@Override
	void PowerUpUse(Player Player) {
		Player.setDamage(Player.getDamage()+damageUp);
		this.Destroy();
	}

}
