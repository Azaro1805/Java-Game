package dev.Game.Entities.Creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.Game.Handler;
import dev.Game.Entities.Entity;
import dev.Game.gfx.Animation;
import dev.Game.gfx.Assets;
import dev.Game.inventory.Inventory;
import dev.Game.items.Item;

public class Boss extends Creature {

	//Animation
	private Animation animRight, animLeft, animUd, animStand, animAttackRight, animStandLeft, animAttackLeft,
	animDie, animHurt;
	private boolean switchSide = false, die = false;
	private int aRight, aLeft, aUp, aDown;

	// Attack Timers
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	// attackCooldown  - cooldown between attacks in ms;
	private int attackPower = 7;

	public Boss( Handler handler ,float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2 );

		//Animation
		animRight = new Animation(100, Assets.Enemy_right);
		animLeft = new Animation(100, Assets.Enemy_left);
		animUd = new Animation(100, Assets.Enemy_ud);
		animStand = new Animation(1000, Assets.Enemy_Stand);
		animStandLeft = new Animation(1000, Assets.Enemy_StandL);
		animAttackRight = new Animation(100, Assets.Enemy_attackRight);
		animAttackLeft = new Animation(100, Assets.Enemy_attackLeft);
		animDie = new Animation(350, Assets.Enemy_Die); // need to graphics improved
		animHurt = new Animation(100, Assets.Enemy_Hurt);

	}

	public Boss( Handler handler ,float x, float y, int health ) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2, health);

		//Animation
		animRight = new Animation(100, Assets.Enemy_right);
		animLeft = new Animation(100, Assets.Enemy_left);
		animUd = new Animation(100, Assets.Enemy_ud);
		animStand = new Animation(1000, Assets.Enemy_Stand);
		animStandLeft = new Animation(1000, Assets.Enemy_StandL);
		animAttackRight = new Animation(100, Assets.Enemy_attackRight);
		animAttackLeft = new Animation(100, Assets.Enemy_attackLeft);
		animDie = new Animation(350, Assets.Enemy_Die); // need to graphics improved
		animHurt = new Animation(100, Assets.Enemy_Hurt);

	}

	public Boss( Handler handler ,float x, float y, int health , int attackPower ) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2, health);

		//Animation
		animRight = new Animation(100, Assets.Enemy_right);
		animLeft = new Animation(100, Assets.Enemy_left);
		animUd = new Animation(100, Assets.Enemy_ud);
		animStand = new Animation(1000, Assets.Enemy_Stand);
		animStandLeft = new Animation(1000, Assets.Enemy_StandL);
		animAttackRight = new Animation(100, Assets.Enemy_attackRight);
		animAttackLeft = new Animation(100, Assets.Enemy_attackLeft);
		animDie = new Animation(350, Assets.Enemy_Die); // need to graphics improved
		animHurt = new Animation(100, Assets.Enemy_Hurt);
		this.attackPower = attackPower;
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
		//animHurt.tick();

		//Movement
		getInput();
		move();
		//handler.getGameCamera().ceterCamrea(this);

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
			ar.x = cb.x + cb.width / 2 - arSize / 2 ;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown) { 
			ar.x = cb.x + cb.width / 2 - arSize / 2 ;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2 ;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x +  cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2 ;
		}else {
			return;
		}

		attackTimer = 0;

		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) 
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {// attackPower - how much damage the player deal
				if(e.isPlayer()) {
					e.hurt(attackPower);
					System.out.println("Enemy hurt me, I lost " + attackPower + " hp");
				}
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
			yMove = -speed +1;
		if (handler.getKeyManager().down ) //|| handler.getKeyManager().downs)
			yMove = speed -1;
		if (handler.getKeyManager().right ) // || handler.getKeyManager().rightd)
			xMove = speed -1;
		if (handler.getKeyManager().left ) //|| handler.getKeyManager().lefta)
			xMove = -speed +1 ;
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
		//if(die)
			//g.drawImage(animDie.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.drawImage(getCurrentInamationFrame(), (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//if(health == 1)
			// handler.getWorld().getEntityManager().addEntity(new Enemy(handler, x+300, y+150, 5 , 1));



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
				return animAttackRight.getCurrentFrame();
			}else { 
				return animAttackLeft.getCurrentFrame();
			}
		}else if (aDown > 0 ){
			if (switchSide) {
				return animAttackRight.getCurrentFrame();
			}else { 
				return animAttackLeft.getCurrentFrame();
			}
		}else if (aRight > 0 ){
			switchSide = false;
			return animAttackLeft.getCurrentFrame();
		}else if (aLeft > 0 ){
			switchSide = true;
			return animAttackRight.getCurrentFrame();
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
		die = true;
		System.out.println("Enemy killed");
		// doesn't do anything	
		 //handler.getGame().getG().drawImage(animDie.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

			
		
	}

	//Getters && Setters

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}


}
