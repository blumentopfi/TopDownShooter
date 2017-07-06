package framework.input;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.Timer;

import framework.components.*;
import framework.geometry.QuadTree;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

/**
 * Manager for our game Ticks and our Input
 * 
 * @author Fin
 *
 */
public class InputManager implements ActionListener {
	private static final int DELAY = 10; // Tick every 10 milliseconds
	Camera camera; // our Camera
	JFrame window; // our gameWindow
	KeyListener keylistener; // our Listener for the input
	Timer timer; // Tick timer
	static boolean paused = false ; 
	public static void Pause(){
		paused = true ; 
	}
	public static void UnPause(){
		paused = false ; 
	}
	
	public static boolean isPause(){
		return paused ; 
	}

	public InputManager() {
		window = SceneManager.getInstance().getMainCamera().getGameWindow();
		camera = SceneManager.getInstance().getMainCamera();
		MouseInput a = new MouseInput();
		window.addMouseListener(a);
		timer = new Timer(DELAY, this);
		timer.start();

	}

	public void RefreshKeyManager() {
		window.removeKeyListener(keylistener);
		window.addKeyListener(keylistener);
		window.setFocusable(true);
		window.setFocusTraversalKeysEnabled(true);
	}
	/**
	 * Class to that broadcasts MouseInput to all GameObjects
	 * @author Fin
	 *
	 */
	class MouseInput implements MouseListener {
		List<GameObject> gameObjectsinScene;

		public MouseInput() {
			super();
			gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		/**
		 * Go through all GameObjects and give them the MouseEvent
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
			for (int i = 0; i < gameObjectsinScene.size(); i++) {
				GameObject Object = gameObjectsinScene.get(i);
				Object.MousePressed(arg0);
			}

		}
		/**
		 * Go through all GameObjects and give them the MouseEvent
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			for (int i = 0; i < gameObjectsinScene.size(); i++) {
				GameObject Object = gameObjectsinScene.get(i);
				Object.MouseReleased(arg0);
			}

		}

	}
/**
 * Not using that yet
 * @param shapeA
 * @param shapeB
 * @return
 */
private static boolean testIntersection(Shape shapeA, Shape shapeB) {
   if (shapeA instanceof Rectangle2D.Float){
	   if (shapeB instanceof Ellipse2D.Float){
		   
	   }
	   if (shapeB instanceof Rectangle2D.Float){
		   Rectangle2D.Float a = (Rectangle2D.Float) shapeA; 
		   Rectangle2D.Float b = (Rectangle2D.Float) shapeB; 
		   return a.intersects(b) ;
		   
	   }
   }
   if (shapeA instanceof Ellipse2D.Float){
	   if (shapeB instanceof Rectangle2D.Float){
		   
	   }
	   if (shapeB instanceof Ellipse2D.Float){
		   Ellipse2D.Float a = (Ellipse2D.Float) shapeA; 
		   Ellipse2D.Float b = (Ellipse2D.Float) shapeB;
		   return true ;
	   }
   }
   
   
   return false ;
}
	/**
	 * Function to detect Collisions via a Quad Tree
	 *
 	 * @see <a href="Wikipedia Quad Tree">https://de.wikipedia.org/wiki/Quadtree</a>
	 * @param gameObjectsinScene
	 */
	private void CheckCollisions(List<GameObject> gameObjectsinScene){
		//New Collision Detection using a Quad Tree
		QuadTree quad = new QuadTree(0, camera.getViewRect());
		quad.clear();
		for (int i = 0; i < gameObjectsinScene.size(); i++) {
			if (gameObjectsinScene.get(i).getCollider() != null) {
				quad.insert(gameObjectsinScene.get(i));
			}
		}
		List<GameObject> returnedObjects = new ArrayList<GameObject>();
		for (int i = 0; i < gameObjectsinScene.size(); i++) { //iterate through objects
			GameObject objectA = gameObjectsinScene.get(i);
			Collider colliderToCheckA = objectA.getComponent(Collider.class);
			if (colliderToCheckA != null) {
				returnedObjects.clear();
				Shape ShapeA = colliderToCheckA.getCollidingShape();
				quad.retrieve(returnedObjects, gameObjectsinScene.get(i)); //get Objects in the same node in tree
				for (int j = 0; j < returnedObjects.size(); j++) {
					GameObject objectB = returnedObjects.get(j);			
					if (objectB != objectA) {
						Collider colliderToCheckB;
						colliderToCheckB = objectA.getComponent(Collider.class);
						if (colliderToCheckB != null) {
							Shape ShapeB = colliderToCheckB.getCollidingShape();
							if (ShapeA.intersects(ShapeB.getBounds2D())) { //check Collisions between
								objectA.OnCollision(objectB);
								objectB.OnCollision(objectA);
							}
						}

					}
				}
			}
		}
	}

	/**
	 * This gets called by the timer.
	 */
	public void actionPerformed(ActionEvent arg0) {
		// Get all the Objects in our Scene and Update them
		List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene();
		if (!paused){
		for (int i = 0; i < gameObjectsinScene.size(); i++) {
			GameObject Object = gameObjectsinScene.get(i);
			if (Object.isActive()) Object.Update();
		}
		}
		
		CheckCollisions(gameObjectsinScene) ; 
		// If there are destroyed gameObject remove them
		SceneManager.getInstance().GetAllGameObjectsInScene()
				.removeAll(SceneManager.getInstance().getGameObjectToDelete());
		SceneManager.getInstance().getGameObjectToDelete().clear();

	}

}
