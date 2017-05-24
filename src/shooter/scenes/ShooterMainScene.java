package shooter.scenes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import framework.input.InputManager;
import framework.main.Scene;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.objects.Background;
import shooter.objects.GameManager;
import shooter.objects.HealthPowerUp;
import shooter.objects.Player;

public class ShooterMainScene extends Scene {
	private GameManager gameManager ; 
	@Override
	public void gameObjectsOnStart() {
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		new Background() ; 
		new Player("Assets/PlaneSprites/B-17.png","MainPlayer") ;
		gameManager = new GameManager("Manager") ;
		
	}
	public GameManager getManager(){
		return gameManager ; 
	}
	public void finishScene(){
		main_Camera = null ; 
		gameManager = null ; 
		
	}

}
