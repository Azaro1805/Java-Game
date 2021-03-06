package dev.GameFrame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import dev.GameFrame.Display.Display;
import dev.GameFrame.gfx.Assets;
import dev.GameFrame.states.GameState;
import dev.GameFrame.states.State;

public class Game implements Runnable {

	private boolean running = false;
	private Display display;
	public int  width , height;
	public String title;
	private Thread thread; 
	private BufferStrategy bs;
	private Graphics g;

	//States
	private State gameState;



	// constructor
	public Game(String title, int width, int height) {
		this.height = height;
		this.width = width;
		this.title= title;

	}

	private void init () {
		display = new Display(title, width, height );
		Assets.init();

		gameState = new GameState(); 
		State.setState(gameState);
	}

	private void tick() {
		if (State.getState() != null) {
			State.getState().tick();
		}
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


		if (State.getState() != null) {
			State.getState().render(g);
		}


		//End Drawing!

		bs.show();
		g.dispose();

	}//render

	public void run() {
		init();
		int fps = 60;
		double  timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;


		while (running ) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000 ) {
				System.out.println("Ticks and Frames / fps :" + ticks);
				ticks=0;
				timer=0;
			}

		}//while 

		stop();

	}//run

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

//notes :
//g.drawImage(test ,0,0,null);
//g.drawImage(sheet.crop(40, 0, 120, 128) , 5, 5, null);
//g.drawImage(Assets.grass,x,10,null);
//g.drawImage(Assets.stump,50,50,null);
//g.drawImage(Assets.dirt,90,90,null);
//g.drawImage(Assets.player,130,130,null);
