package framework.main;

import framework.rendering.Camera;
/**
 * Scene Class for our Scene Managment
 * @author Fin
 *
 */
abstract public class Scene {
	protected Camera main_Camera ;
	protected Scene(){
	}
	/** 
	 * Initialization of the Szene
	 */
	public abstract void init () ;
	/**
	 * Kind of destructor for the scene
	 */
	public void finishScene(){
		main_Camera.getRenderer().interrupt();
		main_Camera = null ; 
	}
}
