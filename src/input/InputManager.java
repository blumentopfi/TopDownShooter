package input;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import main.SceneManager;
import main.GameObject;
import objects.Enemy;
import rendering.Camera;

public class InputManager implements ActionListener {
	private static final int DELAY = 10;
	Camera camera;
	public InputManager(){
		JFrame window = SceneManager.getInstance().getMainCamera().getGameWindow() ;
		camera = SceneManager.getInstance().getMainCamera();
		window.addKeyListener(new KeyManager());
		window.setFocusable(true);
		window.setFocusTraversalKeysEnabled(true);
		Timer timer = new Timer(DELAY,this) ; 
		timer.start();
		
	}
	public class KeyManager implements KeyListener{
		public void keyPressed(KeyEvent e) {
			List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ; 
			for (GameObject Object: gameObjectsinScene){
				Object.keyPressed(e);
			}
			
		}

		
		public void keyReleased(KeyEvent e) {
			List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ; 
			for (GameObject Object: gameObjectsinScene){
				Object.keyReleased(e);
			}
		
			
		}


		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ;
		//setupEnemies(gameObjectsinScene);
		for (int i = 0 ; i < gameObjectsinScene.size() ; i++){
			GameObject Object = gameObjectsinScene.get(i) ; 
			System.out.println(Object.getName());
			Object.Update();
			System.out.println("Updated");
			if (Object.getPosition().y < (SceneManager.getInstance().getMainCamera().getViewRect().getMinY()) && (Object.getName().equals("Missle"))){
				SceneManager.getInstance().getGameObjectToDelete().add(Object) ;
			}
			if (Object.getPosition().y > (SceneManager.getInstance().getMainCamera().getViewRect().getMaxY()) && (Object.getName().equals("Enemy"))){
				SceneManager.getInstance().getGameObjectToDelete().add(Object) ;
			}
		}


		//TODO Position ist not in pixels, so getX and getY do not work without further calculation. Need to reverse WorldCoordToScreenCoord.
		for (GameObject objectA : gameObjectsinScene) {
			if((objectA.getName().equals("Enemy")) || (objectA.getName().equals("Missle")) || (objectA.getName().equals("MainPlayer"))) {
				for (GameObject objectB : gameObjectsinScene) {
					if((objectB.getName().equals("Enemy")) || (objectB.getName().equals("Missle")) || (objectB.getName().equals("MainPlayer"))) {
						if(!objectA.equals(objectB)) {
							Rectangle recA = gameObjectToRectangle(objectA);
							Rectangle recB = gameObjectToRectangle(objectB);
							System.out.println("Collision: " + recA.intersects(recB));
							if((objectA.getName().equals("Missle")) && (objectB.getName().equals("Enemy"))) {
								//(Enemy)objectB.setHealth(objectB.getHealth() - 50);
							}
						}
					}
				}
			}
		}
		SceneManager.getInstance().PrintAllGameObjectsByName();
		SceneManager.getInstance().GetAllGameObjectsInScene().removeAll(SceneManager.getInstance().getGameObjectToDelete()) ;
		SceneManager.getInstance().getGameObjectToDelete().clear();
		
	}

	
	public Rectangle gameObjectToRectangle(GameObject object) {
		Point2D.Float posA = camera.ScreenCoordToWorldCoord(object.getPosition());
		Dimension dim = object.getTransform().getSize();
		double height = dim.getHeight();
		double width = dim.getWidth();
		Rectangle rec = new Rectangle((int)(posA.getX() - (width/2)), (int)(posA.getY() - (height/2)), (int) width, (int) height);
		return rec;
	}
	
	
}
