package dev.Game.Entities.Creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.Game.Handler;
import dev.Game.gfx.Animation;
import dev.Game.gfx.Assets;

public class Player extends Creature {

	//Animation
	private Animation animRight, animLeft, animUd, animStand ;



	public Player( Handler handler ,float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2 );

		//bounds.x = 0; //17
		//bounds.y = 35;
		//bounds.width = 35;
		//bounds.height =35;


		//Animation
		animRight = new Animation(100, Assets.player_right);
		animLeft = new Animation(100, Assets.player_left);
		animUd = new Animation(100, Assets.player_ud);
		animStand = new Animation(1000, Assets.player_Stand);
	}

	@Override
	public void tick() {
		
		//Animation
		animRight.tick();
		animLeft.tick();
		animUd.tick();
		animStand.tick();
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().ceterCamrea(this);
		
		//Attack
		checkAttack();
	}

	private void checkAttack() {
		Rectangle cb = getCollisionBounds(0,0);

		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.height = arSize;
		ar.width = arSize;
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
		g.drawImage(getCurrentInamationFrame(), (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);


		//	g.setColor(Color.red);
		//	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		//	bounds.width, bounds.height);
	}

	private BufferedImage getCurrentInamationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if (xMove > 0){
			return animRight.getCurrentFrame();
		}else if (yMove < 0 || yMove > 0 ){
		return animUd.getCurrentFrame();
		}else {
			return animStand.getCurrentFrame();
		}
	}

	@Override
	public void die() {
		System.out.println("You Lose");
	}


}
