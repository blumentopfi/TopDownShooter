package shooter.scenes;

import framework.main.Scene;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.objects.GameManager;

import java.awt.geom.Rectangle2D;

public class TestScene extends Scene {
	private GameManager gameManager ;

	@Override
	public void init() {
		SceneManager.getInstance() ; 
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);

	}
	public GameManager getManager(){
		return gameManager ; 
	}
	public void finishScene() {
		main_Camera = null;
		gameManager = null;
	}

}
