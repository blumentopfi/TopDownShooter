package framework.rendering;
/**
 * Class that handles the rendering
 * @author Fin
 * 
 */
import framework.main.GameObject;
import framework.main.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Camera  {

	private final Rectangle2D.Float ViewRect ; //World Space
	private final JFrame  m_GameWindow ;  //Game Window
	private final GameView m_GameView ;  //Panel where the game renders
	final FPSCounter m_fpscounter ; //Counter for the FPS
	/**
	 * Constructor for the camera class
	 * @param height height of the game window
	 * @param width widht of the game window
	 * @param ViewRect Rect for world space
	 * @param gameWindow reference to our game Window
	 */
	public Camera(int height, int width, Rectangle2D.Float ViewRect, JFrame gameWindow){
		m_GameView = new GameView() ; 
		this.ViewRect = ViewRect ;
		m_GameWindow = gameWindow ;  
		m_GameWindow.setSize(width, height);
		m_GameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		m_GameWindow.setVisible(true);
		m_GameWindow.setLayout(new BorderLayout());
		m_GameWindow.add(m_GameView) ; 
		m_GameView.setLayout(new FlowLayout());	
		m_fpscounter = new FPSCounter() ; 
		m_fpscounter.start();
		render() ; 
	}
	/**
	 * 
	 * @return the viewing rect
	 */
	public Rectangle2D.Float getViewRect(){
		return ViewRect ; 
	}
	
	/**
	 * add a gui element to our window
	 * @param toAdd Element we want to add
	 */
	public void AddGUIElement(JComponent toAdd){
		m_GameView.add(toAdd) ; 
		m_GameView.revalidate();
		m_GameView.repaint(); 
	}
	/**
	 * remove a gui element
	 * @param toremove remove this element
	 */
	public void RemoveGUIElement(JComponent toremove){
		m_GameView.remove(toremove);
		m_GameView.revalidate();
		m_GameView.repaint(); 
	}
	
	/**
	 * Override default flow layout for our panel
	 * @param New New Layout null for no layout
	 */
	public void OverrideLayout(LayoutManager New){
		m_GameView.setLayout(New);
	}
	/**
	 * Our Class for the game panel so we can specify our own rendering
	 * @author ftoet
	 *
	 */
	 class GameView extends JPanel {
		  @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        
		        doDrawing(g);
		    }
		  	public GameView(){
		  		super() ; 
		  	}
	}
	
	/**
	 * 
	 * @return the window
	 */
	public JFrame getGameWindow(){
		return m_GameWindow ; 
	}
	/**
	 * Rendering function
	 * @param g
	 */
	private void doDrawing(Graphics g) {
		boolean Debug = false ;  
		List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ; //get all object
		for (int i = 0 ; i < gameObjectsinScene.size() ; i++){
			GameObject Object = gameObjectsinScene.get(i) ;
				if (Object != null && Object.isActive()){ //if object is active
					Point2D.Float test = this.WorldCoordToScreenCoord(Object.getPosition()) ; // get the position in screen space

					if (Object.getSprite() != null){//if it has a renderable component
						Point2D.Float PointToRender = WorldCoordToScreenCoord(Object.getPosition()) ; // get the position in screen space
						g.drawImage(Object.getSprite().getImage(), (int)(PointToRender.x - (Object.getSprite().getWidth()/2)), ((int)PointToRender.y - (Object.getSprite().getHeight()/2)), null) ; // draw it in the middle of the sprite
					}
					if (Debug){ //when debug is active
						if (Object.getCollider() != null){
							Shape shape = Object.getCollider().getCollidingShape() ; //get the colliding shape
							
							if (shape instanceof Rectangle2D.Float){ // if collider is rectangle
								Rectangle2D.Float rect = (Rectangle2D.Float)shape ; 
								Point2D.Float PointToRender = WorldCoordToScreenCoord(new Point2D.Float((float)rect.getX(),(float)rect.getY())) ; 
								g.setColor(Color.GREEN);
								g.drawRect((int)PointToRender.x, (int)PointToRender.y,(int)( rect.getWidth()* m_GameWindow.getWidth() / ViewRect.getWidth()), (int)(rect.getHeight() * m_GameWindow.getHeight() / ViewRect.getHeight()) );
							}
							if (shape instanceof Ellipse2D.Float){// if collider is ellipse
								Ellipse2D.Float rect = (Ellipse2D.Float)shape ; 
								Point2D.Float PointToRender = WorldCoordToScreenCoord(new Point2D.Float((float)rect.getX(),(float)rect.getY())) ; 
								g.setColor(Color.GREEN);
								g.drawOval((int)PointToRender.x, (int)PointToRender.y,(int)( rect.getWidth()* m_GameWindow.getWidth() / ViewRect.getWidth()), (int)(rect.getHeight() * m_GameWindow.getHeight() / ViewRect.getHeight()) );
								
							}
						}
					}

				}
		}

		
	}
	/**
	 * Convert World to Screen coordinate
	 * @param WorldPoint Given point in world coordinates
	 * @return ScreenPoint returned point in screen coordinates
	 */
	public Point2D.Float WorldCoordToScreenCoord (Point2D.Float WorldPoint){
		Point2D.Float ScreenPoint = new Point2D.Float(0,0) ; 
		if (m_GameWindow != null && ViewRect != null && WorldPoint != null){
		ScreenPoint.y = (float) (m_GameWindow.getHeight() / ViewRect.getHeight() * WorldPoint.y) ; 
		ScreenPoint.x = (float) (m_GameWindow.getWidth() / ViewRect.getWidth() * WorldPoint.x) ; 
		}
		return ScreenPoint ; 
	}

	/**
	 * Convert ScreenPoint to WorldPoint
	 * @param ScreenPoint Given point in world coordinates
	 * @return WorldPoint returned point in screen coordinates
	 */
	public Point2D.Float ScreenCoordToWorldCoord (Point2D.Float ScreenPoint){
		Point2D.Float WorldPoint = new Point2D.Float(0,0) ;
		WorldPoint.y = (float) (ScreenPoint.y / (m_GameWindow.getHeight() / ViewRect.getHeight())) ;
		WorldPoint.x = (float) (ScreenPoint.x / (m_GameWindow.getHeight() / ViewRect.getHeight())) ;
		return WorldPoint ;
	}

	/**
	 * Convert ScreenPoint to WorldPoint
	 * @param ScreenPointNonFloat The point on the screen.
	 * @return The point in the world.
	 */
	public Point2D.Float ScreenCoordToWorldCoord (Point ScreenPointNonFloat){
		Point2D.Float ScreenPoint = new Point2D.Float(ScreenPointNonFloat.x, ScreenPointNonFloat.y) ; 
		Point2D.Float WorldPoint = new Point2D.Float(0,0) ;
		WorldPoint.y = (float) (ScreenPoint.y / (m_GameWindow.getHeight() / ViewRect.getHeight())) ;
		WorldPoint.x = (float) (ScreenPoint.x / (m_GameWindow.getHeight() / ViewRect.getHeight())) ;
		return WorldPoint ;
	}
	/**
	 * 
	 * @return gameView
	 */
	public GameView getGameView(){
		return m_GameView ; 
	}
	/**
	 * Render Thread
	 */
	private RenderThread Renderer ; 
	/**
	 * 
	 * @return the renderer
	 */
	public RenderThread getRenderer(){
		return Renderer ; 
	}
	/**
	 * Start the rendering
	 */
	private void render(){
		Renderer = new RenderThread(this) ; 
		Renderer.start();
	}
}
