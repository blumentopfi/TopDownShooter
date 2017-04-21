package main; 
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import main.GameObject;
import objects.Enemy;
import objects.Player;
import rendering.Camera;
import input.InputManager; 
public class GameManager {
	static Camera main_Camera ;
	static Player MainPlayer ;
	private static List<GameObject> m_GameObjectsInScene ;
	private static List<GameObject> m_GameObjectsToDelete;
	public static void main(String[] args) {
		MainPlayer = new Player("Assets/PlaneSprites/B-17.png","MainPlayer") ;
		MainPlayer.setPosition(new Point2D.Float(5,5));	
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10)) ;
		InputManager input_manager = new InputManager()  ; 
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
	private static GameManager instance ; 
	private GameManager() {
		m_GameObjectsInScene = new ArrayList<GameObject>() ;
		m_GameObjectsToDelete = new ArrayList<GameObject>() ; 
	} ; 
	public static GameManager getInstance() {
		if (GameManager.instance == null){
			GameManager.instance = new GameManager () ;
		}
		return GameManager.instance ; 
	}
	
	public void AddGameObjectToScene(GameObject toAdd){
		m_GameObjectsInScene.add(toAdd) ; 
	}
	public List<GameObject> GetAllGameObjectsInScene (){
		return m_GameObjectsInScene ; 
	}

}
