package main;

import rendering.Camera;

abstract public class Scene {
	protected Camera main_Camera ; 
	public Scene(){
	}
	public abstract void gameObjectsOnStart () ; 
	public Camera getMainCamera(){
		return main_Camera ; 
	}
	public void finishScene(){
		main_Camera.getRenderer().interrupt();
		main_Camera.interrupt(); 
		main_Camera = null ; 
	}
}
