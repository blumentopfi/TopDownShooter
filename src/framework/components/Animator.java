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
 * Die Animations Komponente unseres Frameworks
 * 
 * @author 7toeter
 * @version 1.0
 * 
 */
public class Animator extends Component {
	GameObject m_myObject; // Object the Animator is assgined to
	List<Sprite> m_SpriteSheet = new ArrayList<Sprite>(); //Sprite Sheet des Animators
	int FrameCount = 0; // Variable to hold where we are in the SpriteSheet ; 
	long NextFrame = 0; // Variable to hold FrameLoopSpeed
	int SwitchRate = 10;
	boolean looping = true; //is Animatiorn looping
	boolean hasFinished = false; //is Animation finished
	/**
	 * Konstruktur des Animators, wir benötigen ein SpriteSheet das Object was uns als Komponent 
	 * hinzugefügt hat, und die rate in der wir den SpriteSheet durchgehen sollen, das hier loopt per default
	 * @param SpriteSheet
	 * @param initObject
	 * @param rate 
	 */
	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject,int rate) {
		this.m_myObject = initObject;
		for (int i = 0 ; i < SpriteSheet.size() ; i++){
			this.m_SpriteSheet.add(new Sprite(SpriteSheet.get(i),initObject)) ; 
		}
		this.SwitchRate = rate ; 
	}
	/**
	 * 
	 * @return SpriteSheet
	 */
	public List<Sprite> getSpriteSheet(){
		return m_SpriteSheet ; 
	}
	/**
	 * Konstruktur des Animators, wir benötigen ein SpriteSheet das Object was uns als Komponent 
	 * hinzugefügt hat, und die rate in der wir den SpriteSheet durchgehen sollen.
	 * @param SpriteSheet
	 * @param initObject
	 * @param looping
	 * @param rate
	 */
	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject, boolean looping,int rate) {
		this.m_myObject = initObject;
		for (int i = 0 ; i < SpriteSheet.size() ; i++){
			this.m_SpriteSheet.add(new Sprite(SpriteSheet.get(i),initObject)) ; 
		}
		this.looping = looping;
		this.SwitchRate = rate ; 
	}
	/**
	 * Reset the Animator if it has finished
	 */
	public void Reset(){
		FrameCount = 0 ; 
		hasFinished = false ; 
	}
	/**
	 * Update the Component
	 */
	@Override
	public void ComponentUpdate() {
		if (!hasFinished) {
			if (NextFrame < System.currentTimeMillis()) { //if we are smaller then NextFrame switch the Image
				if (FrameCount >= m_SpriteSheet.size() && looping == true) { 
					FrameCount = 0; //if we are through our Sheet and we are looping reset.
				}
				if (FrameCount >= m_SpriteSheet.size() && looping == false) {
					hasFinished = true; //if we are through and not looping tell we are finished and stop
					return ; 
				}			
				this.switchImage(m_SpriteSheet.get(FrameCount));
				NextFrame = System.currentTimeMillis() + SwitchRate;
				FrameCount++;
			}
		}
	}
	/**
	 * Switch the Sprite of the GameObject we are assigned to
	 * @param image
	 */
	public void switchImage(Sprite image) {
		m_myObject.removeComponent(m_myObject.getSprite());
		m_myObject.addComponent(image);
	}
	/**
	 * Lets see if this is finished
	 * @return
	 */
	public boolean hasFinished() {
		return hasFinished;
	}

}
