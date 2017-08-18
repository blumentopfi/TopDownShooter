package animationGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;



class State extends JButton {
	
		private int draggedAtX ;
		private int draggedAtY ;
		Map<String,State> nextStates = new HashMap<>() ;
		State(){
			super() ; 
			addMouseListener(new MouseAdapter(){
	            public void mousePressed(MouseEvent e){
	                draggedAtX = e.getX();
	                draggedAtY = e.getY();
	            }
	        });

	        addMouseMotionListener(new MouseMotionAdapter(){
	            public void mouseDragged(MouseEvent e){
	                setLocation(e.getX() - draggedAtX + getLocation().x,
	                        e.getY() - draggedAtY + getLocation().y);
	            }
	        });
		}
		void drawArrows(Graphics g){
			for (State s : nextStates.values()){
				System.out.println("Drawing Arrow");
				g.setColor(Color.BLACK);
				g.drawLine(this.getBounds().x, this.getBounds().y, s.getBounds().x, s.getBounds().y);
			}
		}
		
}
