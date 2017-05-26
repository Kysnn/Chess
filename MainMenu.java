

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame {
	
	Chessboard cb = null;
	MultiplayerMenu mpm;
	public MainMenu()
	{
		startMainMenu();
	}
	public void startMainMenu()
	{
		setTitle("Chess");
		setSize(200, 100 );
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p1 = new JPanel();
		JButton b1 = new JButton("start offline");
		JButton b2 = new JButton("multiplayer");
		JButton b3 = new JButton("exit");
		b1.addActionListener(new ButtonListener(b1));
		b2.addActionListener(new ButtonListener(b2));
		b3.addActionListener(new ButtonListener(b3));
		
		
		p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		b1.setAlignmentX(CENTER_ALIGNMENT);
		b2.setAlignmentX(CENTER_ALIGNMENT);
		b3.setAlignmentX(CENTER_ALIGNMENT);
	
		getContentPane().add(p1);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
	}
	
	public class ButtonListener implements ActionListener
	{
		JButton b;
		public ButtonListener(JButton bSource)
		{
			b = bSource;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println(b.getText());
			 if(b.getText().equals("multiplayer"))
				{
					mpm = new MultiplayerMenu();
					System.out.println(b.getText());
				}
			 else if(b.getText().equals("start offline"))
				 {      Game g=new Game();
					cb = new Chessboard(g);
					System.out.println(b.getText());
				 }
			
			else if(b.getText().equals("exit"))
				System.exit(ABORT);
		}
		
	}

}
