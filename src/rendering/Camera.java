package rendering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.GameManager;
import main.GameObject;
import rendering.RenderThread;
public class Camera extends Thread {
	protected Rectangle ViewRect ;
	protected JFrame  m_GameWindow ; 
	protected GameView m_GameView ; 
	public Camera(int height , int width,Rectangle ViewRect){
		m_GameView = new GameView() ; 
		this.ViewRect = ViewRect ; 
		m_GameWindow = new JFrame("Top Down Shooter") ; 
		m_GameWindow.setSize(width, height);
		m_GameWindow.setVisible(true);
		m_GameWindow.setLayout(new BorderLayout());
		m_GameWindow.add(m_GameView);
	}
	public Rectangle getViewRect(){
		return ViewRect ; 
	}
	public class GameView extends JPanel {
		  @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);

		        doDrawing(g);

		        Toolkit.getDefaultToolkit().sync();
		    }
	}
	
	
	public JFrame getGameWindow(){
		return m_GameWindow ; 
	}
	public void doDrawing(Graphics g) {
		
		List<GameObject> gameObjectsinScene = GameManager.getInstance().GetAllGameObjectsInScene() ; 
		for (GameObject Object: gameObjectsinScene){	
				if (Object != null){
				Point2D.Float PointToRender = WorldCoordToScreenCoord(Object.getPosition()) ; 
				g.drawImage(Object.GetSprite(), (int)(PointToRender.x - (Object.GetSprite().getWidth()/2)), ((int)PointToRender.y - (Object.GetSprite().getHeight()/2)), null) ; 
				}
		}
		
	}
	public Point2D.Float WorldCoordToScreenCoord (Point2D.Float WorldPoint){
		Point2D.Float ScreenPoint = new Point2D.Float(0,0) ; 
		ScreenPoint.y = (float) (m_GameWindow.getHeight() / ViewRect.getHeight() * WorldPoint.y) ; 
		ScreenPoint.x = (float) (m_GameWindow.getWidth() / ViewRect.getWidth() * WorldPoint.x) ; 
		return ScreenPoint ; 
	}
	
	public JPanel getGameView(){
		return m_GameView ; 
	}
	/*private RenderThread Renderer ; 
	public RenderThread getRenderer(){
		return Renderer ; 
	}
	
	private void render(){
		Renderer = new RenderThread(this) ; 
		Renderer.start();
	}*/
}
