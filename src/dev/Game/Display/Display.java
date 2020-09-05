package dev.Game.Display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame; 
	private Canvas canvas;
	
	
	
	// JFrame size and title
	private String title;
	private int width , height;
	
	
	//constructor
	public Display (String title, int width, int height ) {
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

		canvas = new Canvas ();
		canvas.setPreferredSize(new Dimension( width, height ));
		canvas.setMaximumSize(new Dimension( width, height ));
		canvas.setMinimumSize(new Dimension( width, height ));
		canvas.setFocusable(false); //make the JFame focus that we can use keyboard
		
		frame.add(canvas);
		frame.pack();
		
	}// createDisplay
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getframe() {
		return frame;
	}
	
}//Display class
	