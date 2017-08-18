package shooter.objects;

import framework.components.Animator;
import framework.components.Audio;
import framework.components.Sprite;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Stylish Explosion for enemy Planes
 * @author Fin
 *
 */
public  class ExplosionPlane extends Explosion {
	private Animator myAnimator ;
	Audio a ;

	/**
	 * Constructor for explosions for planes.
	 * @param Name The name of this game-object.
	 */
	ExplosionPlane(String Name){
		super(Name) ; 
		this.setActive(false);
		this.addComponent(new Sprite("/Assets/ProjectileSprite/Explosion/expl_06_0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<>() ;
		try {
			for (int i = 0 ; i < 32 ; i++) 
				myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/ProjectileSprite/Explosion/expl_06_" + i + ".png")));
			 try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}    
		this.setDimension(new Dimension(this.getTransform().getSize().width/2,this.getTransform().getSize().height/2));
		myAnimator = new Animator(myAnimation,this,false,25) ; 
		this.addComponent(myAnimator);
		this.addComponent(a = new Audio());
		try {
			a.AddSound("BOOOOOM", "/Assets/Sound/Explosion+1.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update() ; 
		if (myAnimator != null){
		if (myAnimator.hasFinished()){ //if animation is finished return this to pool
			manager.ExplosionsPlane.returnExplosionToPool(this);
			
		}
		}	
	}

	/**
	 * Play the sound of the explosion
	 */
	void Boom(){
		a.PlaySound("BOOOOOM"); //BOOOOOOOOM
	}
}
