package dev.Game.Entities.Creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.Game.Handler;
import dev.Game.Entities.Entity;
import dev.Game.gfx.Animation;
import dev.Game.gfx.Assets;
import dev.Game.inventory.Inventory;

public class Enemy extends Creature {

	//Animation
	private Animation animRight, animLeft, animUd, animStand, animAttackRight, animStandLeft, animAttackLeft,
						animDie, animHurt;
	private boolean switchSide = false;
	private int aRight, aLeft, aUp, aDown;
	
	// Attack Timers
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	// attackCooldown  - cooldown between attacks in ms;
	private int attackPower = 10;

	public Enemy( Handler handler ,float x, float y) {
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
		animStandLeft = new Animation(1000, Assets.player_StandL);
		animAttackRight = new Animation(100, Assets.player_attackRight);
		animAttackLeft = new Animation(100, Assets.player_attackLeft);
		animDie = new Animation(350, Assets.player_Die); // need to graphics improved
		animHurt = new Animation(100, Assets.player_Hurt);
		
	}

	@Override
	public void tick() {

		//Animation
		animRight.tick();
		animLeft.tick();
		animUd.tick();
		animStand.tick();
		animStandLeft.tick();
		animAttackRight.tick();
		animAttackLeft.tick();
		animDie.tick();
		animHurt.tick();
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().ceterCamrea(this);

		//Attack
		checkAttack();
	}

	private void checkAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown) // return if the player try to attack to fast (nothing happend)
			return;

		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.height = arSize;
		ar.width = arSize;

		if(handler.getKeyManager().aUp ) { // || handler.getKeyManager().c for all
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown) { 
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x +  cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else {
			return;
		}

		attackTimer = 0;

		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) 
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {// attackPower - how much damage the player deal
				e.hurt(attackPower); 
				return;				
			}
		}

	}

	private void getInput() {
		
		xMove = 0 ;
		yMove = 0 ;
		aRight = 0;
		aLeft = 0;
		aUp = 0;
		aDown = 0;

		if (handler.getKeyManager().up ) //|| handler.getKeyManager().upw )
			yMove = -speed;
		if (handler.getKeyManager().down ) //|| handler.getKeyManager().downs)
			yMove = speed;
		if (handler.getKeyManager().right ) // || handler.getKeyManager().rightd)
			xMove = speed;
		if (handler.getKeyManager().left ) //|| handler.getKeyManager().lefta)
			xMove = -speed;
		if(handler.getKeyManager().aRight) 
			aRight ++;
		if(handler.getKeyManager().aLeft) 
			aLeft++;
		if(handler.getKeyManager().aUp) 
			aUp++;
		if(handler.getKeyManager().aDown) 
			aDown++;

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
			switchSide = true;
			return animLeft.getCurrentFrame();
		}else if (xMove > 0){
			switchSide = false;
			return animRight.getCurrentFrame();
		}else if (yMove < 0 || yMove > 0 ){
			return animUd.getCurrentFrame();
		}else if (aUp > 0 ){
			if (switchSide) {
				return animAttackLeft.getCurrentFrame();
			}else { 
				return animAttackRight.getCurrentFrame();
			}
		}else if (aDown > 0 ){
			if (switchSide) {
				return animAttackLeft.getCurrentFrame();
			}else { 
				return animAttackRight.getCurrentFrame();
			}
		}else if (aRight > 0 ){
			switchSide = false;
			return animAttackRight.getCurrentFrame();
		}else if (aLeft > 0 ){
			switchSide = true;
			return animAttackLeft.getCurrentFrame();
		}else { // stand to the right or left (switchSide = true -> left)
			if(!switchSide) {
				return animStand.getCurrentFrame();
			}else {
				return animStandLeft.getCurrentFrame();
			}
		}
	}

	@Override
	public void die() {
		System.out.println("Enemy killed");
	}

	//Getters && Setters
	
	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}


}
