package main; 
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import main.GameObject;
import objects.* ; 
import rendering.Camera;
import input.InputManager; 
public class SceneManager {
	static Camera main_Camera ;
	static Player MainPlayer ;
	static GameManager gameManager ; 
	private static List<GameObject> m_GameObjectsInScene ;
	private static List<GameObject> m_GameObjectsToDelete;
	public static void main(String[] args) {
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10)) ;
		MainPlayer = new Player("Assets/PlaneSprites/B-17.png","MainPlayer") ;
		MainPlayer.setPosition(new Point2D.Float(5,5));	
		System.out.println(MainPlayer.getCollider().getCollidingRectangle());
		System.out.println(MainPlayer.getTransform().getSize());
		
		InputManager input_manager = new InputManager()  ; 
		gameManager = new GameManager("Manager") ;
		//GameManager.getInstance().PrintAllGameObjectsByName();
	}
	
	public List<GameObject> getGameObjectToDelete(){
		return m_GameObjectsToDelete;
	}
	
	public Player getPlayer(){
		return MainPlayer ; 
	}
	
	public Camera getMainCamera(){
		return main_Camera ; 
	}
	public void PrintAllGameObjectsByName(){
		for (GameObject g: m_GameObjectsInScene){
			//System.out.println(g.getName());
		}
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
