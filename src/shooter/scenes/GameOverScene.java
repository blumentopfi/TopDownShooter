package shooter.scenes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import framework.input.InputManager;
import framework.main.Scene;
import framework.main.SceneManager;
import framework.rendering.Camera;
import shooter.objects.GameManager;
import shooter.objects.HealthPowerUp;
import shooter.objects.Player;

public class GameOverScene extends Scene {
	JButton StartButton ; 
	JButton QuitButton ; 
	JLabel BackgroundImage ; 
	JLabel Titel ; 	
	@Override
	public void gameObjectsOnStart() {
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		//main_Camera.getGameWindow().repaint(); 
		main_Camera.OverrideLayout(null);
		
		
		ImageIcon icon = new ImageIcon(new ImageIcon("Assets/Menu/RestartButton.png").getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT)) ;
		StartButton = new JButton(icon) ; 
		int x = main_Camera.getGameWindow().getWidth()/2 - 300 ;
		int y =  3 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		StartButton.setBounds(x, y, 600, 100);
		StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framework.main.SceneManager.getInstance().SetScene(new ShooterMainScene());
            }
        });
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		main_Camera.AddGUIElement(StartButton);
		
		
		ImageIcon icon2 = new ImageIcon(new ImageIcon("Assets/Menu/QuitButton.png").getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT)) ;
		QuitButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - 300 ;
		y =  5 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		QuitButton.setBounds(x, y, 600, 100);
		QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		QuitButton.setOpaque(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setBorderPainted(false);
		//main_Camera.AddGUIElement(QuitButton);
		ImageIcon image = new ImageIcon(new ImageIcon("Assets/Splashscreens/Game_Over_Screen.jpg").getImage().getScaledInstance( main_Camera.getGameWindow().getWidth(), main_Camera.getGameWindow().getHeight()
				, Image.SCALE_DEFAULT)) ;
		BackgroundImage = new JLabel("",image,JLabel.CENTER) ; 
		BackgroundImage.setBounds(0, 0, main_Camera.getGameWindow().getWidth(), main_Camera.getGameWindow().getHeight());
		main_Camera.AddGUIElement(BackgroundImage);
		
	}
	
	public void finishScene(){
	main_Camera.RemoveGUIElement(BackgroundImage);
	main_Camera.RemoveGUIElement(StartButton);
	main_Camera.RemoveGUIElement(QuitButton);
	}
	
}
