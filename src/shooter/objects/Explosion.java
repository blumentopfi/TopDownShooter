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

public  class Explosion extends GameObject {
	Animator myAnimator ; 
	public Explosion(String Name){
		super(Name) ; 
		this.setActive(false);
		this.addComponent(new Sprite("Assets/ProjectileSprite/Explosion/1_0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<BufferedImage>() ; 
		try {
			for (int i = 0 ; i < 17 ; i++) 
				myAnimation.add(ImageIO.read(new File("Assets/ProjectileSprite/Explosion/1_" + i + ".png")));

		} catch (IOException e) {
			e.printStackTrace();
		}    
		this.setDimension(new Dimension(this.getTransform().getSize().width/2,this.getTransform().getSize().height/2));
		myAnimator = new Animator(myAnimation,this,false,100) ; 
		this.addComponent(myAnimator);
		
		
	}
	public void Update(){
		super.Update() ; 
		if (myAnimator != null){
		if (myAnimator.hasFinished()){
			ExplosionPool.returnExplosionToPool(this);
		}
		}	
	}
}
