package Components;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import main.GameObject;

public class Collider extends Component {
	GameObject myObject ; 
	Rectangle2D.Float collidingRectangle ; 
	public Collider(Rectangle2D.Float collidingRectangle,GameObject initObject){
		this.myObject = initObject ; 
		this.collidingRectangle = collidingRectangle ; 
	}
	
	@Override
	public void ComponentUpdate() {	
		this.collidingRectangle.x = (float)( myObject.getPosition().getX()-this.collidingRectangle.getWidth()/2) ; 
		this.collidingRectangle.y = (float) (myObject.getPosition().getY()-this.collidingRectangle.getHeight()/2) ; 
	}
	public Rectangle2D.Float getCollidingRectangle(){
		return this.collidingRectangle ;
	}
}
