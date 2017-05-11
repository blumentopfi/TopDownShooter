package objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import Components.Collider;
import Components.Sprite;
import main.GameObject;
import main.SceneManager;


public class Background extends GameObject {
	public Background() {
        super("Background");
        this.setPosition(new Point2D.Float(5,0));
        this.addComponent(new Sprite("Assets/Splashscreens/map.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
    }
	
	public Background(Point2D.Float x) {
		super("Background");
        this.setPosition(x);
        this.addComponent(new Sprite("Assets/Splashscreens/map.png",this));
        this.setDimension(new Dimension(SceneManager.getInstance().getMainCamera().getGameWindow().getWidth(),SceneManager.getInstance().getMainCamera().getGameWindow().getHeight()*2));
	}
	
	public void Update(){
		this.setPosition(new Point2D.Float(this.getPosition().x,this.getPosition().y + 0.01f ));
		if (this.getPosition().y >= 10){
			this.setPosition(new Point2D.Float(5, 0));
			}
	}
}
