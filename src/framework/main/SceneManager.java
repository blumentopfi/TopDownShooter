package framework.main; 
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import framework.input.InputManager;
import framework.main.GameObject;
import framework.rendering.Camera;
import shooter.objects.*;
import shooter.scenes.MenuScene;
import shooter.scenes.ShooterMainScene; 
public class SceneManager {
	static Camera main_Camera ;
	static Scene m_Scene ; 
	static HealthPowerUp test; 
	static GameManager gameManager ; 
	static JFrame game_Window ; 
	static InputManager input_manager ; 
	private static List<GameObject> m_GameObjectsInScene ;
	private static List<GameObject> m_GameObjectsToDelete;
	public static void init(Scene StartScene) {
		System.setProperty("sun.java2d.opengl", "True") ; 
		game_Window = new JFrame("TopDownShooter") ;
		SceneManager.getInstance().SetScene(StartScene);
		input_manager = new InputManager()  ; 
	}
	
	public List<GameObject> getGameObjectToDelete(){
		return m_GameObjectsToDelete;
	}


	public void SetScene(Scene toSet){
		if (m_Scene != null){
		input_manager = null ; 
		m_Scene.finishScene(); 
		main_Camera = null ;
		m_GameObjectsInScene.clear();
		m_GameObjectsToDelete.clear() ; 
		}
		m_Scene = toSet ; 
		m_Scene.gameObjectsOnStart();
		
		
	}
	
	public void setMainCamera(Camera a){
		main_Camera = a ; 
	}
	
	public Camera getMainCamera(){
		return main_Camera ; 
	}
	
	public JFrame getGameWindow (){
		return game_Window ; 
	}
	
	public void PrintAllGameObjectsByName(){
		for (GameObject g: m_GameObjectsInScene){
			//System.out.println(g.getName());
		}
	}
	
	public GameObject getGameObjectByName(String Name){
		for (int i = 0 ; i < m_GameObjectsInScene.size() ; i++){
			if (m_GameObjectsInScene.get(i).getName() == Name){
				return m_GameObjectsInScene.get(i) ; 
			}
		}
		return null ; 
	}
	
	private static SceneManager instance ; 
	private SceneManager() {
		m_GameObjectsInScene = new ArrayList<GameObject>() ;
		m_GameObjectsToDelete = new ArrayList<GameObject>() ; 
	} ; 
	public static SceneManager getInstance() {
		if (SceneManager.instance == null){
			SceneManager.instance = new SceneManager () ;
		}
		return SceneManager.instance ; 
	}
	
	public void AddGameObjectToScene(GameObject toAdd){
		m_GameObjectsInScene.add(toAdd) ; 
	}
	public List<GameObject> GetAllGameObjectsInScene (){
		return m_GameObjectsInScene ; 
	}

}
