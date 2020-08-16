package dev.Game.states;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.Entities.Creatures.Player;
import dev.Game.Entities.Statics.Tree;
import dev.Game.worlds.World;

public class GameState extends State {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/World/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);

	}

}// State
