package framework.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

public abstract class Collider extends Component {
	GameObject myObject ; //Object the Collider is assgined to
	Shape collidingShape ; 
	boolean HasOverridenSize = false ; 
	/**
	 * Mother Class for the Collider 
	 * @param collidingRectangle
	 * @param initObject
	 */
	public Collider(GameObject initObject){
		this.myObject = initObject ; 
	}
	/**
	 * ColliderUpdate function where we set the position of the Collider to the position of the assigned object
	 */
	@Override
	public void ComponentUpdate() {
		this.setX((float)( myObject.getPosition().getX()-this.collidingShape.getBounds2D().getWidth()/2)); 
		this.setY((float) (myObject.getPosition().getY()-this.collidingShape.getBounds2D().getHeight()/2)) ; 
	}
	
	abstract void setX(float newX) ; 
	abstract void setY(float newY) ; 
	public abstract float getX() ; 
	public abstract float getY() ; 
	public abstract boolean intersects(Collider A) ; 
	/**
	 * If the size of the objects changes we have to change our collider
	 */
	public void UpdateCollider(){
		if (!HasOverridenSize)this.collidingShape = gameObjectToShape(this.myObject);
	}
	/**
	 * Getter for the CollidingShape
	 * @return Shape
	 */
	public Shape getCollidingShape(){
		return this.collidingShape ;
	}
	abstract Shape gameObjectToShape(GameObject object) ; 
	/**
	 * Convert the Image of an Rectangle in Screen Size to a rectangle in GameSize
	 * @param object
	 * @return Rectangle in Game Size
	 */
}
