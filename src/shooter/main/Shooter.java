package shooter.main;
import framework.main.*;

import shooter.scenes.MenuScene;
import shooter.scenes.ShooterMainScene; 
/**
 * Main Class for the shooter
 * @author Fin
 */
public class Shooter {

	public static void main(String[] args) {
		SceneManager.getInstance();
		SceneManager.init(new MenuScene()); 
	}
}
