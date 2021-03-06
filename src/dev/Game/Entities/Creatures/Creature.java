package dev.Game.Entities.Creatures;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.Entities.Entity;
import dev.Game.tiles.Tile;

public abstract class Creature extends Entity {

	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_WIDTH = 35,DEFAULT_HEIGHT=35;
	protected float speed;
	protected float xMove, yMove;


	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}


	public Creature(Handler handler, float x, float y, int width, int height, int health) {
		super(handler, x, y, width, height, health);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
		moveX();
		if(!checkEntityCollisions(0f, yMove))
		moveY();
	}

	public void moveX() {
		if (xMove > 0 ) { //move right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			//first - upper right corner sec- down right corner
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
					!collisionWithTile(tx, (int) (y + bounds.y +bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else { // delete the gap from the tile
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width -1; 
			}
			
		}else if (xMove < 0) { //move left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			//first - upper left corner sec- down left corner
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
					!collisionWithTile(tx, (int) (y + bounds.y +bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else { // delete the gap from the tile
				x = tx * Tile.TILEWIDTH  + Tile.TILEWIDTH - bounds.x; 
			}
		}
	}

	public void moveY() {
		if (yMove < 0 ) { //move up
			int ty = (int) (y + yMove + bounds.y ) / Tile.TILEHEIGHT;

			if (!collisionWithTile( (int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile( (int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else { // delete the gap from the tile
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y ; 
			}
		}else if (yMove >0) { // move down
			int ty = (int) (y + yMove + bounds.y +bounds.height ) / Tile.TILEHEIGHT;

			if (!collisionWithTile( (int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile( (int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else { // delete the gap from the tile
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height +1; 
			}

		}
		
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Getter and Setters
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

} //Creature
