package dev.Game.Entities.Creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.Game.Handler;
import dev.Game.gfx.Animation;
import dev.Game.gfx.Assets;

public class PlayerBarControl extends Creature{
	

	private int aRight, aLeft, aUp, aDown, xp, xpToLevelUp, clickL, hp;
	private BufferedImage bufferedImage;
	private Animation animHpUp, animHpDown, animXpDown, animXpUp; 
	protected Player player;

	public PlayerBarControl(Handler handler, float x, float y, int width, int height, Player player) {
		super( handler, x, y, width, height);
		this.player = player;
		xp = player.getXp();
		xpToLevelUp = player.getXpToLevelUp();
		hp = player.getHealth();
		
		animHpUp = new Animation(100, Assets.bars);
		animHpDown = new Animation(100, Assets.bars);
		animXpDown = new Animation(100, Assets.bars);
		animXpUp = new Animation(100, Assets.bars);
	}

	@Override
	public void tick() {
		animHpUp.tick();
		animHpDown.tick();
		animXpDown.tick();
		animXpUp.tick();
		
		getInput();
		move();
		handler.getGameCamera().ceterCamrea(this);
	}

	private void getInput() {
		xMove = 0 ;
		yMove = 0 ;
		aRight = 0;
		aLeft = 0;
		aUp = 0;
		aDown = 0;
		clickL = 0;


		if (handler.getKeyManager().up) //|| handler.getKeyManager().upw )
			yMove = -speed;
		if (handler.getKeyManager().down) //|| handler.getKeyManager().downs)
			yMove = speed;
		if (handler.getKeyManager().right) // || handler.getKeyManager().rightd)
			xMove = speed;
		if (handler.getKeyManager().left) //|| handler.getKeyManager().lefta)
			xMove = -speed;
		if(handler.getKeyManager().aRight) 
			aRight ++;
		if(handler.getKeyManager().aLeft) 
			aLeft++;
		if(handler.getKeyManager().aUp) 
			aUp++;
		if(handler.getKeyManager().aDown) 
			aDown++;
		if (handler.getKeyManager().clickL) 
			clickL++;

	}

	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentInamationFrame(), (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}

	private BufferedImage getCurrentInamationFrame() {
		return animHpUp.getCurrentFrame();
	}


	
	@Override
	public void die() {
	}

}
