package framework.rendering;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import framework.main.*;
import framework.rendering.Camera;  
public class RenderThread extends Thread {
		long lastTime  ; 
		private Camera m_RenderCamera ; 
		public RenderThread(Camera CameraWhichRenders){
			m_RenderCamera = CameraWhichRenders ; 
		}
		public void run(){
			lastTime = System.nanoTime() ; 
			while (!this.isInterrupted()){
				long time = System.nanoTime() ; 
				Time.deltaTime =  ((time - lastTime) / 1000000000f ); 
				lastTime = time ; 
				render() ;  
				try {
					this.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}
		

		
		private void render () { 
			if (SceneManager.getInstance().getMainCamera() != null){
			SceneManager.getInstance().getMainCamera().getGameView().repaint();
			}
		
		}
		
		
}
