package shooter.objects;

import framework.components.Animator;
import framework.main.GameObject;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eike Nils on 22.06.2017.
 */
public class AdvancedEnemy extends Enemy {


    public AdvancedEnemy(String PathToSprite, String Name, float posX, float posY) {
        super(PathToSprite, Name, posX, posY);
        setHealth(200);
        setValue(40);
        List<BufferedImage> myAnimation = new ArrayList<BufferedImage>() ;
        try {
            myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/Advanced/1.png")));
            myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/Advanced/2.png")));
            myAnimation.add(ImageIO.read(new File("Assets/PlaneSprites/Advanced/3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addComponent(new Animator(myAnimation,this,50));
    }

    @Override
    public void shoot() {
        if(lastMove == 1) {

            GameObject MyBullet1 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet1.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y + 1f));
            MyBullet1.Rotate(180 - 27) ;

            GameObject MyBullet2 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet2.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y + 1f));
            MyBullet2.Rotate(180 - 27) ;

        }
        if(lastMove == 0) {
            GameObject MyBullet1 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet1.setPosition(new Point2D.Float(this.getPosition().x+0.15f, this.getPosition().y + 1f));
            MyBullet1.Rotate(180 + 27) ;

            GameObject MyBullet2 = new MissleEnemy(200,new Point2D.Float(1, +4)) ;
            MyBullet2.setPosition(new Point2D.Float(this.getPosition().x-0.15f, this.getPosition().y + 1f));
            MyBullet2.Rotate(180 + 27) ;
        }
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
