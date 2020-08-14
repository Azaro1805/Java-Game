package dev.Game.Entities.Creatures;

import java.awt.Graphics;
import dev.Game.Handler;
import dev.Game.gfx.Assets;

public class Player extends Creature {


	public Player( Handler handler ,float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2 );
	}
	
	@Override
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().ceterCamrea(this);
	}

	private void getInput() {
		xMove = 0 ;
		yMove = 0 ;
		
		if (handler.getKeyManager().up || handler.getKeyManager().upw )
			yMove = -speed;
		if (handler.getKeyManager().down || handler.getKeyManager().downs)
			yMove = speed;
		if (handler.getKeyManager().right || handler.getKeyManager().rightd)
			xMove = speed;
		if (handler.getKeyManager().left || handler.getKeyManager().lefta)
			xMove = -speed;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}


}
