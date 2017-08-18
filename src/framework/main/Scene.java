package framework.main;

import framework.rendering.Camera;
/**
 * Scene Class for our Scene Managment
 * @author Fin
 *
 */
abstract public class Scene {
	protected Camera main_Camera ;

	public Scene(){
	}
	/** 
	 * Initialization of the Szene
	 */
	public abstract void init () ; 
	public Camera getMainCamera(){
		return main_Camera ; 
	}
	/**
	 * Kind of destructor for the scene
	 */
	public void finishScene(){
		main_Camera.getRenderer().interrupt();
		main_Camera = null ; 
	}
	public boolean isFinished(){
		boolean isFinished = false;
		return isFinished;
	}
}
