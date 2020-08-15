package dev.Game.gfx;

import dev.Game.Game;
import dev.Game.Handler;
import dev.Game.Entities.Entity;
import dev.Game.tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;

	
	public GameCamera (Handler handler, float xOffset , float YOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlackSpace() {
		if (xOffset < 0 ) {
			xOffset = 0;
		}else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() ) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if (yOffset < 0 ) {
			yOffset = 0;
		}else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() ) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() ;
		}
	}
	
	public void ceterCamrea (Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlackSpace();
	}
	
	public void move (float xAmt , float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlackSpace();
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
