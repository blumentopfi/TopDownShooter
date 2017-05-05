package Scenes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import input.InputManager;
import main.Scene;
import main.SceneManager;
import objects.GameManager;
import objects.HealthPowerUp;
import objects.Player;
import rendering.Camera;

public class MenuScene extends Scene {
	JButton StartButton ; 
	JButton QuitButton ; 
	JLabel BackgroundImage ; 
	JLabel Titel ; 
	@Override
	public void gameObjectsOnStart() {
		main_Camera = new Camera(1000,1000,new Rectangle(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		main_Camera.OverrideLayout(null);
		
		ImageIcon icon = new ImageIcon(new ImageIcon("Assets/Menu/Button.png").getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT)) ;
		StartButton = new JButton(icon) ; 
		int x = main_Camera.getGameWindow().getWidth()/2 - 300 ;
		int y =  3 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		StartButton.setBounds(x, y, 600, 100);
		StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.SceneManager.getInstance().SetScene(new ShooterMainScene());
            }
        });
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		main_Camera.AddGUIElement(StartButton);
		x = main_Camera.getGameWindow().getWidth()/2 - 900/2 ;
		 y =  0 ; 
		ImageIcon img = new ImageIcon(new ImageIcon("Assets/Menu/Title.png").getImage().getScaledInstance(900, 200, Image.SCALE_DEFAULT)) ;
		Titel = new JLabel("",img,JLabel.CENTER) ; 
		Titel.setBounds(x, y, 900, 200);
		main_Camera.AddGUIElement(Titel);
		
		
		ImageIcon image = new ImageIcon(new ImageIcon("Assets/Splashscreens/MainMenuSplashScreen.jpg").getImage().getScaledInstance( main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight()
				, Image.SCALE_DEFAULT)) ;
		BackgroundImage = new JLabel("",image,JLabel.CENTER) ; 
		BackgroundImage.setBounds(0, 0, main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight());
		main_Camera.AddGUIElement(BackgroundImage);
		
		
		
	}
	
	public void finishScene(){
	main_Camera.RemoveGUIElement(StartButton);
	//main_Camera.RemoveGUIElement(QuitButton);
	main_Camera.RemoveGUIElement(BackgroundImage);
	}
	
}
