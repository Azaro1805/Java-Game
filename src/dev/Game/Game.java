package dev.Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.Game.Display.Display;
import dev.Game.gfx.Assets;
import dev.Game.gfx.GameCamera;
import dev.Game.input.KeyManager;
import dev.Game.states.GameState;
import dev.Game.states.State;

public class Game implements Runnable {

	//Display
	private Display display;
	private int  width , height;
	public String title;
	
	//Run of the Game
	private boolean running = false;
	private Thread thread; 

	//Graphic
	private BufferStrategy bs;
	private Graphics g;

	//States
	private State gameState;
	//private State menuState;

	//input
	private KeyManager keyManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler 
	private Handler handler;

	public Game(String title, int width, int height) {
		this.height = height;
		this.width = width;
		this.title= title;
		keyManager = new KeyManager();
	}

	private void init () {
		display = new Display(title, width, height );
		display.getframe().addKeyListener(keyManager);
		Assets.init();
		
		gameCamera = new GameCamera(this, 0, 0); //game camera start in 0,0 
		handler = new Handler(this);
		
		gameState = new GameState(handler); 
		State.setState(gameState);
		//menuState = new MenuState(this); 
		//State.setState(menuState);
	}

	private void tick() {
		keyManager.tick();
		
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

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}//Game

//notes :
//g.drawImage(test ,0,0,null);
//g.drawImage(sheet.crop(40, 0, 120, 128) , 5, 5, null);
//g.drawImage(Assets.grass,x,10,null);
//g.drawImage(Assets.stump,50,50,null);
//g.drawImage(Assets.dirt,90,90,null);
//g.drawImage(Assets.player,130,130,null);
