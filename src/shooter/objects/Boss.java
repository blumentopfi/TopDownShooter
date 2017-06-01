package shooter.objects;

import framework.components.Collider;
import framework.components.Sprite;
import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Time;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by 5knopp on 01.06.2017.
 */
public class Boss extends GameObject {
    public float speed = 1.5f;
    public int health = 10000;
    public static int MOVE_DISTANCE = 100;
    public int value = 20 ;
    public int moveCounter = 0;
    public int tempMove = 4;
    long FireRate = 500  ;
    long NextFire = 0  ;
    GameManager manager ;

    public Boss(String PathToSprite, String Name, float posX, float posY) {
        super(Name);
        this.setPosition(new Point2D.Float(posX, posY));
        this.addComponent(new Sprite(PathToSprite,this));
        this.addComponent(new Collider(new Rectangle2D.Float(0,0,1,1),this));
        manager = (GameManager)framework.main.SceneManager.getInstance().getGameObjectByName("Manager") ;
        this.setDimension(new Dimension((int)(this.getWidth()*2.0),(int)(this.getHeight()*2.0)));
    }

    public int getValue(){
        return value ;
    }

    public void Update(){
        System.out.println("BOSS UPDATE");
        super.Update();
        if (NextFire < System.currentTimeMillis() ){
            this.shootSingle();
            NextFire = System.currentTimeMillis() + FireRate ;
        }

        if (health <= 0 ){
            manager.AddScore(this.getValue());
            this.Destroy();
        }
    }
    public void move() {


    }
    private void shootSingle(){
        System.out.println("BOSS FIRE");
        double maxWidth = this.getWidth();
        double maxHeight = this.getHeight();

        Point2D.Float xL = SceneManager.getInstance().getMainCamera().WorldCoordToScreenCoord(this.getPosition());

        double PosX = xL.getX();

        GameObject MyBulletLeft = new MissleEnemy(25, new Point2D.Float(0,+4));
        GameObject MyBulletRight = new MissleEnemy(25, new Point2D.Float(0, +4));

        Point2D.Float posXPointL = SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(new Point2D.Float((float) (PosX - (maxWidth / 2)), this.getPosition().y + 1f));
        Point2D.Float posXPointR = SceneManager.getInstance().getMainCamera().ScreenCoordToWorldCoord(new Point2D.Float((float) (PosX + (maxWidth / 2)), this.getPosition().y + 1f));
        System.out.println("LX" + posXPointL.x);
        System.out.println("LY" + posXPointL.y);
        System.out.println("RX" + posXPointR.x);
        System.out.println("RY" + posXPointR.y);
        MyBulletLeft.setPosition(posXPointL);
        MyBulletRight.setPosition(posXPointR);

        MyBulletLeft.Rotate(180) ;
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
