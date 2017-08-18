package shooter.objects;

import framework.components.RectangleCollider;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Base-Class for missiles.
 */
public class Missle extends GameObject {
	int damage ; 
	Point2D.Float movement ;


	/**
	 * Constructor of Missile.
	 * @param damage The damage of the missile.
	 * @param movement The movement-vector of the missile.
	 */
	Missle(int damage, Point2D.Float movement){
		super("Missle") ;
		this.addComponent(new RectangleCollider(new Rectangle2D.Float(0,0,0.3f,0.3f),this));
		this.damage = damage ; 
		this.movement = movement ; 
		this.Rotate(360 - (int)Math.toDegrees(Math.tan(movement.x/movement.y)));
	}


	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + (movement.x * (float)Time.deltaTime),this.getPosition().y + (movement.y * (float)Time.deltaTime))) ; 
		if (!SceneManager.getInstance().getMainCamera().getViewRect().contains(this.getPosition())){
			this.Destroy();
		}
	}
}
