package shooter.testing;

import org.junit.Test;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.objects.Enemy;
import shooter.objects.GameManager;
import shooter.objects.Player;
import shooter.scenes.MenuScene;
import shooter.scenes.TestScene;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.junit.Assert;

public class ShooterTest {
	TestScene test ; 
	public ShooterTest() {
		SceneManager.getInstance();
		SceneManager.init(test = new TestScene()); 
	}
	@Test
	public void testShotAndKill() throws InterruptedException{
		Thread.sleep(100);
		GameManager m = new GameManager("Manager") ; 
		Player p = new Player("Assets/PlaneSprites/1.png","MainPlayer") ; 
		new Enemy("Assets/PlaneSprites/Enemy Bipolar.png", "Enemy", 5, 0);
		p.setPosition(new Point2D.Float(5, 2));
		p.setDamage(50000);
		p.shoot(); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null,SceneManager.getInstance().getGameObjectByName("Enemy")) ; 
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(20,m.getScore());
		p.Destroy();
		m.Destroy(); 
	}
	@Test
	public void testSetter(){
		try{
		Thread.sleep(100);
		Player p = new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		Point2D.Float point = new Point2D.Float(8, 8) ;
		Thread.sleep(100) ;
		p.setPosition(point);
		
		assertEquals(p.getPosition(),point) ;
		Thread.sleep(100) ;
		p.Rotate(20);
		Thread.sleep(100) ;
		assertEquals(p.getTransform().getRotation(),20);
		p.setDamage(50);
		assertEquals(50, p.getDamage());
		p.setHealth(2000);
		assertEquals(2000,p.getHealth()) ;
		p.setDimension(new Dimension(500,500));
		assertEquals(p.getTransform().getSize().getHeight(), 500,2);
		assertEquals(p.getTransform().getSize().getWidth(), 500,2); //We will not test more setter and getter as Logic is more important to test.
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	@Test
	public void testSceneWorldFunctions() throws InterruptedException{
		Camera main_camera = SceneManager.getInstance().getMainCamera() ; 
		Thread.sleep(100);
		Point2D.Float a = new Point2D.Float(5, 5) ; 
		Point2D.Float b = main_camera.WorldCoordToScreenCoord(main_camera.ScreenCoordToWorldCoord(a)) ; 
		assertEquals(a.x,b.x,0) ;
		assertEquals(a.y,b.y,0) ;
	}
	@Test
	public void testWaveSpawning(){
		GameManager m = new GameManager("Manager") ;
		Player p = new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		m.StartWaves();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int Counter = 0 ; 
		for (GameObject g : SceneManager.getInstance().GetAllGameObjectsInScene()){
			if (g.getName() == "Enemy") Counter++ ; 
		}
		assertEquals(5,Counter) ;	
	}
	@Test
	public void testKeyInput() throws InterruptedException{
		Player p = new Player("Assets/PlaneSprites/1.png","MainPlayer") ;
		try {
			Robot robot = new Robot() ;
			robot.keyPress(KeyEvent.VK_SPACE); //Shooting
			Thread.sleep(100);
			assertEquals(false,null == SceneManager.getInstance().getGameObjectByName("Missle")) ;
			float x = p.getPosition().x ; 
			robot.keyPress(KeyEvent.VK_LEFT); //Move Left
			Thread.sleep(100);
			assertTrue(x > p.getPosition().x) ;
			x = p.getPosition().x ; 
			robot.keyPress(KeyEvent.VK_RIGHT); //MOve Right
			Thread.sleep(100);
			assertTrue(x < p.getPosition().x) ;
			x = p.getPosition().y ; 
			robot.keyPress(KeyEvent.VK_UP);//Move up
			Thread.sleep(100);
			assertTrue(x > p.getPosition().y) ;
			x = p.getPosition().y ; 
			robot.keyPress(KeyEvent.VK_DOWN);//Move down
			Thread.sleep(100);
			assertTrue(x < p.getPosition().y) ;
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
	
}
