package objects;

import main.GameObject;

import java.awt.geom.Point2D;

/**
 * Created by Eike Nils on 21.04.2017.
 */
public class Enemy extends GameObject {

    public float speed = 0.03f;
    public int health = 100;

    public Enemy(String PathToSprite, String Name, float posX, float posY) {
        super(PathToSprite, Name);
        this.setPosition(new Point2D.Float(posX, posY));
    }

    public void Update(){
    	super.Update();
        this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + speed));
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
