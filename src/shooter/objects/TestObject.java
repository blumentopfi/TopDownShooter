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
import framework.main.Scene;
import framework.main.SceneManager;
import shooter.scenes.TestScene;

public  class TestObject extends GameObject {
	TestScene myScene ; 
	public TestObject(String Name,TestScene testScene){
		super(Name) ; 
		this.myScene = testScene ; 
	}
	public void Update(){
		if (SceneManager.getInstance().getGameObjectByName("Enemy") == null){
			this.myScene.EnemyDead(); 
			this.Destroy(); 
		}
	}


}
