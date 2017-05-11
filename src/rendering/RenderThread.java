package rendering;
import main.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.List;

import rendering.Camera;  
public class RenderThread extends Thread {
		private long sleepTime = 1000/60 ; 
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
			long taskTime = System.nanoTime(); 
			long time1 = System.nanoTime() ; 
			if (SceneManager.getInstance().getMainCamera() != null){
			SceneManager.getInstance().getMainCamera().getGameView().repaint();
			}
			taskTime = System.nanoTime() - taskTime ; 
			long time2 = System.nanoTime() ; 
			if (sleepTime - (taskTime) > 0 ){
				try {
					this.sleep(sleepTime-(taskTime/1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Time.deltaTime=(0.00001f* (time2-time1));
			//System.out.println(Time.deltaTime);
		}
		
		
}
