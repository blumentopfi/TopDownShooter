package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;

/**
 * 
 * @author 7toeter
 *
 */
public class Background extends GameObject {
	public Background() {
        super("Background");
        this.setPosition(new Point2D.Float(5,0));
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
    }
	public Background(boolean test) {
        super("Background");
        this.setPosition(new Point2D.Float(5,-20f));
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
    }	
	public Background(Point2D.Float x) {
		super("Background");
        this.setPosition(x);
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
	}
	
	public void Update(){
		this.setPosition(new Point2D.Float(this.getPosition().x,this.getPosition().y + 0.03f ));
		if (this.getPosition().y >= 20){
			this.setPosition(new Point2D.Float(5, -18.7f));
			}
	}
}
