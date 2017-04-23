package rendering;
import main.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import rendering.Camera;  
public class RenderThread extends Thread {
	
		private Camera m_RenderCamera ; 
		public RenderThread(Camera CameraWhichRenders){
			m_RenderCamera = CameraWhichRenders ; 
		}
		public void run(){
			while (!this.isInterrupted()){
				render() ; 
			}
		}
		

		
		private void render () {
			SceneManager.getInstance().getMainCamera().getGameView().repaint();
		}
		
		
}
