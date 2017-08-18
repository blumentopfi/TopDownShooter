package shooter.objects;

import framework.components.Animator;
import framework.main.GameObject;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eike Nils on 22.06.2017.
 * Version of "Enemy" with more health and more damage.
 */
public class AdvancedEnemy extends Enemy {

    /**
     * Constructor for AdvancedEnemy
     * @param PathToSprite Path to the Sprite.
     * @param Name Name of the Object.
     * @param posX Starting-coordinate X.
     * @param posY Starting-coordinate Y.
     */
    AdvancedEnemy(String PathToSprite, String Name, float posX, float posY) {
        super(PathToSprite, Name, posX, posY);
        setHealth(200);
        setValue(40);
        List<BufferedImage> myAnimation = new ArrayList<>() ;
        try {
            myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/Advanced/1.png")));
            myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/Advanced/2.png")));
            myAnimation.add(ImageIO.read(this.getClass().getResource("/Assets/PlaneSprites/Advanced/3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addComponent(new Animator(myAnimation,this,50));
    }

    /**
     * Shooting-function for the Enemy. Dual-Shot is standard for advanced enemies.
     * Direction of the Bullet is dependent on the last move the plane made.
     */
    @Override
    public void shoot() {
        //Last move was right.
        if(lastMove == 1) {

            GameObject MyBullet1 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet1.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y + 1f));
            MyBullet1.Rotate(180 - 27) ;

            GameObject MyBullet2 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet2.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y + 1f));
            MyBullet2.Rotate(180 - 27) ;

        }
        //Last move was left.
        if(lastMove == 0) {
            GameObject MyBullet1 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet1.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y + 1f));
            MyBullet1.Rotate(180 + 27) ;

            GameObject MyBullet2 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet2.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y + 1f));
            MyBullet2.Rotate(180 + 27) ;
        }
        //Last move was straight.
        if(lastMove == 2) {
            GameObject MyBullet1 = new MissleEnemy(200,new Point2D.Float(0, +4)) ;
            MyBullet1.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y + 1f));
            MyBullet1.Rotate(180) ;

            GameObject MyBullet2 = new MissleEnemy(200,new Point2D.Float(0, +4)) ;
            MyBullet2.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y + 1f));
            MyBullet2.Rotate(180) ;
        }
    }


}
