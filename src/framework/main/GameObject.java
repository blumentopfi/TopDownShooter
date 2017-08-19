package framework.main;

import framework.components.Animator;
import framework.components.Collider;
import framework.components.Component;
import framework.components.Sprite;
import framework.geometry.Transform;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class for any objects that is in the game
 * @author Fin
 *
 */
public abstract class GameObject {
	private String m_name ; 
	private long m_id  ;
	private Transform m_transform ; 
	private boolean m_isActive = true ; 
	private final List<Component> m_Components = new ArrayList<>() ; // List of Components that are attached
	/**
	 * Add a component
	 * @param component Component we want to add
	 */
	public void addComponent(Component component){
		m_Components.add(component) ;
	}
	public void removeComponent(Component component){
		m_Components.remove(component) ; 
	}
	/**
	 * Destroy the gameObject
	 */
	public void Destroy(){
		m_Components.clear();
		SceneManager.getInstance().getGameObjectToDelete().add(this) ;
	}
	/**
	 * That happens on Collision
	 * @param collidingObject The object this collides with
	 */
	public void OnCollision(GameObject collidingObject) {
	}
	/**
	 * Set the rotation of the gameObject with absolute values : so rotate(90) = 90 degrees and not += 90
	 * @param Winkel Which degree we want this to rotate
	 */
	public void Rotate(int Winkel){
		Winkel = Winkel % 360 ; 
		if (this.getAnimator()!=null){
			int current_rotation = this.getSprite().rotation ;
			if (current_rotation > Winkel){
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(360 - current_rotation + Winkel);
						s.rotation = Winkel ; 
					}
				}
				
			}
			
			if(current_rotation < Winkel){
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(Winkel-current_rotation);
						s.rotation = Winkel ; 
					}
				}
			}
			
			this.getSprite().rotation = Winkel ; 
			this.m_transform.setRotation(Winkel);
			
		}
		if (this.getSprite() != null){
			int current_rotation = this.getSprite().rotation ;
			if (current_rotation > Winkel){
				this.getSprite().rotate(360 - current_rotation + Winkel);
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(360 - current_rotation + Winkel);
						s.rotation = Winkel ; 
					}
				}
				
			}
			
			if(current_rotation < Winkel){
				this.getSprite().rotate(Winkel-current_rotation);
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(Winkel-current_rotation);
						s.rotation = Winkel ; 
					}
				}
			}
			
			this.getSprite().rotation = Winkel ; 
			this.m_transform.setRotation(Winkel);
			
			
		}
	}
	/**
	 *  Get the Collider of the object.
	 * @return The Collider.
	 */
	public Collider getCollider(){
		for (Component c : m_Components) {
			if (c != null) {
				if (c instanceof Collider) {
					return (Collider) c;
				}
			}
		}
		return null ; 
	}
	/**
	 * Generic function to get a generic Component not used everywhere
	 * @param type The Type.
	 * @return Type Component.
	 */
	public<T extends Component>  T getComponent (Class<T> type){

		for (Component c : m_Components) {
			if (c != null) {
				if (c.getClass().getTypeName().equals(type.getTypeName()) || c.getClass().getSuperclass().getTypeName().equals(type.getTypeName())) {
					return type.cast(c);
				}
			}
		}	
		return null;
		
	}
	
	/**
	 * 
	 * @return Sprite of Object
	 */
	public Sprite getSprite(){
		for (Component c : m_Components){
			if (c instanceof Sprite){
				return (Sprite)c ; 
			}
		}
		return null ; 
	}
	/**
	 * 
	 * @return Animator of Object
	 */
	public Animator getAnimator(){
		for (Component c : m_Components){
			if (c instanceof Animator){
				return (Animator)c ; 
			}
		}
		return null ; 
	}
	
	public double getWidth(){
		return this.m_transform.getSize().getWidth() ; 
	}
	public double getHeight(){
		return this.m_transform.getSize().getHeight() ; 
	}
	
	
	/**
	 * Super update updates all the components
	 */
	public void Update(){
		for (int i = 0 ; i <  m_Components.size() ; i++) {
			Component c = m_Components.get(i) ;
			c.ComponentUpdate();
		}
	}
	/**
	 * Init the GameObject with a Name ;
	 * @param Name Name of the Object.
	 */
    protected GameObject(String Name){
		Random random = new Random(System.nanoTime()) ;
		m_id = random.nextLong() ;  
		if(m_id < 0){
			m_id = m_id * -1 ; 
		}
		m_name = Name ;
		SceneManager.getInstance().AddGameObjectToScene(this);
		m_transform = new Transform(new Dimension(0,0),new Point2D.Float(0,0)) ; //set default point to 0,0 and size to size of the image
	}
	
	public void setDimension(Dimension d) {
		this.m_transform.setSize(d);
		if (this.getSprite() != null){
			this.getSprite().resize(d);
		}
		if (this.getAnimator() != null){
			for (Sprite s : this.getAnimator().getSpriteSheet()){
				s.resize(d);
			}
		}
		if (this.getCollider() != null){
			this.getCollider().UpdateCollider();
		}
		
	}
	public void MousePressed(MouseEvent e) {
	} 
	public void MouseReleased(MouseEvent e){
		
	}

	public Point2D.Float getPosition(){
		if (this.m_transform != null){
		return this.m_transform.getPosition() ;
		}
		return null ; 
	}

	public void setPosition(Point2D.Float newPosition){
		this.m_transform.setPosition(newPosition);
	}

	public Transform getTransform() {
		return m_transform;
	}

	public void setTransform(Transform m_transform) {
		this.m_transform = m_transform;
	}
	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}
	public long getId(){
		return m_id ; 
	}
	public boolean isActive() {
		return m_isActive;
	}
	public void setActive(boolean isActive) {
		this.m_isActive = isActive;
	}
	
}
