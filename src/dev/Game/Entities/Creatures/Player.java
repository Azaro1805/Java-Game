package dev.Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import dev.Game.Handler;
import dev.Game.gfx.Assets;

public class Player extends Creature {


	public Player( Handler handler ,float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2 );
		
		//bounds.x = 0; //17
		//bounds.y = 35;
		//bounds.width = 35;
		//bounds.height =35;
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
		
	//	g.setColor(Color.red);
	//	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			//	bounds.width, bounds.height);
	}


}
