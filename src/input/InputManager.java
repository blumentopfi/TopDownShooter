package input;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

import Components.Collider;
import main.SceneManager;
import main.GameObject;
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
			try{
			List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ; 
			for (GameObject Object: gameObjectsinScene){
				Object.keyPressed(e);
			}
			}catch(ConcurrentModificationException ex){
				keyPressed(e) ;
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
			Object.Update();	
		}

		
		for (int i = 0 ; i < gameObjectsinScene.size() ; i++){
			try{
				GameObject objectA = gameObjectsinScene.get(i);
				Collider colliderToCheckA ; 
				colliderToCheckA = objectA.getCollider() ; 
				if (colliderToCheckA != null){
					Rectangle2D.Float rectangleOfColliderToCheckA = colliderToCheckA.getCollidingRectangle() ; 
					for (int j = 0 ; j < gameObjectsinScene.size() ; j++){
						try{
							GameObject objectB = gameObjectsinScene.get(j);
							if (objectB != objectA){			
								Collider colliderToCheckB ; 
								colliderToCheckB = objectB.getCollider() ; 
								if (colliderToCheckB != null){
									Rectangle2D.Float rectangleOfColliderToCheckB = colliderToCheckB.getCollidingRectangle() ; 
									if (rectangleOfColliderToCheckA.intersects(rectangleOfColliderToCheckB)){
										objectA.OnCollision(objectB);
										objectB.OnCollision(objectA);
									}
								}
								
							}
						}catch(IndexOutOfBoundsException e){
							//ignore
						}
					}
				}
			}
			catch(IndexOutOfBoundsException e){
				//ignore
			}
		}
		
		SceneManager.getInstance().PrintAllGameObjectsByName();
		SceneManager.getInstance().GetAllGameObjectsInScene().removeAll(SceneManager.getInstance().getGameObjectToDelete()) ;
		SceneManager.getInstance().getGameObjectToDelete().clear();
		
	}

	
	
	
	
}
