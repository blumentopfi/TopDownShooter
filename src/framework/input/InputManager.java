package framework.input;

import framework.components.Collider;
import framework.geometry.QuadTree;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager for our game Ticks and our Input
 * 
 * @author Fin
 *
 */
public class InputManager implements ActionListener {
	private static final int DELAY = 10; // Tick every 10 milliseconds
	private final Camera camera; // our Camera
	private final JFrame window; // our gameWindow
	private static boolean paused = false ;
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
		Timer timer = new Timer(DELAY, this);
		timer.start();

	}

	/**
	 * Class to that broadcasts MouseInput to all GameObjects
	 * @author Fin
	 *
	 */
	class MouseInput implements MouseListener {
		final List<GameObject> gameObjectsInScene;

		MouseInput() {
			super();
			gameObjectsInScene = SceneManager.getInstance().GetAllGameObjectsInScene();
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
			for (GameObject Object : gameObjectsInScene) {
				Object.MousePressed(arg0);
			}

		}
		/**
		 * Go through all GameObjects and give them the MouseEvent
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

/*
/**
 * Not using that yet
 * @param shapeA The original Shape.
 * @param shapeB The Shape to test for intersection.
 * @return True if intersected.
 */
/*
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
*/
	/**
	 * Function to detect Collisions via a Quad Tree
	 *
 	 * @see <a href="Wikipedia Quad Tree">https://de.wikipedia.org/wiki/Quadtree</a>
	 * @param gameObjectsInScene The list of elements to test collision.
	 */
	private void CheckCollisions(List<GameObject> gameObjectsInScene){
		//New Collision Detection using a Quad Tree
		QuadTree quad = new QuadTree(0, camera.getViewRect());
		quad.clear();
		for (GameObject aGameObjectsInScene : gameObjectsInScene) {
			if (aGameObjectsInScene.getCollider() != null) {
				quad.insert(aGameObjectsInScene);
			}
		}
		List<GameObject> returnedObjects = new ArrayList<>();
		for (int i = 0  ;  i < gameObjectsInScene.size() ; i++) { //iterate through objects
			GameObject objectA = gameObjectsInScene.get(i) ;
			Collider colliderToCheckA = objectA.getCollider();
			if (colliderToCheckA != null) {
				returnedObjects.clear();
				Shape ShapeA = colliderToCheckA.getCollidingShape();
				quad.retrieve(returnedObjects, objectA); //get Objects in the same node in tree
				for (int j = 0 ; j < returnedObjects.size() ; j++) {
					GameObject objectB = returnedObjects.get(j) ;
					if (objectB != objectA) {
						Collider colliderToCheckB;
						colliderToCheckB = objectB.getCollider();
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
		List<GameObject> gameObjectsInScene = SceneManager.getInstance().GetAllGameObjectsInScene();
		if (!paused){
			for (int i = 0 ; i < gameObjectsInScene.size() ; i++){
				GameObject Object = gameObjectsInScene.get((i));
				if (Object.isActive()) Object.Update();
			}
		}
		
		CheckCollisions(gameObjectsInScene) ;
		// If there are destroyed gameObject remove them
		SceneManager.getInstance().GetAllGameObjectsInScene()
				.removeAll(SceneManager.getInstance().getGameObjectToDelete());
		SceneManager.getInstance().getGameObjectToDelete().clear();

	}

}
