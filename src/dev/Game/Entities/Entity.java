package dev.Game.Entities;

import java.awt.Graphics;

public abstract class Entity {

	//all extends class have access
	protected float x, y;
	protected int width, height;

	public Entity(float x, float y,  int width, int height ) {
		this.x = x;
		this.y = y;
		this.height=height;
		this.width = width;

	}

	public abstract void tick();

	public abstract void render (Graphics g ) ;

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



}//Entity