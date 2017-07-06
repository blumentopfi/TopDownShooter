package framework.main; 
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import framework.input.InputManager;
import framework.main.GameObject;
import framework.rendering.Camera;
import shooter.objects.*;
import shooter.scenes.MenuScene;
import shooter.scenes.ShooterMainScene; 
/**
 * The Manager that holds all the Information about the Scenes and the Objects in the scene. 
 * @author Fin
 *
 */
public class SceneManager {
	static Camera main_Camera ; 
	static Scene m_Scene ; 
	static JFrame game_Window ; 
	static InputManager input_manager ; 
	static public GraphicsConfiguration config ; 
	private static List<GameObject> m_GameObjectsInScene ;
	private static List<GameObject> m_GameObjectsToDelete;
	public static void init(Scene StartScene) {
		System.setProperty("sun.java2d.opengl", "True") ; //Use OpenGL Rendering 
		game_Window = new JFrame("TopDownShooter") ; //create the window
		SceneManager.getInstance().SetScene(StartScene); //Set the first Scene
		input_manager = new InputManager()  ; //create an input manager 
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		config = device.getDefaultConfiguration();
	}
	
	public List<GameObject> getGameObjectToDelete(){
		return m_GameObjectsToDelete;
	}

	/**
	 * Switch to another Scene
	 * @param toSet
	 */
	public void SetScene(Scene toSet){
		if (m_Scene != null){ //If we already have a scene we have to destroy it first
		m_Scene.finishScene(); 
		m_Scene = null ; 
		main_Camera = null ;
		m_GameObjectsInScene.clear(); //Clear all the remaining GameoBjects
		m_GameObjectsToDelete.clear() ; 
		}
		m_Scene = toSet ;  //Set the new Scene
		m_Scene.init(); //start the new scene
		if (input_manager!= null)  input_manager.RefreshKeyManager(); //TODO
		
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
	public ActionMap getActionMap(){
		return main_Camera.getGameView().getActionMap() ; 
	}
	public InputMap getInputMap(){
		return main_Camera.getGameView().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW) ; 
	}
	
	/**
	 * Print the names of all GameOBjects to the console
	 */
	public void PrintAllGameObjectsByName(){
		for (GameObject g: m_GameObjectsInScene){
			System.out.println(g.getName());
		}
	}
	/**
	 * Return the gameObject when you know the name of it
	 * @param Name Name of gameOBject to find
	 * @return gameObject
	 */
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
	/**
	 * Add a GameObject to the scene
	 * @param toAdd Object we want to add
	 */
	public void AddGameObjectToScene(GameObject toAdd){
		m_GameObjectsInScene.add(toAdd) ; 
	}
	/**
	 * Return every gameObject that is in the scene
	 * @return every GameObject in Scene
	 */
	public List<GameObject> GetAllGameObjectsInScene (){
		return m_GameObjectsInScene ; 
	}

}
