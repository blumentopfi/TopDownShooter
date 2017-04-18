package geometry;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Transform {
	private Point2D.Float m_position ; 
	private Dimension m_size ;
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
	
}
