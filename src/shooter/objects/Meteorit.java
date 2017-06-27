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

	public Meteorit(String Name) {
		super(Name);
		Random r = new Random(System.nanoTime());
		int OurInt = r.nextInt(4) + 1;
		this.addComponent(new Sprite("Assets/ProjectileSprite/a" + String.valueOf(OurInt) + ".png", this));
		this.setDimension(
				new Dimension(this.getTransform().getSize().width / 6, this.getTransform().getSize().height / 6));
		this.addComponent(new RectangleCollider(this));
	}

	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer") {
			Player collidingPlayer = (Player) collidingObject;
			collidingPlayer.addDamage(damage);
			this.Destroy();
		}
	}

	public void Update() {
		super.Update();
		this.setPosition(
				new Point2D.Float(this.getPosition().x, this.getPosition().y + (float) (speed * Time.deltaTime)));
	}
}
