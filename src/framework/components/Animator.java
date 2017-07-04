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
/**
 * 
 * @author 7toeter
 * 
 * 
 */
public class Animator extends Component {
	GameObject m_myObject; // Object the Animator is assgined to
	List<Sprite> m_SpriteSheet = new ArrayList<Sprite>();
	int FrameCount = 0;
	long NextFrame = 0;
	int SwitchRate = 10;
	boolean looping = true;
	boolean hasFinished = false;

	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject,int rate) {
		this.m_myObject = initObject;
		for (int i = 0 ; i < SpriteSheet.size() ; i++){
			this.m_SpriteSheet.add(new Sprite(SpriteSheet.get(i),initObject)) ; 
		}
		this.SwitchRate = rate ; 
	}
	public List<Sprite> getSpriteSheet(){
		return m_SpriteSheet ; 
	}
	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject, boolean looping,int rate) {
		this.m_myObject = initObject;
		for (int i = 0 ; i < SpriteSheet.size() ; i++){
			this.m_SpriteSheet.add(new Sprite(SpriteSheet.get(i),initObject)) ; 
		}
		this.looping = looping;
		this.SwitchRate = rate ; 
	}
	public void Reset(){
		FrameCount = 0 ; 
		hasFinished = false ; 
	}
	@Override
	public void ComponentUpdate() {
		if (!hasFinished) {
			if (NextFrame < System.currentTimeMillis()) {
				if (FrameCount >= m_SpriteSheet.size() && looping == true) {
					FrameCount = 0;
				}
				if (FrameCount >= m_SpriteSheet.size() && looping == false) {
					hasFinished = true;
					return ; 
				}			
				this.switchImage(m_SpriteSheet.get(FrameCount));
				NextFrame = System.currentTimeMillis() + SwitchRate;
				FrameCount++;
			}
		}
	}

	public void switchImage(Sprite image) {
		m_myObject.removeComponent(m_myObject.getSprite());
		m_myObject.addComponent(image);
	}

	public boolean hasFinished() {
		return hasFinished;
	}

}
