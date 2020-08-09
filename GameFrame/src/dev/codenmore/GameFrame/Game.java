package dev.codenmore.GameFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.codenmore.GameFrame.Display.Display;
import dev.codenmore.GameFrame.gfx.ImageLoader;

public class Game implements Runnable {

	private boolean running = false;
	private Display display;
	public int  width , height;
	public String title;
	//private Canvas canvas;
	private Thread thread; 
	private BufferStrategy bs;
	private Graphics g;
	
	

	// constructor
	public Game(String title, int width, int height) {
		this.height = height;
		this.width = width;
		this.title= title;

	}

	private void tick() {

	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear Screen 
		g.clearRect(0, 0, width, height); // 0-0 all of the screen 
		//Draw Here!
		
		
		//End Drawing!
		bs.show();
		g.dispose();
		
	}//render

	public void run() {
		init();

		while (running ) {
			tick();
			render();
		}//while 
	}//run

	private void init () {
		display = new Display(title, width, height );
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread =  new Thread (this); // this = game class
		thread.start();

	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//stop



}//Game
