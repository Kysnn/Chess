
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.charset.CharsetEncoder;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MultiplayerMenu extends JFrame {
	
	JLabel ipLabel,targetIpLabel;
	JPanel p1 = new JPanel();
	JRadioButton rb1 ,rb2;
	JButton startGameButton;
	JTextField targetIpField;
	JButton joinGameButton;
	String[] sides = {"W","B"};
	private static final long serialVersionUID = 1L;
	public MultiplayerMenu()
	{
		startMenu();
	}
	private void startMenu(){
		rb1 = new JRadioButton("Create Game");
		rb1.addActionListener(new ButtonListener(rb1));
		rb2 = new JRadioButton("Join Game");
		rb2.addActionListener(new ButtonListener(rb2));
		startGameButton = new JButton("Create ->");
		startGameButton.addActionListener(new ButtonListener(startGameButton));
		startGameButton.setEnabled(false);
		joinGameButton = new JButton("Join ->");
		joinGameButton.setEnabled(false);
		joinGameButton.addActionListener(new ButtonListener(joinGameButton));
		targetIpField = new JTextField();
		targetIpField.setEnabled(false);
		targetIpLabel = new JLabel("Server Ip :");
		try {
			ipLabel = new JLabel("Your IP address : " + Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.err.println("ERROR AT DETECTING IP ADDRESS");
		}
		ButtonGroup bg1 = new ButtonGroup();
		p1.setLayout(null);
		p1.add(ipLabel);
		bg1.add(rb1);
		bg1.add(rb2);
		p1.add(rb1);
		p1.add(rb2);
		p1.add(startGameButton);
		p1.add(targetIpField);
		p1.add(joinGameButton);
		p1.add(targetIpLabel);
		
		ipLabel.setLocation(5,10);
		ipLabel.setSize(200,20);
		rb1.setLocation(5,100);
		rb1.setSize(100,20);
		rb2.setLocation(5,140);
		rb2.setSize(100,20);
		startGameButton.setLocation(150,100);
		startGameButton.setSize(88,25);
		targetIpLabel.setLocation(5,170);
		targetIpLabel.setSize(100,22);
		targetIpField.setLocation(5,190);
		targetIpField.setSize(100,22);
		joinGameButton.setLocation(150,185);
		joinGameButton.setSize(88,25);
		
		setTitle("Server Settings");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(p1,BorderLayout.CENTER);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		
	}
	public class ButtonListener implements ActionListener
	{
		JRadioButton radioButton;
		JButton button;
		
		public ButtonListener(JRadioButton bSource)
		{
			radioButton = bSource;
		}
		public ButtonListener(JButton bSource)
		{
			button = bSource;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(radioButton != null && radioButton.getText().equals("Create Game")) //Checking radio button 1
				{
				startGameButton.setEnabled(true);
				targetIpField.setEnabled(false);
				joinGameButton.setEnabled(false);
				targetIpField.setText("");
				
				}
				
			if(radioButton != null && radioButton.getText().equals("Join Game")) //Checking radio button 2
			{
				startGameButton.setEnabled(false);
				targetIpField.setEnabled(true);
				joinGameButton.setEnabled(true);
				
			}
			if(button != null && button.getText().equals("Create ->") )
			{
				Game game = new Game();
				game.startGame();
			}
			if(button != null && button.getText().equals("Join ->"))
			{
				
				Game game = new Game(targetIpField.getText());
				game.startGame();
			}
		}
		}
	}
	


