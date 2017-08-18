package shooter.main;
import framework.main.SceneManager;
import shooter.scenes.MenuScene;
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
