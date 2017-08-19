package framework.components;

import java.awt.Color;
/**
 * @deprecated
 * @author Fin
 *
 */
public class UIString {
	private final String string ;
	private final int x ;
	private final int y ;
	private final Color color ;
	public UIString(int x , int y , String string, Color color) {
		this.string = string ; 
		this.x = x ; 
		this.y = y ; 
		this.color = color ; 
	}
	

}
