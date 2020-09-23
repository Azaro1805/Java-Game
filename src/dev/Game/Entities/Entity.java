package dev.Game.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Game.Handler;

public abstract class Entity {

	//all extends class have access
	public static final int DEFAULT_HEALTH = 10;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds ;
	protected int health;
	protected boolean active = true, player = false , enemy = false;

	// Attack Timers
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	public Entity(Handler handler, float x, float y,  int width, int height ) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height=height;
		this.width = width;
		health = DEFAULT_HEALTH;
		bounds =  new Rectangle(0, 0, width, height);

	}

	public Entity(Handler handler, float x, float y,  int width, int height, int health) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height=height;
		this.width = width;
		this.health = health;
		bounds =  new Rectangle(0, 0, width, height);

	}


	public abstract void tick();

	public abstract void render (Graphics g ) ;

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e  : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle ((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public abstract void die();

// אולי להוסיף לפה את האנימציה ? עם גרפיק הצלחתי לעצור אבל זה עוצר הכל ..
	
	public void hurt(int amt) {
		health -= amt;
		if(health <= 0 ) {
			die();

			/*boolean die = true;
			attackTimer = System.currentTimeMillis();
			while (die) {
				if(attackTimer+10000 < System.currentTimeMillis())
					die = false;
			}
			*/
			active = false;
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isPlayer() {
		return player;
	}
	
	public boolean isEnemy() {
		return enemy;
	}

	public void setIfPlayer(boolean player) {
		this.player = player;
	}

	public void setIfEnemy(boolean enemy) {
		this.enemy = enemy;
	}
	
}//Entity
