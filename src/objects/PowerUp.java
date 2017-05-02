package objects;

import java.awt.geom.Point2D;

import main.GameObject;

public abstract class PowerUp extends GameObject {
	private float fallspeed = 0.01f ; 
	public PowerUp(String Name){
		super(Name) ; 
	}
	public void Update(){
		super.Update() ; 
		this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + fallspeed));
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "MainPlayer"){
		PowerUpUse((Player)collidingObject) ; 
		}
	}
	abstract void PowerUpUse(Player Player) ; 
}
