package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import main.GameManager;
import main.GameObject;
import objects.Enemy;

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
		setupEnemies(gameObjectsinScene);
		for (GameObject Object: gameObjectsinScene){
			Object.Update();
			if (Object.getPosition().y < (GameManager.getInstance().getMainCamera().getViewRect().getMinY()) && (Object.getName().equals("Missle"))){
				GameManager.getInstance().getGameObjectToDelete().add(Object) ;
			}
			if (Object.getPosition().y > (GameManager.getInstance().getMainCamera().getViewRect().getMaxY()) && (Object.getName().equals("Enemy"))){
				GameManager.getInstance().getGameObjectToDelete().add(Object) ;
			}
		}
		GameManager.getInstance().PrintAllGameObjectsByName();
		
		GameManager.getInstance().GetAllGameObjectsInScene().removeAll(GameManager.getInstance().getGameObjectToDelete()) ;
		GameManager.getInstance().getGameObjectToDelete().clear();
		
	}

	public void setupEnemies(List<GameObject> gos) {
		int enemyCounter = 0;
		for (GameObject enemys : gos) {
			if (enemys.getName().equals("Enemy")) {
				enemyCounter++;
			}
		}
		if (enemyCounter < 5) {
			Random random = new Random();
			float pos_X = random.nextInt(9 - 1 + 1) + 1;
			float pos_Y = random.nextInt(0 - (-5) + 1) + (-5);
			new Enemy("Assets/PlaneSprites/Enemy B-17.png", "Enemy", pos_X, pos_Y);
		}

	}
	
	
}
