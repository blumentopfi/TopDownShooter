package shooter.scenes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
	JLabel BackgroundImageGO ;
	JLabel BackgroundImage ;
	JLabel Titel ;
	JLabel Final_Score;
	@Override
	public void init() {
		main_Camera = new Camera(1000,1000,new Rectangle2D.Float(0,0,10,10),SceneManager.getInstance().getGameWindow()) ;
		SceneManager.getInstance().setMainCamera(main_Camera);
		main_Camera.OverrideLayout(null);

		ImageIcon icon = new ImageIcon(new ImageIcon("Assets/Menu/RestartButton.png").getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT)) ;
		StartButton = new JButton(icon) ; 
		int x = main_Camera.getGameWindow().getWidth()/2 - 300 ;
		int y =  3 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		StartButton.setBounds(x, y, 600, 100);
		StartButton.addActionListener(e -> SceneManager.getInstance().SetScene(new ShooterMainScene()));
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		main_Camera.AddGUIElement(StartButton);
		
		
		ImageIcon icon2 = new ImageIcon(new ImageIcon("Assets/Menu/QuitButton.png").getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT)) ;
		QuitButton = new JButton(icon2) ; 
		x = main_Camera.getGameWindow().getWidth()/2 - 300 ;
		y =  5 * main_Camera.getGameWindow().getHeight()/8 - 100  ; 
		QuitButton.setBounds(x, y, 600, 100);
		QuitButton.addActionListener(e -> System.exit(0));
		QuitButton.setOpaque(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setBorderPainted(false);
		main_Camera.AddGUIElement(QuitButton);
		showHighScore();
		ImageIcon imageGO = new ImageIcon(new ImageIcon("Assets/Splashscreens/Game_Over_Screen.jpg").getImage());
		BackgroundImageGO = new JLabel("",imageGO,JLabel.CENTER) ;
		BackgroundImageGO.setBounds(0, 0, main_Camera.getGameWindow().getWidth(), 100);
		main_Camera.AddGUIElement(BackgroundImageGO);

		ImageIcon image = new ImageIcon(new ImageIcon("Assets/Splashscreens/background_black.png").getImage().getScaledInstance( main_Camera.getGameWindow().getWidth(), main_Camera.getGameWindow().getHeight()
				, Image.SCALE_DEFAULT)) ;
		BackgroundImage = new JLabel("",image,JLabel.CENTER) ;
		BackgroundImage.setBounds(0, 0, main_Camera.getGameWindow().getWidth(), main_Camera.getGameWindow().getHeight());
		main_Camera.AddGUIElement(BackgroundImage);

	}

	public void showHighScore() {
		List<String> highscores = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get("highscore.txt"))) {
			stream.forEach(highscores::add);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Long> highscores_Long = new ArrayList<Long>();
		for (String s : highscores) {
			highscores_Long.add(Long.parseLong(s));
		}

		System.out.println("NS: " + highscores_Long);
		Collections.sort(highscores_Long);
		Collections.reverse(highscores_Long);
		System.out.println("S: " + highscores_Long);

		JLabel headline = new JLabel("Highscores", JLabel.CENTER);
		headline.setForeground(Color.WHITE);
		headline.setFont (headline.getFont ().deriveFont (64.0f));
		headline.setBounds((main_Camera.getGameWindow().getWidth()/2 - 200),100,400,100);
		headline.setVisible(true);
		main_Camera.AddGUIElement(headline);

		for (int i = 0; (i < highscores_Long.size()) && (i < 5); i++) {
			String s = String.valueOf(highscores_Long.get(i));
			JLabel temp = new JLabel(s, JLabel.CENTER) ;
			temp.setText(s);
			temp.setForeground(Color.WHITE);
			temp.setFont (temp.getFont ().deriveFont (40.0f));
			temp.setBounds((main_Camera.getGameWindow().getWidth()/2 - 100),(200 + (i * 50)),200,100);
			System.out.println("Comp" + temp);
			temp.setVisible(true);
			main_Camera.AddGUIElement(temp);
		}

	}
	
	public void finishScene(){
	main_Camera.RemoveGUIElement(BackgroundImage);
	main_Camera.RemoveGUIElement(StartButton);
	main_Camera.RemoveGUIElement(QuitButton);
	}
	
}
