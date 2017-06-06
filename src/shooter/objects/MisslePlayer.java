package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;

public class MisslePlayer extends Missle {
		
	public MisslePlayer(int damage,Point2D.Float movement ){
		super(damage,movement) ; 
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		Enemy collidingEnemy = (Enemy)collidingObject ; 
		collidingEnemy.addDamage(damage);
		Explosion e = new Explosion("Explosion") ;
		e.setPosition(this.getPosition());
		//e.setDimension(new Dimension((int)e.getWidth()/3,(int)e.getHeight()/3));
		this.Destroy();
		}
		if (collidingObject.getName() == "Boss"){
			Boss collidingEnemy = (Boss)collidingObject ;
			collidingEnemy.addDamage(damage);
			Explosion e = new Explosion("Explosion") ;
			e.setPosition(this.getPosition());
			this.Destroy();
		}
	}
	public void Update(){
		super.Update();
	}
	
}
