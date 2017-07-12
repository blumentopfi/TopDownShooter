package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import framework.components.Animator;
import framework.components.Sprite;
import framework.main.GameObject;
/**
 * Stylish Explosion for our shots, nice blue effect
 * @author Fin
 *
 */
public  class ExplosionSciFi extends Explosion {
	Animator myAnimator ;

	/**
	 * Explosion for the Sci-Fi-Bullets of the player.
	 * @param Name The name of this game-object.
	 */
	public ExplosionSciFi(String Name){
		super(Name) ; 
		this.setActive(false);
		this.addComponent(new Sprite("/Assets/ProjectileSprite/Explosion/1_0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<BufferedImage>() ; 
		try {
			for (int i = 0 ; i < 17 ; i++) 
				myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/ProjectileSprite/Explosion/1_" + i + ".png")));
			    try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}    
		//  this.setDimension(new Dimension(this.getTransform().getSize().width/2,this.getTransform().getSize().height/2));
		myAnimator = new Animator(myAnimation,this,false,50) ; 
		this.addComponent(myAnimator);
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update() ; 
		if (myAnimator != null){
		if (myAnimator.hasFinished()){ //if we have finished return ourselfs to the pool
			manager.ExplosionsSciFi.returnExplosionToPool(this);
		}
		}	
	}
}
