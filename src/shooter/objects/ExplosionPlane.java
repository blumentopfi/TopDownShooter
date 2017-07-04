package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import framework.components.Animator;
import framework.components.Audio;
import framework.components.Sprite;
import framework.main.GameObject;

public  class ExplosionPlane extends Explosion {
	Animator myAnimator ; 
	Audio a ; 
	public ExplosionPlane(String Name){
		super(Name) ; 
		this.setActive(false);
		this.addComponent(new Sprite("Assets/ProjectileSprite/Explosion/expl_06_0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<BufferedImage>() ; 
		try {
			for (int i = 0 ; i < 32 ; i++) 
				myAnimation.add(ImageIO.read(new File("Assets/ProjectileSprite/Explosion/expl_06_" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}    
		this.setDimension(new Dimension(this.getTransform().getSize().width/2,this.getTransform().getSize().height/2));
		myAnimator = new Animator(myAnimation,this,false,25) ; 
		this.addComponent(myAnimator);
		this.addComponent(a = new Audio());
		try {
			a.AddSound("BOOOOOM", "Assets/Sound/Explosion+1.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void Update(){
		super.Update() ; 
		if (myAnimator != null){
		if (myAnimator.hasFinished()){
			manager.ExplosionsPlane.returnExplosionToPool(this);
			
		}
		}	
	}
	public void Boom(){
		a.PlaySound("BOOOOOM");
	}
}
