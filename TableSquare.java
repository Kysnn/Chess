

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class TableSquare extends JButton {
	
	private static final long serialVersionUID = 1L;
	Piece onThis;
	int x,y;
	
	
	public TableSquare() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
	public TableSquare(int X,int Y)
	{
		super();
		x = X;
		y = Y;
		
	}
   
	public TableSquare(Action arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TableSquare(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TableSquare(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TableSquare(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public void Update()
	{
		
	}


	
	

}
