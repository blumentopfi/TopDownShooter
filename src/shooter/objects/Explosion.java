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
import framework.main.SceneManager;
/**
 * Mother Class for our Explosion Types
 * @author Fin
 *
 */
public  class
Explosion extends GameObject {
	Animator myAnimator ; 
	GameManager manager ; 
	public Explosion(String Name){
		super(Name) ; 

		this.manager = (GameManager)SceneManager.getInstance().getGameObjectByName("Manager") ;
		
	}
	public void Update(){
		super.Update();
	}
}
