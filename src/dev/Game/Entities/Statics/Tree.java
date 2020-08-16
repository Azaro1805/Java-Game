package dev.Game.Entities.Statics;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.gfx.Assets;
import dev.Game.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2); 
		//Tile.TILEWIDTH, Tile.TILEHEIGHT - is the size of the tree in draw
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
}
