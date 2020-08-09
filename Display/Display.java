package dev.codenmore.GameFrame.Display;

import javax.swing.JFrame;

public class Display {

	private JFrame frame; 
	
	// JFrame size and title
	private String Title;
	private int Width , Height;
	
	//constructor
	public Display (String title, int width, int height ) {
		this.Title = title;
		this.Width = width;
		this.Height = height;
		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(Title);
		frame.setSize(Width, Height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}// createDisplay
	
}//Display class
	