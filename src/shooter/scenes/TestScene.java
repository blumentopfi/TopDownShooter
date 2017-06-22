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
import shooter.objects.Enemy;
import shooter.objects.Explosion;
import shooter.objects.GameManager;
import shooter.objects.HealthPowerUp;
import shooter.objects.Player;
import shooter.objects.TestObject;

public class TestScene extends Scene {
	private GameManager gameManager ; 
	TestObject test ; 
	public static boolean valid = false ; 
	@Override
	public void init() {
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		
		test = new TestObject("test",this) ; 
	}
	public GameManager getManager(){
		return gameManager ; 
	}
	public void finishScene(){
		main_Camera = null ; 
		gameManager = null ; 
	}
	
	public static void EnemyDead(){
		valid = true ; 
	}

}
