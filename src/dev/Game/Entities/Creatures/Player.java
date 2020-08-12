package dev.Game.Entities.Creatures;

import java.awt.Graphics;

import dev.Game.Game;
import dev.Game.gfx.Assets;

public class Player extends Creature {

	private Game game;

	public Player( Game game ,float x, float y) {
		super(x, y , Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2 );
		this.game = game;
	}
	
	@Override
	public void tick() {
		getInput();
		move();
	}

	private void getInput() {
		xMove = 0 ;
		yMove = 0 ;
		
		if (game.getKeyManager().up || game.getKeyManager().upw )
			yMove = -speed;
		if (game.getKeyManager().down || game.getKeyManager().downs)
			yMove = speed;
		if (game.getKeyManager().right || game.getKeyManager().rightd)
			xMove = speed;
		if (game.getKeyManager().left || game.getKeyManager().lefta)
			xMove = -speed;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
	}


}
