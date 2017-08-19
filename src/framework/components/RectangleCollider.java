package framework.components;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * Rectangle Collider component, used for collision
 * @author Fin
 *@version 1.0
 */
public class RectangleCollider extends Collider {
	/**
	 * Constructor to init collider with user specified size
	 * @param collidingRectangle colliding Shape
	 * @param initObject Mother Object
	 */
	public RectangleCollider(Rectangle2D.Float collidingRectangle, GameObject initObject) {
		super(initObject);
		this.collidingShape = collidingRectangle ; 
		this.HasOverridenSize = true ; 
	}
	/**
	 * Constructor to init collider with size of object
	 * @param initObject Mother Object
	 */
	public RectangleCollider(GameObject initObject) {
		super(initObject);
	
		this.collidingShape = this.gameObjectToShape(initObject) ; 
	}

	@Override
	void setX(float newX) {
		Rectangle2D.Float toSet = (Rectangle2D.Float)this.collidingShape ; 
		toSet.x = newX ; 
	}

	@Override
	void setY(float newY) {
		Rectangle2D.Float toSet = (Rectangle2D.Float)this.collidingShape ; 
		toSet.y = newY ; 
	
	}
	@Override
	public float getX(){
		Rectangle2D.Float toSet = (Rectangle2D.Float)this.collidingShape ; 
		return toSet.x ; 
	}
	@Override
	public float getY(){
		Rectangle2D.Float toSet = (Rectangle2D.Float)this.collidingShape ; 
		return toSet.y ; 
	}

	@Override
	Shape gameObjectToShape(GameObject object) {
		Point2D.Float point = object.getPosition() ; 
		Rectangle2D.Float rec = new Rectangle2D.Float() ; 
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

}
