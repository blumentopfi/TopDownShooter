package objects;

import main.GameObject;
import main.SceneManager;
import rendering.Time;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import Components.Collider;
import Components.Sprite;

/**
 * Created by Eike Nils on 21.04.2017.
 */
public class Enemy extends GameObject {

    public float speed = 1.5f;
    public int health = 100;
    public int value = 20 ; 
    GameManager manager ; 
    
    public Enemy(String PathToSprite, String Name, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
        manager = (GameManager)main.SceneManager.getInstance().getGameObjectByName("Manager") ; 
        this.setDimension(new Dimension((int)(this.getWidth()*0.6),(int)(this.getHeight()*0.6)));
    }
    
    public int getValue(){
    	return value ; 
    }

    public void Update(){
    	super.Update();
        this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + (float)(speed * Time.deltaTime)));
       // System.out.println(this.getPosition());
        if (this.getPosition().y > SceneManager.getInstance().getMainCamera().getViewRect().getMaxY()){
			this.Destroy();
		}
        if (health <= 0 ){
        	manager.AddScore(this.getValue());
        	this.Destroy();
        }
    }
    public void addDamage(int damage){
    	health -= damage ; 
    }
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }
    public void Destroy(){
    	super.Destroy(); 
    	Random random = new Random(System.nanoTime()) ; 
    	int drop = random.nextInt(10) ; 
    	if (drop == 1){
    	new HealthPowerUp(this.getPosition(),50) ; 
    	}
    	if (drop == 2){
    		new DamagePowerUp(this.getPosition(),50) ; 
    	}
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
