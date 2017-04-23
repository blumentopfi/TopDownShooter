package objects;

import java.awt.geom.Point2D;

import main.SceneManager;
import main.GameObject;

public class Missle extends GameObject {
	public Missle(){
		super("Assets/ProjectileSprite/Bullet.png","Missle") ; 
	}
	
	public void Update(){
		super.Update();
		this.setPosition(new Point2D.Float(this.getPosition().x,this.getPosition().y - 0.1f)) ; 
	}
}
