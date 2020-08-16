package dev.Game.Entities.Statics;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.Entities.Entity;

public abstract class StaticEntity extends Entity{

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}


}//StaticEntity
