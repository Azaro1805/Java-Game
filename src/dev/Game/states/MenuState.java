package dev.Game.states;

import java.awt.Graphics;

import dev.Game.Game;
import dev.Game.Handler;

public class MenuState extends State {
	
	public  MenuState (Handler handler) {
		super(handler);
	}
	
	@Override
	public void tick() {
		//System.out.println(handler.getMouseManager().getMouseX());
	}
	
	@Override
	public void render(Graphics g) {
		
	}

	
}
