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
			GameManager.getInstance().getMainCamera().getGameView().repaint();
		}
		
		
}
