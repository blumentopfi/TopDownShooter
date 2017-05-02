package rendering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import main.SceneManager;
import main.GameObject;
import rendering.RenderThread;
public class Camera extends Thread {
	protected Rectangle ViewRect ;
	protected JFrame  m_GameWindow ; 
	protected GameView m_GameView ; 
	protected JLabel ScoreLabel = new JLabel("Score: ",JLabel.LEFT);
	protected List<JComponent> GUIElements  = new ArrayList<JComponent>() ; 
	public Camera(int height , int width,Rectangle ViewRect){
		m_GameView = new GameView() ; 
		this.ViewRect = ViewRect ; 
		m_GameWindow = new JFrame("Top Down Shooter") ; 
		m_GameWindow.setSize(width, height);
		m_GameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		m_GameWindow.setVisible(true);
		m_GameWindow.setLayout(new BorderLayout());
		m_GameWindow.add(m_GameView);
		m_GameView.setLayout(new FlowLayout());
		ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
		m_GameView.add(ScoreLabel);
		render() ; 
	}
	public Rectangle getViewRect(){
		return ViewRect ; 
	}
	
	public JLabel getScoreLabel(){
		return ScoreLabel ; 
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
		boolean Debug = true ; 
		List<GameObject> gameObjectsinScene = SceneManager.getInstance().GetAllGameObjectsInScene() ; 
		for (GameObject Object: gameObjectsinScene){	
				if (Object != null){
					if (Object.getSprite() != null){
						Point2D.Float PointToRender = WorldCoordToScreenCoord(Object.getPosition()) ; 
						g.drawImage(Object.getSprite().getImage(), (int)(PointToRender.x - (Object.getSprite().getWidth()/2)), ((int)PointToRender.y - (Object.getSprite().getHeight()/2)), null) ;
					}
					if (Debug){
						if (Object.getCollider() != null){
							Rectangle2D.Float rect = Object.getCollider().getCollidingRectangle() ;
							Point2D.Float PointToRender = WorldCoordToScreenCoord(new Point2D.Float((float)rect.getX(),(float)rect.getY())) ; 
							g.setColor(Color.GREEN);
							g.drawRect((int)PointToRender.x, (int)PointToRender.y,(int)( rect.getWidth()* m_GameWindow.getWidth() / ViewRect.getWidth()), (int)(rect.getHeight() * m_GameWindow.getHeight() / ViewRect.getHeight()) );
						}
					}
				}
		}
		
		
	}
	public Point2D.Float WorldCoordToScreenCoord (Point2D.Float WorldPoint){
		Point2D.Float ScreenPoint = new Point2D.Float(0,0) ; 
		ScreenPoint.y = (float) (m_GameWindow.getHeight() / ViewRect.getHeight() * WorldPoint.y) ; 
		ScreenPoint.x = (float) (m_GameWindow.getWidth() / ViewRect.getWidth() * WorldPoint.x) ; 
		return ScreenPoint ; 
	}

	public Point2D.Float ScreenCoordToWorldCoord (Point2D.Float ScreenPoint){
		Point2D.Float WorldPoint = new Point2D.Float(0,0) ;
		WorldPoint.y = (float) (m_GameWindow.getHeight() / ViewRect.getHeight() * ScreenPoint.y) ;
		WorldPoint.x = (float) (m_GameWindow.getWidth() / ViewRect.getWidth() * ScreenPoint.x) ;
		return WorldPoint ;
	}
	
	public JPanel getGameView(){
		return m_GameView ; 
	}
	private RenderThread Renderer ; 
	public RenderThread getRenderer(){
		return Renderer ; 
	}
	
	private void render(){
		Renderer = new RenderThread(this) ; 
		Renderer.start();
	}
}
