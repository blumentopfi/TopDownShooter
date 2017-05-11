package Scenes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import input.InputManager;
import main.Scene;
import main.SceneManager;
import objects.Background;
import objects.GameManager;
import objects.HealthPowerUp;
import objects.Player;
import rendering.Camera;

public class ShooterMainScene extends Scene {
	private GameManager gameManager ; 
	@Override
	public void gameObjectsOnStart() {
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		new Background() ; 
		new Player("Assets/PlaneSprites/B-17.png","MainPlayer") ;
		gameManager = new GameManager("Manager") ;
		new HealthPowerUp(5,0,20) ; 
		
	}
	public GameManager getManager(){
		return gameManager ; 
	}

}
