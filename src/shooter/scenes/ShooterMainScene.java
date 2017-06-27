package shooter.scenes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.input.InputManager;
import framework.main.Scene;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.objects.Background;
import shooter.objects.ExplosionPool;
import shooter.objects.GameManager;
import shooter.objects.HealthPowerUp;
import shooter.objects.Player;

public class ShooterMainScene extends Scene {
	private GameManager gameManager ; 
	@Override
	public void init() {
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		new Background() ; 
		new Background(true) ; 
		new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		long time = System.nanoTime() ; 
		ExplosionPool.init();  
		System.out.println(System.nanoTime() - time) ;
		gameManager = new GameManager("Manager") ;
		gameManager.StartWaves(); 
		
	}
	public GameManager getManager(){
		return gameManager ; 
	}
	public void finishScene(){
		main_Camera = null ; 
		gameManager = null ; 
		
	}

}
