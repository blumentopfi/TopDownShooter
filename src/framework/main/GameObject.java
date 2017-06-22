package framework.main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import framework.components.*;
import framework.geometry.*;
import framework.main.SceneManager; 
/**
 * Class for any objects that is in the game
 * @author Fin
 *
 */
public abstract class GameObject {
	private String m_name ; 
	private int m_tag ;  
	private Transform m_transform ; 
	private List<Component> m_Components = new ArrayList<Component>() ; // List of Components that are attached
	/**
	 * Add a component
	 * @param component
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
	 * @param collidingObject
	 */
	public void OnCollision(GameObject collidingObject) {
	}
	/**
	 * Set the rotation of the gameObject
	 * @param Winkel
	 */
	public void Rotate(int Winkel){
		Winkel = Winkel % 360 ; 
		if (this.getSprite() != null){
			int current_rotation = this.m_transform.getRotation() ;
			if (current_rotation > Winkel){
				this.getSprite().rotate(360 - current_rotation + Winkel);
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(360 - current_rotation + Winkel);
					}
				}
				
			}
			
			if(current_rotation < Winkel){
				this.getSprite().rotate(Winkel-current_rotation);
				if (this.getAnimator()!= null){
					for (Sprite s : this.getAnimator().getSpriteSheet()){
						s.rotate(Winkel-current_rotation);
					}
				}
			}
			
			
			this.m_transform.setRotation(Winkel);
			
			
		}
	}
	public Collider getCollider(){
		for (Component c : m_Components){
			if (c instanceof Collider){
				return (Collider)c ; 
			}
		}
		return null ; 
	}
	public Sprite getSprite(){
		for (Component c : m_Components){
			if (c instanceof Sprite){
				return (Sprite)c ; 
			}
		}
		return null ; 
	}
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
		for (int i = 0 ; i < m_Components.size() ; i++){
			m_Components.get(i).ComponentUpdate();
		}
	};
	/**
	 * 
	 * @param Name
	 */
	public GameObject(String Name){
		m_name = Name ;
		SceneManager.getInstance().AddGameObjectToScene(this);
		m_transform = new Transform(new Dimension(0,0),new Point2D.Float(0,0)) ; //set default point to 0,0 and size to size of the image
	}
	
	public void setDimension(Dimension d) {
		this.m_transform.setSize(d);
		if (this.getSprite() != null){
			this.getSprite().resize(d);
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

	public int getTag() {
		return m_tag;
	}

	public void setTag(int tag) {
		m_tag = tag;
	}
	
}
