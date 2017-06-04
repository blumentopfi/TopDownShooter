package framework.input;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.Timer;

import framework.components.*;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;
/**
 * Manager for our game Ticks and our Input
 * @author Fin
 *
 */
public class InputManager implements ActionListener {
	private static final int DELAY = 10  ; // Tick every 10 milliseconds
	Camera camera; //our Camera
	JFrame window ; //our gameWindow
	KeyListener keylistener ;  // our Listener for the input
	Timer timer ; // Tick timer
	public InputManager(){
		window = SceneManager.getInstance().getMainCamera().getGameWindow() ;
		camera = SceneManager.getInstance().getMainCamera();
		timer = new Timer(DELAY,this) ; 
		timer.start();
		
	}
	

	public void RefreshKeyManager(){
		window.removeKeyListener(keylistener);
		window.addKeyListener(keylistener) ; 
		window.setFocusable(true);
		window.setFocusTraversalKeysEnabled(true);
	}
	
	/**
	 * This gets called by the timer. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		//Get all the Objects in our Scene and Update them 
		List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ;
		//setupEnemies(gameObjectsinScene);
		for (int i = 0 ; i < gameObjectsinScene.size() ; i++){
			GameObject Object = gameObjectsinScene.get(i) ; 
			Object.Update();	
		}

		//Collision Checking
		for (int i = 0 ; i < gameObjectsinScene.size() ; i++){
			try{
				GameObject objectA = gameObjectsinScene.get(i);
				Collider colliderToCheckA ; 
				colliderToCheckA = objectA.getCollider() ; 
				if (colliderToCheckA != null){
					Shape ShapeA = colliderToCheckA.getCollidingShape() ; 
					for (int j = 0 ; j < gameObjectsinScene.size() ; j++){
						try{
							GameObject objectB = gameObjectsinScene.get(j);
							if (objectB != objectA){			
								Collider colliderToCheckB ; 
								colliderToCheckB = objectB.getCollider() ; 
								if (colliderToCheckB != null){
									Shape ShapeB = colliderToCheckB.getCollidingShape() ;  
									if (ShapeA.intersects(ShapeB.getBounds2D())){
										objectA.OnCollision(objectB);
										objectB.OnCollision(objectA);
									}
								}
								
							}
						}catch(IndexOutOfBoundsException e){
						}
					}
				}
			}
			catch(IndexOutOfBoundsException e){
			}
		}
		//If there are destroyed gameObject remove them 
		SceneManager.getInstance().GetAllGameObjectsInScene().removeAll(SceneManager.getInstance().getGameObjectToDelete()) ;
		SceneManager.getInstance().getGameObjectToDelete().clear();
		
	}


	
	
	
	
}
