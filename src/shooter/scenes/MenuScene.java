package shooter.scenes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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

public class MenuScene extends Scene {
	JButton StartButton ; 
	JButton QuitButton ; 
	JButton OptionButton ; 
	JLabel BackgroundImage ; 
	JLabel Titel ; 	
	@Override
	public void init() {
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		main_Camera.OverrideLayout(null);
		ImageIcon icon = new ImageIcon(new ImageIcon("Assets/Menu/play_buttons.png").getImage()) ;
		StartButton = new JButton(icon) ; 
		int x = main_Camera.getGameWindow().getWidth()/2 - icon.getIconWidth()/2 ;
		int y =  1 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		StartButton.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framework.main.SceneManager.getInstance().SetScene(new ShooterMainScene());
            }
        });
		StartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        StartButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/play_buttons_pressed.png").getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        StartButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/play_buttons.png").getImage()));
		    }
		});
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		main_Camera.AddGUIElement(StartButton);
		 
		ImageIcon img = new ImageIcon(new ImageIcon("Assets/Menu/Title.png").getImage()) ;
		Titel = new JLabel("",img,JLabel.CENTER) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - img.getIconHeight()/2 ;
		 y =  0 ;
		Titel.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		main_Camera.AddGUIElement(Titel);
		
		ImageIcon icon2 = new ImageIcon(new ImageIcon("Assets/Menu/exit_buttons.png").getImage()) ;
		QuitButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - icon2.getIconWidth()/2 ;
		y =  5 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		QuitButton.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
		QuitButton.setOpaque(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setBorderPainted(false);
		QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		QuitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        QuitButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/exit_buttons_pressed.png").getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        QuitButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/exit_buttons.png").getImage()));
		    }
		});
		main_Camera.AddGUIElement(QuitButton);
		icon2 = new ImageIcon(new ImageIcon("Assets/Menu/optionst_buttons.png").getImage()) ;
		OptionButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - icon2.getIconWidth()/2 ;
		y =  3 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		OptionButton.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());
		OptionButton.setOpaque(false);
		OptionButton.setContentAreaFilled(false);
		OptionButton.setBorderPainted(false);
		OptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		OptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        OptionButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/optionst_buttons_pressed.png").getImage()));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        OptionButton.setIcon(new ImageIcon(new ImageIcon("Assets/Menu/optionst_buttons.png").getImage()));
		    }
		});
		main_Camera.AddGUIElement(OptionButton);
		
		ImageIcon image = new ImageIcon(new ImageIcon("Assets/Splashscreens/background.jpg").getImage().getScaledInstance( main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight()
				, Image.SCALE_DEFAULT)) ;
		BackgroundImage = new JLabel("",image,JLabel.CENTER) ; 
		BackgroundImage.setBounds(0, 0, main_Camera.getGameView().getWidth(), main_Camera.getGameView().getHeight());
		main_Camera.AddGUIElement(BackgroundImage);
		

		
		
		
	}
	
	public void finishScene(){
	main_Camera.RemoveGUIElement(StartButton);
	main_Camera.RemoveGUIElement(QuitButton);
	main_Camera.RemoveGUIElement(OptionButton);
	main_Camera.RemoveGUIElement(BackgroundImage);
	main_Camera.RemoveGUIElement(Titel); 
	}
	
}
