package dev.GameFrame.states;

import java.awt.Graphics;

import dev.GameFrame.gfx.Assets;

public class GameState extends State {


	public GameState() {

	}
	
	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(Assets.dirt , 0, 0, null);

	}

}// State
