package shooter.objects;

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
		this.addComponent(new Sprite("Assets/ProjectileSprite/Explosion/0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<BufferedImage>() ; 
		try {
			for (int i = 0 ; i < 7 ; i++) 
				myAnimation.add(ImageIO.read(new File("Assets/ProjectileSprite/Explosion/" + i + ".png")));

		} catch (IOException e) {
			e.printStackTrace();
		}    
		myAnimator = new Animator(myAnimation,this,false,30) ; 
		this.addComponent(myAnimator);
	}
	public void Update(){
		super.Update() ; 
		if (myAnimator.hasFinished()){
			this.Destroy();
		}
		
	}

}
