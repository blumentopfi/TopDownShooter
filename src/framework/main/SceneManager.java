package framework.main; 
import framework.input.InputManager;
import framework.rendering.Camera;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Manager that holds all the Information about the Scenes and the Objects in the scene. 
 * @author Fin
 *
 */
public class SceneManager {
	private static Camera main_Camera ;
	private static Scene m_Scene ;
	private static JFrame game_Window ;
	private static InputManager input_manager ;
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
	public GameObject getGameObjectbyId(long id){
		for (GameObject aM_GameObjectsInScene : m_GameObjectsInScene) {
			if (id == aM_GameObjectsInScene.getId()) {
				return aM_GameObjectsInScene;
			}
		}
		return null ; 
	}

	/**
	 * Switch to another Scene
	 * @param toSet The Scene to set.
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
	 * Return the gameObject when you know the name of it
	 * @param Name Name of gameOBject to find
	 * @return gameObject
	 */
	public GameObject getGameObjectByName(String Name){
		for (GameObject aM_GameObjectsInScene : m_GameObjectsInScene) {
			if (Objects.equals(aM_GameObjectsInScene.getName(), Name)) {
				return aM_GameObjectsInScene;
			}
		}
		return null ; 
	}
	
	private static SceneManager instance ; 
	private SceneManager() {
		m_GameObjectsInScene = new ArrayList<>() ;
		m_GameObjectsToDelete = new ArrayList<>() ;
	}

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
	void AddGameObjectToScene(GameObject toAdd){
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
