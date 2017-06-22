package shooter.objects;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Random;

import framework.components.RectangleCollider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;

/**
 * Created by Eike Nils on 21.04.2017.
 */
public abstract class Enemy extends GameObject {

    public float speed = 1f;
    public int health;
    public int value;



    long FireRate = 500  ;
    long NextFire = 0  ;

    public static int MOVE_DISTANCE = 100;
    public boolean isRotated = false;
    public int moveCounter = 0;
    public int tempMove = 4;
    static Random random = new Random() ;
    // 0 = left, 1 = right, 2 = straight, 3 = initial.
    public int lastMove = 3;

    GameManager manager ;
    
    public Enemy(String PathToSprite, String Name, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.addComponent(new RectangleCollider(this));
        manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ; 
        this.setDimension(new Dimension((int)(this.getWidth()*0.6),(int)(this.getHeight()*0.6)));
    }
    
    public int getValue(){
    	return value ; 
    }
    public void setValue(int newValue) {
        this.value = newValue;
    }

    public void Update(){
    	super.Update();
    	this.move();
        if (NextFire < System.currentTimeMillis() ){
            this.shoot();
            NextFire = System.currentTimeMillis() + FireRate ;
        }
        if (this.getPosition().y > SceneManager.getInstance().getMainCamera().getViewRect().getMaxY()){
			this.Destroy();
		}
        if (health <= 0 ){
        	if (manager != null) manager.AddScore(this.getValue());
        	this.Destroy();
        }
    }
    public void move() {
        if(lastMove == 3) {
            lastMove = random.nextInt(3);
            if(lastMove == 0) {
                this.Rotate(27);
                isRotated = true;
            }
            if(lastMove == 1) {
                this.Rotate(-27);
                isRotated = true;
            }
        }

        if ((lastMove == 0) && moveCounter <= MOVE_DISTANCE) {
           // System.out.println("left");
            if(this.getPosition().x > 1) {
                this.setPosition(new Point2D.Float(this.getPosition().x - (float)((speed * Time.deltaTime) / 2f), this.getPosition().y + (float)(speed * Time.deltaTime)));
            }
            else {
                if(this.isRotated) {
                    this.Rotate(0);
                    this.isRotated = false;
                }
                this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + (float)(speed * Time.deltaTime)));
            }
            moveCounter++;
        }
        if ((lastMove == 1) && moveCounter <= MOVE_DISTANCE) {
           // System.out.println("right");
            if(this.getPosition().x < 9) {
                this.setPosition(new Point2D.Float(this.getPosition().x + (float)((speed * Time.deltaTime) / 2f), this.getPosition().y + (float)(speed * Time.deltaTime)));
            }
            else {
                if(this.isRotated) {
                    this.Rotate(0);
                    this.isRotated = false;
                }
                this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + (float)(speed * Time.deltaTime)));
            }
            moveCounter++;
        }

        if ((lastMove == 2) && moveCounter <= MOVE_DISTANCE) {
            //System.out.println("straight");
            this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y + (float)(speed * Time.deltaTime)));
            moveCounter++;
        }
        if(moveCounter > MOVE_DISTANCE) {
            tempMove = lastMove;
            if(lastMove == 0) {
                if(this.isRotated) {
                    this.Rotate(0);
                }
            }
            if(lastMove == 1) {
                if (this.isRotated) {
                    this.Rotate(0);
                }
            }
            isRotated = false;
            lastMove = 3;
            moveCounter = 0;
        }
    }
    public abstract void shoot();

    public void addDamage(int damage){
    	health -= damage ; 
    }
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }
    public void Destroy(){
    	super.Destroy();
    	if (manager != null) manager.addKilledEnemy();
    	Random random = new Random(System.nanoTime()) ; 
    	int drop = random.nextInt(10) ; 
    	if (drop == 1){
    	new HealthPowerUp(this.getPosition(),50) ; 
    	}
    	if (drop == 2){
    		new DamagePowerUp(this.getPosition(),50) ; 
    	}
    	if (drop == 3){
    		new UpgradePowerUp(this.getPosition()) ;  
    	}
    	//Explosion e = new Explosion("Explosion") ;
		//e.setPosition(this.getPosition());
		//e.setDimension(new Dimension(e.getTransform().getSize().width*3,e.getTransform().getSize().height *3 ));
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
