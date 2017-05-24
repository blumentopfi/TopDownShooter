package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;

public class Missle extends GameObject {
	int damage ; 
	Point2D.Float movement ; 
	
	public Missle(int damage,Point2D.Float movement ){
		super("Missle") ; 
		this.addComponent(new Sprite("Assets/ProjectileSprite/Bullet.png",this));
		this.addComponent(new Collider(new Rectangle2D.Float(0,0,0.3f,0.6f),this));
		this.damage = damage ; 
		this.movement = movement ; 
		this.Rotate(360 - (int)Math.toDegrees(Math.tan(movement.x/movement.y)));
	}
	

	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x + (movement.x * Time.deltaTime),this.getPosition().y + (movement.y * Time.deltaTime))) ; 
		if (this.getPosition().y < SceneManager.getInstance().getMainCamera().getViewRect().getMinY() ){
			this.Destroy();
		}
	}
}
