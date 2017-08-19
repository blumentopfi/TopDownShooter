package framework.components;

import framework.main.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
/**
 * Die Animations Komponente unseres Frameworks
 * 
 * @author 7toeter
 * @version 1.0
 * 
 */
public class Animator extends Component {
	private final GameObject m_myObject; // Object the Animator is assigned to
	private final List<Sprite> m_SpriteSheet = new ArrayList<>(); //Sprite Sheet des Animators
	private int FrameCount = 0; // Variable to hold where we are in the SpriteSheet ;
	private long NextFrame = 0; // Variable to hold FrameLoopSpeed
	private int SwitchRate = 10;
	private boolean looping = true; //is Animation looping
	private boolean hasFinished = false; //is Animation finished
	/**
	 * Konstruktur des Animators, wir ben�tigen ein SpriteSheet das Object was uns als Komponent 
	 * hinzugef�gt hat, und die rate in der wir den SpriteSheet durchgehen sollen, das hier loopt per default
	 * @param SpriteSheet Sprite Sheet of the Animator
	 * @param initObject Mother object
	 * @param rate  rate in which we change the Sprites
	 */
	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject,int rate) {
		this.m_myObject = initObject;
		for (BufferedImage aSpriteSheet : SpriteSheet) {
			this.m_SpriteSheet.add(new Sprite(aSpriteSheet, initObject));
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
	 * Konstruktur des Animators, wir ben�tigen ein SpriteSheet das Object was uns als Komponent 
	 * hinzugef�gt hat, und die rate in der wir den SpriteSheet durchgehen sollen.
	 * @param SpriteSheet Sprite Sheet of the Animator
	 * @param initObject Mother object
	 * @param rate  rate in which we change the Sprites
	 * @param looping should the animator loop
	 */
	public Animator(List<BufferedImage> SpriteSheet, GameObject initObject, boolean looping,int rate) {
		this.m_myObject = initObject;
		for (BufferedImage aSpriteSheet : SpriteSheet) {
			this.m_SpriteSheet.add(new Sprite(aSpriteSheet, initObject));
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
				if (FrameCount >= m_SpriteSheet.size() && looping) {
					FrameCount = 0; //if we are through our Sheet and we are looping reset.
				}
				if (FrameCount >= m_SpriteSheet.size() && !looping) {
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
	 * @param image The Image to switch.
	 */
	private void switchImage(Sprite image) {
		m_myObject.removeComponent(m_myObject.getSprite());
		m_myObject.addComponent(image);
	}
	/**
	 * Lets see if this is finished
	 * @return True if animator has finished.
	 */
	public boolean hasFinished() {
		return hasFinished;
	}

}
