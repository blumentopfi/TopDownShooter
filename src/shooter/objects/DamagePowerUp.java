package shooter.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.*;
import framework.components.Sprite;
/**
 * Damage Power Up if collected ups the damage of the player
 * @author Fin
 *
 */
public class DamagePowerUp extends PowerUp {
	private int damageUp ;

	/**
	 * Constructor of the PowerUp "Damage"
	 * @param SpawnPoint The Position where the PowerUp is created.
	 * @param damageUp The increase of damage the player puts out.
	 */
	public DamagePowerUp(Point2D.Float SpawnPoint, int damageUp){
		super("DamagePowerUp") ; 
		this.damageUp = damageUp ; 
		this.setPosition(SpawnPoint);
		this.addComponent(new Sprite("Assets/PowerUps/Damage.png",this));
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
