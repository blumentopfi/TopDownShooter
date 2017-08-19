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
	private Animator myAnimator ;
	private final GameManager manager ;

	/**
	 * Constructor for explosion.
	 */
	Explosion(){
		super("Explo") ;
	    //get the manager
		this.manager = (GameManager)SceneManager.getInstance().getGameObjectByName("Manager") ;
		
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
}
