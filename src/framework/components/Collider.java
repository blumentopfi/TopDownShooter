package framework.components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

public class Collider extends Component {
	GameObject myObject ; //Object the Collider is assgined to
	Rectangle2D.Float collidingRectangle ; 
	/**
	 * Mother Class for the Collider 
	 * @param collidingRectangle
	 * @param initObject
	 */
	public Collider(Rectangle2D.Float collidingRectangle,GameObject initObject){
		this.myObject = initObject ; 
		this.collidingRectangle = gameObjectToRectangle(this.myObject);
	}
	/**
	 * ColliderUpdate function where we set the position of the Collider to the position of the assigned object
	 */
	@Override
	public void ComponentUpdate() {
		this.collidingRectangle.x = (float)( myObject.getPosition().getX()-this.collidingRectangle.getWidth()/2) ; 
		this.collidingRectangle.y = (float) (myObject.getPosition().getY()-this.collidingRectangle.getHeight()/2) ; 
	}
	/**
	 * If the size of the objects changes we have to change our collider
	 */
	public void UpdateCollider(){
		this.collidingRectangle = gameObjectToRectangle(this.myObject);
	}
	/**
	 * Getter for the CollidingShape
	 * @return Shape
	 */
	public Rectangle2D.Float getCollidingRectangle(){
		return this.collidingRectangle ;
	}
	/**
	 * Convert the Image of an Rectangle in Screen Size to a rectangle in GameSize
	 * @param object
	 * @return Rectangle in Game Size
	 */
	public Rectangle2D.Float gameObjectToRectangle(GameObject object) {
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
