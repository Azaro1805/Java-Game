package dev.Game.states;

import java.awt.Graphics;

import dev.Game.Game;
import dev.Game.Handler;
import dev.Game.Entities.Creatures.Player;
import dev.Game.worlds.World;

public class GameState extends State {

	private Player player;
	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/World/world1.txt");
		handler.setWorld(world);
		player = new Player (handler, 100, 100);
		
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);

	}

}// State
