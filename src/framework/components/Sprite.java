package framework.components;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import framework.main.GameObject;
/**
 * Class for our Sprite Component which holds the Image Infos
 * @author Fin
 *
 */
public class Sprite extends Component {
	BufferedImage my_sprite_image  ; //image of the sprite ; 
	GameObject my_object ;//GameObject the sprite is assigned to  ; 
	/**
	 * Constructor
	 * @param PathToSprite
	 * @param my
	 */
	public Sprite (String PathToSprite,GameObject my){
		my_object = my ; 
		try {
			//We convert the Sprite to the best Format for the graphics card
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = env.getDefaultScreenDevice() ;
			GraphicsConfiguration config = device.getDefaultConfiguration() ; 	 //convert Image to a memory type that given Graphic cards supports
			my_sprite_image = ImageIO.read(new File(PathToSprite)) ;
			BufferedImage tmp = config.createCompatibleImage(my_sprite_image.getWidth(), my_sprite_image.getHeight(),Transparency.TRANSLUCENT);
			tmp.getGraphics().drawImage(my_sprite_image, 0,0,null) ; 
			my_sprite_image = tmp ; 

		} catch (IOException e) {
			// TODO Auto-generated catch block,
			e.printStackTrace();
		}
		//Setting the size of the sprite to the size of gameObject
		my_object.setDimension(new Dimension(this.getWidth(),this.getHeight()));
	}
	
	/**
	 * Function to rotate Images
	 * @param src
	 * @param degrees
	 * @return rotatedImage
	 */
	private static BufferedImage rotateImage(BufferedImage src, double degrees) {
        AffineTransform affineTransform = AffineTransform.getRotateInstance(
                Math.toRadians(degrees),
                src.getWidth() / 2,
                src.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(src.getWidth(), src
                .getHeight(), src.getType());
        Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
        g.setTransform(affineTransform);
        g.drawImage(src, 0, 0, null);
        return rotatedImage;
    }
	/**
	 * API Interface to rotate a sprite
	 * @param winkel
	 */
	public void rotate(int winkel){
	    
	    my_sprite_image = rotateImage(my_sprite_image,winkel) ; 
	}
	/**
	 * @return height
	 */
	public int getHeight(){
		return my_sprite_image.getHeight() ; 
	}
	/**
	 * @return width
	 */
	public int getWidth(){
		return my_sprite_image.getWidth() ; 
	}
	/**
	 * @return Image
	 */
	public BufferedImage getImage(){
		return my_sprite_image ; 
	}
	/**
	 * If the size of the gameObject changes we have to change our sprite size
	 * @param d
	 */
	public void resize(Dimension d){
	my_sprite_image = getScaledImage(my_sprite_image,(int)d.getWidth(),(int)d.getHeight());
	}
	/**
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 * @param srcImg - source image to scale
	 * @param w - desired width
	 * @param h - desired height
	 * @return - the new resized image
	 */
	private BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	@Override
	public void ComponentUpdate() {

	}

}
