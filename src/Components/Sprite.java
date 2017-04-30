package Components;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GameObject;

public class Sprite extends Component {
	BufferedImage my_sprite_image ; 
	GameObject my_object ; 
	public Sprite (String PathToSprite,GameObject my){
		my_object = my ; 
		try {
			my_sprite_image = ImageIO.read(new File(PathToSprite)) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block,
			e.printStackTrace();
		}
		my_object.setDimension(new Dimension(this.getWidth(),this.getHeight()));
	}
	public int getHeight(){
		return my_sprite_image.getHeight() ; 
	}
	public int getWidth(){
		return my_sprite_image.getWidth() ; 
	}
	public BufferedImage getImage(){
		return my_sprite_image ; 
	}
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
		// TODO Auto-generated method stub

	}

}
