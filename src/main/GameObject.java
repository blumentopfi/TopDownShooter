package main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.SceneManager ;
import javax.imageio.ImageIO;
import geometry.* ;
import Components.* ; 
public abstract class GameObject {
	private String m_name ; 
	private int m_tag ; 
	private BufferedImage m_sprite ; 
	private Transform m_transform ; 
	public void  keyPressed(KeyEvent e){} ;
	public void  keyReleased(KeyEvent e){};
	private List<Component> m_Components = new ArrayList<Component>() ;
	protected void addComponent(Component component){
		m_Components.add(component) ;
	}
	public void Destroy(){
		m_Components.clear();
		SceneManager.getInstance().getGameObjectToDelete().add(this) ;
	}
	public void OnCollision(GameObject collidingObject) {
		
	}
	public Collider getCollider(){
		for (Component c : m_Components){
			if (c instanceof Collider){
				return (Collider)c ; 
			}
		}
		return null ; 
	}
	
	public void Update(){
		for (int i = 0 ; i < m_Components.size() ; i++){
			m_Components.get(i).ComponentUpdate();
		}
	};
	public GameObject(String Name){
		m_name = Name ;
		SceneManager.getInstance().AddGameObjectToScene(this);
		m_transform = new Transform(new Dimension(0,0),new Point2D.Float(0,0)) ; //set default point to 0,0 and size to size of the image
	}
	public GameObject(String PathToSprite,String Name){
		m_name = Name ;
		SceneManager.getInstance().AddGameObjectToScene(this);
		try {
			m_sprite = ImageIO.read(new File(PathToSprite)) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_transform = new Transform(new Dimension(m_sprite.getWidth(),m_sprite.getHeight()),new Point2D.Float(0,0)) ; //set default point to 0,0 and size to size of the image
	}

	public Point2D.Float getPosition(){
		return this.m_transform.getPosition() ;
	}

	public void setPosition(Point2D.Float newPosition){
		this.m_transform.setPosition(newPosition);
	}

	public BufferedImage GetSprite(){
		return m_sprite ;
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
