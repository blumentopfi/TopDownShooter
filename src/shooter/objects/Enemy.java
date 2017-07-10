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
    public int health = 100;
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

    /**
     * Constructor of the Base-Class "Enemy"
     * @param PathToSprite The Path to the Sprite.
     * @param Name The Name of the game-object.
     * @param posX Starting-coordinate X.
     * @param posY Starting-coordinate Y.
     */
    public Enemy(String PathToSprite, String Name, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.addComponent(new RectangleCollider(this));
        manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ; 
        this.setDimension(new Dimension((int)(this.getWidth()*0.6),(int)(this.getHeight()*0.6)));
    }

    /**
     * Returns the Value (score) of the Enemy.
     * @return The score of the Enemy.
     */
    public int getValue(){
    	return value ; 
    }

    /**
     * Set the value of the Enemy.
     * @param newValue The score value to set to.
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

    /**
     * Update-function for this game-object.
     * Gets called by the Timer and manages all behavior of this object.
     */
    public void Update(){
    	super.Update();
    	this.move();
        if (NextFire < System.currentTimeMillis() ){
            this.shoot();
            NextFire = System.currentTimeMillis() + FireRate ;
        }
        if (this.getPosition().y > SceneManager.getInstance().getMainCamera().getViewRect().getMaxY()){
        	System.out.println("Destroy because of Clamping");
			this.Destroy();
		}
        if (health <= 0 ){
        	if (manager != null) manager.AddScore(this.getValue());
        	System.out.println("Destroyed by missing Helath");
        	this.Destroy();
        }
    }

    /**
     * Generates random movement.
     * Left, right or stationary and does not fly out of the viewport.
     */
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

    /**
     * Abstract function for shooting.
     * Is implemented by SimpleEnemy and AdvancedEnemy.
     */
    public abstract void shoot();

    /**
     * Deduct damage from the enemies current health.
     * @param damage The damage to deduct.
     */
    public void addDamage(int damage){
    	health -= damage ; 
    }

    /**
     * Set the speed of the enemy.
     * @param newSpeed The speed to set to.
     */
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    /**
     * Destroy the enemy and spawn a random PowerUp.
     */
    public void Destroy(){
    	super.Destroy();
    	if (manager != null) manager.addKilledEnemy();
    	
    	Random random = new Random(System.nanoTime()) ; 
    	int drop = random.nextInt(10) ; 
    	if (drop == 1){
    	new HealthPowerUp(this.getPosition(),1000) ;
    	}
    	if (drop == 2){
    		new DamagePowerUp(this.getPosition(),50) ; 
    	}
    	if (drop == 3){
    		new UpgradePowerUp(this.getPosition()) ;  
    	}
    	if (this.getHealth() <= 0 ){
    	ExplosionPlane e = (ExplosionPlane)manager.ExplosionsPlane.getExplosion() ; 
    	e.setPosition(this.getPosition());
    	e.Boom();
    	}
    	//e.setDimension(new Dimension((int)e.getTransform().getSize().getWidth(),(int)e.getTransform().getSize().getHeight()));
    	
    }

    /**
     * Get the speed of the enemy.
     * @return The speed of the enemy.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Get the current health of the enemy.
     * @return The current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the health of the enemy.
     * @param health The health to set to.
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
