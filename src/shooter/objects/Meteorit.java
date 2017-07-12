package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Random;

import framework.components.RectangleCollider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.rendering.Time;

public class Meteorit extends GameObject {
	int damage = 200;
	public float speed = 3f;

	/**
	 * Constructor for meteorits.
	 * @param Name The name of this game-object.
	 */
	public Meteorit(String Name) {
		super(Name);
		Random r = new Random(System.nanoTime());
		int OurInt = r.nextInt(4) + 1;
		this.addComponent(new Sprite("/Assets/ProjectileSprite/a" + String.valueOf(OurInt) + ".png", this));
		this.setDimension(
				new Dimension(this.getTransform().getSize().width / 6, this.getTransform().getSize().height / 6));
		this.addComponent(new RectangleCollider(this));
	}

	/**
	 * Manages collisions with the player.
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer") {
			Player collidingPlayer = (Player) collidingObject;
			collidingPlayer.addDamage(damage);
			this.Destroy();
		}
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update() {
		super.Update();
		this.setPosition(
				new Point2D.Float(this.getPosition().x, this.getPosition().y + (float) (speed * Time.deltaTime)));
	}
}
