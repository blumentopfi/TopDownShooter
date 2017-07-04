package shooter.objects;

import framework.components.Collider;
import framework.components.OvalCollider;
import framework.components.RectangleCollider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;
import shooter.UI.HealthBar;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import static shooter.objects.Enemy.random;

/**
 * Created by 5knopp on 01.06.2017.
 */
public class Boss extends GameObject {

    public float speed = 1.5f;
    public int health = 3000;

    public static int MOVE_DISTANCE = 100;
    public int value = 20 ;
    public int moveCounter = 0;
    public int lastMove = 2;

    long FireRate = 200  ;
    long NextFire = 0  ;

    GameManager manager ;

    public Boss(String PathToSprite, String Name, int new_health, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.health = new_health;

        this.addComponent(new OvalCollider(this, 1.2));
        manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ;
        this.setDimension(new Dimension((int)(this.getWidth()*2.0),(int)(this.getHeight()*2.0)));
      
    }

    public int getValue(){
        return value ;
    }
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
    public void Update(){
        super.Update();
        move();
        if (NextFire < System.currentTimeMillis() ){
            if (manager.Wave > 1){
        	this.shootTriple(2);
        	this.shootTriple(-2);}else{
        		shootSingle() ; 
        	}
            NextFire = System.currentTimeMillis() + FireRate ;
        }

        if (health <= 0 ){
            manager.AddScore(this.getValue());
            this.Destroy();
        }
    }

    public void move() {
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

    private void shootSingle(){
        double maxWidth = this.getWidth();
        double maxHeight = this.getHeight();

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

    public void addDamage(int damage){
        health -= damage ;
    }

    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public void Destroy(){
        super.Destroy();
        manager.killedBoss();
        
        Random random = new Random(System.nanoTime());
    	new Thread(() -> {
    		float x = this.getPosition().x ; 
            float y = this.getPosition().y ; 
            float random_x_offset = 0 ; 
            float random_y_offset = 0 ; 
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
       

    	
        System.out.println("killedBoss");

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
