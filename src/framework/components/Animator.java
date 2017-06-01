package framework.components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

public class Animator extends Component {
	GameObject myObject ; //Object the Animator is assgined to
	List<BufferedImage> SpriteSheet  = new ArrayList<BufferedImage>(); 
	int FrameCount = 0 ; 
	long NextFrame = 0 ; 
	int SwitchRate = 50 ; 
	public Animator(List<BufferedImage> SpriteSheet,GameObject initObject){
		this.myObject = initObject ; 
		this.SpriteSheet = SpriteSheet ; 
	}
	@Override
	public void ComponentUpdate() {
		if (NextFrame< System.currentTimeMillis() ){
			if(FrameCount >= SpriteSheet.size()){
				FrameCount = 0 ; 
			}
		this.switchImage(SpriteSheet.get(FrameCount));
		NextFrame = System.currentTimeMillis() + SwitchRate ;
		FrameCount++ ; 
		}
	}
	
	public void switchImage(BufferedImage image){
		myObject.removeComponent(myObject.getSprite());
		myObject.addComponent(new Sprite(image,myObject));
	}

}
