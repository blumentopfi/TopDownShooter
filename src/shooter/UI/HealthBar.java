package shooter.UI ;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class HealthBar extends BasicProgressBarUI {

    @Override
    protected Dimension getPreferredInnerVertical() {
        return new Dimension(20, 146);
    }

    @Override
    protected Dimension getPreferredInnerHorizontal() {
        return new Dimension(146, 20);
    }
    
    private Color LerpColor(Color start, Color end, double percentage){
    	Color x = start ; 
    	Color y = end ; 
    	float blending = (float)percentage; 

    	float inverse_blending = 1 - blending;

    	float red =   x.getRed()   * blending   +   y.getRed()   * inverse_blending;
    	float green = x.getGreen() * blending   +   y.getGreen() * inverse_blending;
    	float blue =  x.getBlue()  * blending   +   y.getBlue()  * inverse_blending;
    	
    	red = framework.math.MyMath.Clamp(255, 0, red);
    	green = framework.math.MyMath.Clamp(255, 0, green);
    	blue = framework.math.MyMath.Clamp(255, 0, blue);
    
    	
    	//note that if i pass float values they have to be in the range of 0.0-1.0 
    	//and not in 0-255 like the ones i get returned by the getters.
    	Color blended = new Color (red / 255, green / 255, blue / 255);
    	return blended ; 
    }
    
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int iStrokWidth = 0;
        //g2d.setStroke(new BasicStroke(iStrokWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.GREEN);
        g2d.setBackground(Color.GREEN);
        int width = progressBar.getWidth();
        int height = progressBar.getHeight();
        int iInnerHeight = height ;
        int iInnerWidth = width ;
        double dProgress = progressBar.getPercentComplete();
        if (dProgress < 0) {
            dProgress = 0;
        } else if (dProgress > 1) {
            dProgress = 1;
        }
        
        

        iInnerWidth = (int) Math.round(iInnerWidth * dProgress);

        Rectangle2D.Double fill = new Rectangle2D.Double(iStrokWidth * 2, iStrokWidth * 2,
                iInnerWidth, iInnerHeight);
        //g2d.setColor(c.getForeground());
        
        if (dProgress > 0.5){
        	float OldRange = (1f - 0.5f) ;  
        	float NewRange = (1 - 0) ;  
        	float NewValue = (float) (((dProgress - 0.5f) * NewRange) / OldRange) ;
        	System.out.println(NewValue);
        	g2d.setColor(LerpColor(Color.green,Color.yellow,NewValue));
        }
        if (dProgress <= 0.5){
        	float OldRange = (0.5f - 0f) ;  
        	float NewRange = (1 - 0) ;  
        	float NewValue = (float) (((dProgress - 0f) * NewRange) / OldRange) ;
        	System.out.println(NewValue);
        	g2d.setColor(LerpColor(Color.yellow,Color.red,NewValue));
        }
        
        
        //g2d.setColor(LerpColor(Color.green,Color.red,dProgress));
        g2d.fill(fill);

        g2d.dispose();
    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        super.paintIndeterminate(g, c); //To change body of generated methods, choose Tools | Templates.
    }

}