package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

import main.GameManager;
import main.GameObject;

public class InputManager implements ActionListener {
	private static final int DELAY = 10;
	public InputManager(){
		JFrame window = GameManager.getInstance().getMainCamera().getGameWindow() ;
		window.addKeyListener(new KeyManager());
		window.setFocusable(true);
		window.setFocusTraversalKeysEnabled(true);
		Timer timer = new Timer(DELAY,this) ; 
		timer.start();
		
	}
	public class KeyManager implements KeyListener{
		public void keyPressed(KeyEvent e) {
			List<GameObject> gameObjectsinScene = GameManager.getInstance().GetAllGameObjectsInScene() ; 
			for (GameObject Object: gameObjectsinScene){
				Object.keyPressed(e);
			}
			
		}

		
		public void keyReleased(KeyEvent e) {
			List<GameObject> gameObjectsinScene = GameManager.getInstance().GetAllGameObjectsInScene() ; 
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
		List<GameObject> gameObjectsinScene = GameManager.getInstance().GetAllGameObjectsInScene() ; 
		for (GameObject Object: gameObjectsinScene){	
			Object.Update();
			if (Object.getPosition().y < (GameManager.getInstance().getMainCamera().getViewRect().getMinY()) && Object.getName() == "Missle"){
				GameManager.getInstance().getGameObjectToDelete().add(Object) ;
			}
		}
		GameManager.getInstance().PrintAllGameObjectsByName();
		
		GameManager.getInstance().GetAllGameObjectsInScene().removeAll(GameManager.getInstance().getGameObjectToDelete()) ;
		GameManager.getInstance().getGameObjectToDelete().clear();
		
	}
	
	
}
