package dev.Game.Entities.Statics;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.gfx.Assets;
import dev.Game.items.Item;
import dev.Game.tiles.Tile;

public class Cave extends StaticEntity{

	public Cave(Handler handler, float x, float y) {
		super(handler, x, y, 173, 140, 100000); 
		
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
		g.drawImage(Assets.cave, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
	}
	
}
