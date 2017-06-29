package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;

public class MisslePlayer extends Missle {
		
	public MisslePlayer(int damage,Point2D.Float movement ){
		super(damage,movement);
		this.movement = movement ; 
		this.addComponent(new Sprite("Assets/ProjectileSprite/Bullet.png",this));
	}
	
	public void OnCollision(GameObject collidingObject) {
		if (collidingObject.getName() == "Enemy"){
		Enemy collidingEnemy = (Enemy)collidingObject ; 
		collidingEnemy.addDamage(damage);
		Explosion e = ExplosionPool.getExplosion() ;
		if (e != null) e.setPosition(this.getPosition());
		
		this.Destroy();
		}
		if (collidingObject.getName() == "Boss"){
			Boss collidingEnemy = (Boss)collidingObject ;
			collidingEnemy.addDamage(damage);
			Explosion e = ExplosionPool.getExplosion() ;
			e.setPosition(this.getPosition());
			this.Destroy();
		}
	}
	public void Update(){
		super.Update();
	}
	
}
