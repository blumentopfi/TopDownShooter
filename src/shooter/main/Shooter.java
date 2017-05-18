package shooter.main;
import framework.main.*;
import shooter.scenes.MenuScene; 
public class Shooter {

	public static void main(String[] args) {
		SceneManager.getInstance();
		SceneManager.init(new MenuScene()); 
	}
}
