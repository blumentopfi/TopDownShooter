package shooter.objects;

import framework.components.OvalCollider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static shooter.objects.Enemy.random;

/**
 * Created by Eike Nils on 01.06.2017.
 * Game-object for a simple Boss-enemy.
 * Similar to "Enemy" - Class, but with different movement and scalable shooting-modes.
 */
public class Boss extends GameObject {

    private float speed = 1.5f;
    private int health = 3000;

    private static int MOVE_DISTANCE = 100;
    private int value = 20 ;
    private int moveCounter = 0;
    private int lastMove = 2;

    private long FireRate = 200  ;
    private long NextFire = 0  ;

    private GameManager manager ;

    /**
     * Constructor for Boss.
     * @param PathToSprite The Path to the Sprite.
     * @param Name The name of the object.
     * @param new_health Initial health of the boss.
     * @param posX Starting-coordinate X.
     * @param posY Starting-coordinate Y.
     */
    Boss(String PathToSprite, String Name, int new_health, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.health = new_health;

        this.addComponent(new OvalCollider(this, 1.2));
        manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ;
        this.setDimension(new Dimension((int)(this.getWidth()*2.0),(int)(this.getHeight()*2.0)));
      
    }

    /**
     * Returns the value (score) of this game-object.
     * @return The Value of this object.
     */
    private int getValue(){
        return value ;
    }
    /**
     * Shoot three bullets with a given offset from the Bosses Position
     * @param offset The spread between the bullets.
     */
	private void shootTriple(int offset){
		GameObject MyBullet = new MissleEnemy(25,new Point2D.Float(0, +4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+offset, this.getPosition().y -0.5f));
		MyBullet.Rotate(180);
		MyBullet = new MissleEnemy(25,new Point2D.Float(-1, 4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x-0.3f+offset, this.getPosition().y -0.5f));
		MyBullet.Rotate(180);
		MyBullet = null ; 
		MyBullet = new MissleEnemy(25,new Point2D.Float(+1, 4)) ; 
		MyBullet.setPosition(new Point2D.Float(this.getPosition().x+0.3f+offset, this.getPosition().y -0.5f));
		MyBullet.Rotate(180);
		MyBullet = null ; 
	}

    /**
     * Update-function for this game-object.
     * Gets called by the Timer and manages all behavior of this object.
     */
    public void Update(){
        super.Update();
        move();
        if (NextFire < System.currentTimeMillis() ){
            if (manager.Wave > 1){ //If player got wave 1 to die Boss gets harder
        	this.shootTriple(2);
        	this.shootTriple(-2);}else{
        		shootSingle() ; 
        	}
            NextFire = System.currentTimeMillis() + FireRate ;
        }

        if (health <= 0 ){ //If we die  give the Player a high score value
            manager.AddScore(this.getValue());
            this.Destroy();
        }
    }
    /**
     * Move randomly to the left, right or stay at the current position.
     * Similar to movement in "Enemy" - class.
     */
    private void move() {
        if(this.getPosition().y < 2) {
            this.setPosition(new Point2D.Float(this.getPosition().x, (float) (this.getPosition().y + speed *Time.deltaTime)));
        }
        if(lastMove == 2) {
            lastMove = random.nextInt(2);
        }

        if ((lastMove == 0) && moveCounter <= MOVE_DISTANCE) {
            if(this.getPosition().x > 1) {
                this.setPosition(new Point2D.Float(this.getPosition().x - (float)((speed * Time.deltaTime) / 2f), this.getPosition().y));
            }
            else {
                this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y));
            }
            moveCounter++;
        }
        if ((lastMove == 1) && (moveCounter <= MOVE_DISTANCE)) {
            if(this.getPosition().x < 9) {
                this.setPosition(new Point2D.Float(this.getPosition().x + (float)((speed * Time.deltaTime) / 2f), this.getPosition().y));
            }
            else {
                this.setPosition(new Point2D.Float(this.getPosition().x, this.getPosition().y));
            }
            moveCounter++;
        }

        if(moveCounter > MOVE_DISTANCE) {

            lastMove = 2;
            moveCounter = 0;
        }

    }
    /**
     * Shoot a single Bullet
     */
    private void shootSingle(){
        double maxWidth = this.getWidth();

        Point2D.Float xL = SceneManager.getInstance().getMainCamera().WorldCoordToScreenCoord(this.getPosition());

        double PosX = xL.getX();

        GameObject MyBulletLeft = new MissleEnemy(25, new Point2D.Float(0,+4));
        GameObject MyBulletRight = new MissleEnemy(25, new Point2D.Float(0, +4));

        Point2D.Float posXPointL = SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(new Point2D.Float((float) (PosX - (maxWidth / 2)), this.getPosition().y + 1f));
        Point2D.Float posXPointR = SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(new Point2D.Float((float) (PosX + (maxWidth / 2)), this.getPosition().y + 1f));
        posXPointL.y += 2f;
        posXPointR.y += 2f;
        MyBulletLeft.setPosition(posXPointL);
        MyBulletRight.setPosition(posXPointR);

        MyBulletLeft.Rotate(180);
        MyBulletRight.Rotate(180) ;
    }
    /**
     * Deduct the given damage from the boss's health.
     * @param damage The damage to deduct.
     */
    void addDamage(int damage){
        health -= damage ;
    }

    /**
     * Set the speed at which the boss moves.
     * @param newSpeed The speed of movement.
     */
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    /**
     * Destroy the boss and play a explosion-animation.
     */
    public void Destroy(){
        super.Destroy();
        manager.killedBoss();
        
        Random random = new Random(System.nanoTime());
        //AFTER THIS IS THE COOLEST THREADED EXPLOSION EFFECT BOOOOOOOOOOOOM
    	new Thread(() -> {
    		float x = this.getPosition().x ; 
            float y = this.getPosition().y ; 
            float random_x_offset;
            float random_y_offset;
    		 ExplosionPlane e ;
    		 for (int i = 0 ; i < 20 ; i++){
    			 e = (ExplosionPlane)manager.ExplosionsPlane.getExplosion() ; 
    			 random_y_offset = random.nextFloat()*(2-random.nextInt(5)) ; 
    			 random_x_offset = random.nextFloat()*(2-random.nextInt(5)) ;
    			 e.setPosition(new Point2D.Float(x + random_x_offset, y + random_y_offset));
    			 e.Boom();
    			 try {
					Thread.sleep(random.nextInt(80));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		 }
		}).start();

    }

    /**
     * Get the speed at which the boss moves.
     * @return The speed of the boss.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Retuns the current health of the boss.
     * @return The health of the boss.
     */
    int getHealth() {
        return health;
    }

    /**
     * Set the health of the boss.
     * @param health The value to set the health to.
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
