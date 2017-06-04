package framework.components;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

public class OvalCollider extends Collider {
	
	public OvalCollider(Ellipse2D.Float collidingEllipse, GameObject initObject) {
		super(initObject);
		this.collidingShape = collidingEllipse ; 
		this.HasOverridenSize = true ; 
	}
	public OvalCollider(GameObject initObject) {
		super(initObject);
		this.collidingShape = this.gameObjectToShape(initObject) ; 
		
	}

	@Override
	void setX(float newX) {
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		toSet.x = newX ; 
	}

	@Override
	void setY(float newY) {
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		toSet.y = newY ; 
	
	}
	@Override
	public float getX(){
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		return toSet.x ; 
	}
	@Override
	public float getY(){
		Ellipse2D.Float toSet = (Ellipse2D.Float)this.collidingShape ; 
		return toSet.y ; 
	}

	@Override
	Shape gameObjectToShape(GameObject object) {
		Point2D.Float point = object.getPosition() ; 
		Ellipse2D.Float rec = new Ellipse2D.Float() ; 
		Dimension d = new Dimension(object.getTransform().getSize()) ; 
		rec.x = point.x ; 
		rec.y = point.y ; 
		Camera camera = SceneManager.getInstance().getMainCamera() ; 	
		double height = (1000*camera.getViewRect().height*d.height)/camera.getGameWindow().getHeight()  ; 
		double width  = (1000*camera.getViewRect().width*d.width)/camera.getGameWindow().getWidth()  ; 
		rec.width = (float) width/1000 ; 
		rec.height = (float) height/1000 ; 
		return rec ; 
	}
	@Override
	public boolean intersects(Collider A) {
		return false;
	}

}
