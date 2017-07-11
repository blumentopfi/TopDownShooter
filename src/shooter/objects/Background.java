package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
/**
 * Scrolling looping Background
 * @author Fin
 * @version 1.2
 *
 */
public class Background extends GameObject {
	/**
	 * Init at default Point
	 */
	public Background() {
        super("Background");
        this.setPosition(new Point2D.Float(5,0));
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
    }
	/**
	 * If it is the second init at other default point
	 * @param test
	 */
	public Background(boolean test) {
        super("Background");
        this.setPosition(new Point2D.Float(5,-19.9f));
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
    }
	/**
	 * Init at user Point
	 * @param x
	 */
	public Background(Point2D.Float x) {
		super("Background");
        this.setPosition(x);
        this.addComponent(new Sprite("Assets/Splashscreens/map_2_fixed.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		this.setPosition(new Point2D.Float(this.getPosition().x,this.getPosition().y + 0.03f ));
		if (this.getPosition().y >= 20){ //If we get out of the screen reset to the top
			this.setPosition(new Point2D.Float(5, -18.7f));
			}
	}
}
