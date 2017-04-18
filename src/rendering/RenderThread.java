package rendering;
import main.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import rendering.Camera;  
public class RenderThread extends Thread {
	
		private boolean somethingChanged = true ; 
		private Camera m_RenderCamera ; 
		public RenderThread(Camera CameraWhichRenders){
			m_RenderCamera = CameraWhichRenders ; 
		}
		public void run(){
			while (!this.isInterrupted()){
				render() ; 
			}
		}
		
		public void repaint(){
			somethingChanged = true ; 
		}
		
		private void render () {
			List<GameObject> gameObjectsinScene = GameManager.getInstance().GetAllGameObjectsInScene() ; 
			for (GameObject g : gameObjectsinScene){
				if (m_RenderCamera.ViewRect.contains(g.getTransform().getPosition())){
					Graphics2D graphics = (Graphics2D) m_RenderCamera.getGameView().getGraphics() ;
					Point2D.Float PointToRender = m_RenderCamera.WorldCoordToScreenCoord(g.getPosition()) ; 
					graphics.drawImage(g.GetSprite(), (int)PointToRender.x, (int)PointToRender.y, null) ; 
					//System.out.println("Rendering" + g.getName());
				}
			}
			 
			try {
				this.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
}
