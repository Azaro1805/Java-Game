package dev.Game.states;

import java.awt.Graphics;

import dev.Game.Game;
import dev.Game.Entities.Creatures.Player;

public class GameState extends State {

	private Player player;

	public GameState(Game game) {
		super(game);
		player = new Player (game, 100, 100);
	}
	
	@Override
	public void tick() {
		player.tick();
	}
	
	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}// State
