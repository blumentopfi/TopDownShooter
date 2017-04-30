package objects;

import main.GameObject;
import main.SceneManager;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Components.Collider;
import Components.Sprite;

/**
 * Created by Eike Nils on 21.04.2017.
 */
public class Enemy extends GameObject {

    public float speed = 0.03f;
    public int health = 100;
    public int value = 20 ; 
    
    
    public Enemy(String PathToSprite, String Name, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
       
    }
    
    public int getValue(){
    	return value ; 
    }

    public void Update(){
    	super.Update();
        this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + speed));
        if (this.getPosition().y > SceneManager.getInstance().getMainCamera().getViewRect().getMaxY()){
			this.Destroy();
		}
    }

    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
