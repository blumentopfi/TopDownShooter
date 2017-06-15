package framework.input;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
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

		@Override
		public void mousePressed(MouseEvent arg0) {
			for (int i = 0; i < gameObjectsinScene.size(); i++) {
				GameObject Object = gameObjectsinScene.get(i);
				Object.MousePressed(arg0);
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			for (int i = 0; i < gameObjectsinScene.size(); i++) {
				GameObject Object = gameObjectsinScene.get(i);
				Object.MouseReleased(arg0);
			}

		}

	}

private static boolean testIntersection(Shape shapeA, Shape shapeB) {
   Area areaA = new Area(shapeA);
   areaA.intersect(new Area(shapeB));
   return !areaA.isEmpty();
}
	
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
		for (int i = 0; i < gameObjectsinScene.size(); i++) {
			GameObject objectA = gameObjectsinScene.get(i);
			Collider colliderToCheckA = objectA.getCollider();
			if (colliderToCheckA != null) {
				returnedObjects.clear();
				Shape ShapeA = colliderToCheckA.getCollidingShape();
				quad.retrieve(returnedObjects, gameObjectsinScene.get(i));
				for (int j = 0; j < returnedObjects.size(); j++) {
					GameObject objectB = returnedObjects.get(j);			
					if (objectB != objectA) {
						Collider colliderToCheckB;
						colliderToCheckB = objectB.getCollider();
						if (colliderToCheckB != null) {
							Shape ShapeB = colliderToCheckB.getCollidingShape();
							if (ShapeA.intersects(ShapeB.getBounds2D())) {
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
		// setupEnemies(gameObjectsinScene);
		for (int i = 0; i < gameObjectsinScene.size(); i++) {
			GameObject Object = gameObjectsinScene.get(i);
			Object.Update();
		}
		
		CheckCollisions(gameObjectsinScene) ; 

		/*
		// Old Collision Checking
		for (int i = 0; i < gameObjectsinScene.size(); i++) {
			try {
				GameObject objectA = gameObjectsinScene.get(i);
				Collider colliderToCheckA;
				colliderToCheckA = objectA.getCollider();
				if (colliderToCheckA != null) {
					Shape ShapeA = colliderToCheckA.getCollidingShape();
					for (int j = 0; j < gameObjectsinScene.size(); j++) {
						try {
							GameObject objectB = gameObjectsinScene.get(j);
							if (objectB != objectA) {
								Collider colliderToCheckB;
								colliderToCheckB = objectB.getCollider();
								if (colliderToCheckB != null) {
									Shape ShapeB = colliderToCheckB.getCollidingShape();
									if (ShapeA.intersects(ShapeB.getBounds2D())) {
										objectA.OnCollision(objectB);
										objectB.OnCollision(objectA);
									}
								}

							}
						} catch (IndexOutOfBoundsException e) {
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}*/
		// If there are destroyed gameObject remove them
		SceneManager.getInstance().GetAllGameObjectsInScene()
				.removeAll(SceneManager.getInstance().getGameObjectToDelete());
		SceneManager.getInstance().getGameObjectToDelete().clear();

	}

}
