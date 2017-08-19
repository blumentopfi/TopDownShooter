package shooter.objects;

import framework.components.Animator;
import framework.components.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Stylish Explosion for our shots, nice blue effect
 * @author Fin
 *
 */
class ExplosionSciFi extends Explosion {
	private final Animator myAnimator ;

	/**
	 * Explosion for the Sci-Fi-Bullets of the player.
	 */
	ExplosionSciFi(){
		super() ;
		this.setActive(false);
		this.addComponent(new Sprite("/Assets/ProjectileSprite/Explosion/1_0.png",this));
		List<BufferedImage>myAnimation = new ArrayList<>() ;
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


}
