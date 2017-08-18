package shooter.objects;

import framework.components.Animator;
import framework.main.GameObject;
import framework.main.SceneManager;
/**
 * Base-Class for our Explosion Types
 * @author Fin
 *
 */
public  class
Explosion extends GameObject {
	Animator myAnimator ; 
	GameManager manager ;

	/**
	 * Constructor for explosion.
	 * @param Name Name of the game-object.
	 */
	Explosion(String Name){
		super(Name) ; 
	    //get the manager
		this.manager = (GameManager)SceneManager.getInstance().getGameObjectByName("Manager") ;
		
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		super.Update();
	}
}
