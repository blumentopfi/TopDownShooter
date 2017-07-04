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
/**
 * Abstract mother class for our Colliders
 * @author Fin
 * @version 1.5
 * Switched to Abstract Class to implement more Collider Types
 */
public abstract class Collider extends Component {
	GameObject myObject ; //Object the Collider is assgined to
	Shape collidingShape ; //Shape of the Collider
	boolean HasOverridenSize = false ; //See if user set size manually
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
	/**
	 * see if this Collider interesects with another
	 * @param A
	 * @return
	 */
	public abstract boolean intersects(Collider A) ; 
	/**
	 * 
	 * @return Width
	 */
	public  float getWidth(){
		return (float) this.collidingShape.getBounds2D().getWidth();
	}
	/**
	 * 
	 * @return Height
	 */
	public  float getHeight() {
		return (float) this.collidingShape.getBounds2D().getHeight();
	}
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
	/**
	 * return the gameObject in form of the colliding Shape 
	 * @param object
	 * @return
	 */
	abstract Shape gameObjectToShape(GameObject object) ; 
}
