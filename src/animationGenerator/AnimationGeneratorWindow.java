package animationGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class AnimationGeneratorWindow extends JFrame {
	private final GraphPanel GraphPanel = new GraphPanel() ;
	private final JButton NewStateButton = new JButton("New State") ;
	private final List<State> m_states = new ArrayList<>() ;
	private State justClickedButton ;
	AnimationGeneratorWindow(){
		
		super("Animation Generator") ;
		this.setVisible(true);
		this.setBounds(0, 0, 1000, 1000); 
		this.setLayout(new BorderLayout());
		JPanel controlPanel = new JPanel();
		this.add(controlPanel,BorderLayout.NORTH) ;
		this.add(GraphPanel, BorderLayout.CENTER);
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(NewStateButton) ;
		GraphPanel.setLayout(null);
		initializeActions() ;
	}
	
	private void initializeActions(){
		NewStateButton.addActionListener(e -> this.SpawnNewState());
	}
	private void SpawnNewState(){
		State a = new State() ; 
		m_states.add(a) ; 
		GraphPanel.add(a);
		a.setBounds(0, 0, 30, 50);
		a.addActionListener(e -> this.MakeConnection(a));
	}
	private void MakeConnection(State ToConnect){
		if (justClickedButton == null){
			justClickedButton = ToConnect ;
			justClickedButton.setBackground(Color.BLUE);
			return ; 
		}
		if (justClickedButton == ToConnect){
			justClickedButton.setBackground(new JButton().getBackground());
			justClickedButton = null ; 
			return ; 
		}
		justClickedButton.nextStates.put("a<10", ToConnect) ; 
		justClickedButton.setBackground(new JButton().getBackground());
		GraphPanel.repaint();
		justClickedButton = null ; 
	}
	
	public class GraphPanel extends JPanel {
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        for (State s : m_states){
	        	s.drawArrows(this.getGraphics());
	        }
	    }
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
