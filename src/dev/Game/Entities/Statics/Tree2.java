package dev.Game.Entities.Statics;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.gfx.Assets;
import dev.Game.items.Item;
import dev.Game.tiles.Tile;

public class Tree2 extends StaticEntity{

	public Tree2(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2); 
		//Tile.TILEWIDTH, Tile.TILEHEIGHT - is the size of the tree in draw
		
		bounds.x = 10;
		bounds.y = (int) (height /1.5f);
		bounds.height = (int) (height - height /1.5f);
		bounds.width = width - 20;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree2, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.Wood.createNew((int) x-15, (int) y-40));
	}
	
}
