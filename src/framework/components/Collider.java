package framework.components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import framework.main.GameObject;
import framework.main.SceneManager;
import framework.rendering.Camera;

public class Collider extends Component {
	GameObject myObject ; 
	Rectangle2D.Float collidingRectangle ; 
	public Collider(Rectangle2D.Float collidingRectangle,GameObject initObject){
		this.myObject = initObject ; 
		this.collidingRectangle = gameObjectToRectangle(this.myObject);
	}
	
	@Override
	public void ComponentUpdate() {
		this.collidingRectangle.x = (float)( myObject.getPosition().getX()-this.collidingRectangle.getWidth()/2) ; 
		this.collidingRectangle.y = (float) (myObject.getPosition().getY()-this.collidingRectangle.getHeight()/2) ; 
	}
	public void UpdateCollider(){
		this.collidingRectangle = gameObjectToRectangle(this.myObject);
	}
	public Rectangle2D.Float getCollidingRectangle(){
		return this.collidingRectangle ;
	}
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
