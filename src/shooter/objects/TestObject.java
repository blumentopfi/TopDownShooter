package shooter.objects;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import framework.components.Animator;
import framework.components.RectangleCollider;
import framework.components.Sprite;
import framework.main.GameObject;

public  class TestObject extends GameObject {
	Animator myAnimator ; 
	public TestObject(String Name){
		super(Name) ; 
		this.addComponent(new Sprite("Assets/ProjectileSprite/Explosion/0.png",this));
		this.addComponent(new RectangleCollider(this));
	}
	public void Update(){
	}

}
