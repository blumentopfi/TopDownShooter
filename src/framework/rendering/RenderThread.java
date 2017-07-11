package framework.rendering;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import framework.main.*;
import framework.rendering.Camera;  
/**
 * Thread that handles the rendering
 * @author ftoet
 *
 */
public class RenderThread extends Thread {
		long last_time  ; 
		private Camera m_RenderCamera ; //our Camera
		public RenderThread(Camera CameraWhichRenders){
			m_RenderCamera = CameraWhichRenders ; 
		}
		public void run(){
			last_time = System.nanoTime() ; 
			if (m_RenderCamera.m_fpscounter != null) m_RenderCamera.m_fpscounter.interrupt();
			while (!this.isInterrupted()){
				long time = System.nanoTime() ; 
				double check = time-last_time ;
				check = check/1000000000f ; 
				Time.deltaTime = check ; //Calculate delta Time
				last_time = time ; 
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
					SceneManager.getInstance().getMainCamera().getGameView().repaint(); //Call the repaint methond of panel
			}		
		}
		
		
}
