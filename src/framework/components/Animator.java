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
	GameObject myObject; // Object the Animator is assgined to
	List<BufferedImage> SpriteSheet = new ArrayList<BufferedImage>();
	int FrameCount = 0;
	long NextFrame = 0;
	int SwitchRate = 10;
	boolean looping = true;
	boolean hasFinished = false;

	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject,int rate) {
		this.myObject = initObject;
		this.SpriteSheet = SpriteSheet;
		this.SwitchRate = rate ; 
	}

	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject, boolean looping,int rate) {
		this.myObject = initObject;
		this.SpriteSheet = SpriteSheet;
		this.looping = looping;
		this.SwitchRate = rate ; 
	}

	@Override
	public void ComponentUpdate() {
		if (!hasFinished) {
			if (NextFrame < System.currentTimeMillis()) {
				if (FrameCount >= SpriteSheet.size() && looping == true) {
					FrameCount = 0;
				}
				if (FrameCount >= SpriteSheet.size() && looping == false) {
					hasFinished = true;
					return ; 
				}

				this.switchImage(SpriteSheet.get(FrameCount));
				NextFrame = System.currentTimeMillis() + SwitchRate;
				FrameCount++;

			}
		}
	}

	public void switchImage(BufferedImage image) {
		myObject.removeComponent(myObject.getSprite());
		myObject.addComponent(new Sprite(image, myObject));
	}

	public boolean hasFinished() {
		return hasFinished;
	}

}
