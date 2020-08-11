package dev.Game.states;

import java.awt.Graphics;

import dev.Game.Game;

public abstract class State {

	protected Game game;

	private static State currentState = null;

	
	public State(Game game) {
		this.game = game;
	}


	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}



	public abstract void tick() ;
	public abstract void render(Graphics g);

}
