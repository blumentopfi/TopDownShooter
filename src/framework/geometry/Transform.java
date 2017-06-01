package framework.geometry;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Class which holds all the informations regarding position, rotation and dimension
 * @author Fin
 *
 */
public class Transform {
	private Point2D.Float m_position ; 
	private Dimension m_size ;
	private int rotation = 0 ; 
	/**
	 * @param size
	 * @param position
	 */
	public Transform(Dimension size , Point2D.Float position){
		m_size = size ; 
		m_position = position ; 
	}
	public Point2D.Float getPosition() {
		return m_position;
	}
	public void setPosition(Point2D.Float position) {
		this.m_position = position;
	}
	public Dimension getSize() {
		return m_size;
	}
	public void setSize(Dimension size) {
		this.m_size = size;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	} 
	
}
