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

public class Player extends Creature {

	//Animation
	private Animation animRight, animLeft, animUd, animStand, animAttackRight, animStandLeft, animAttackLeft,
	animDie, animHurt;
	private boolean switchSide = false, levelUp = false, ATTlevelUp = false;;
	private int aRight, aLeft, aUp, aDown, xp, xpToLevelUp, clickL, maxHealth, woodATT, stoneATT, wood, stone;
	private int clickP;

	
	// Attack Timers
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	// attackCooldown  - cooldown between attacks in ms;
	private int attackPower = 1;

	//Inventory
	private Inventory inventory;

	public Player( Handler handler ,float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT*2);
		this.setIfPlayer(true);
		this.xp = 4;
		this.xpToLevelUp = 5;
		this.maxHealth = health;
		this.stoneATT = 1;
		this.woodATT = 1;
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

		inventory =  new Inventory(handler);
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
		
		if(clickL == 1) { // level-up process
			PossibleTolevelUp();
			levelUp();
		}

		if(clickP == 1) { // attack power level-up process
			PossibleAttackTolevelUp();
			attackLevelUp();
		}
		
		//Attack
		checkAttack();

		//Inventory
		inventory.tick();
	}

	private void checkAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown) // return if the player try to attack to fast (nothing happend)
			return;

		if(inventory.isActive()) // if the inventory is open the player doesn't move
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
		clickL = 0;
		clickP = 0;

		if (inventory.isActive())
			return;

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
		if (handler.getKeyManager().clickP) 
			clickP++;

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
		System.out.println("You Lose");
		handler.getGame().setState(handler.getGame().gameOverState);
	}

	public void postRender(Graphics g) {
		inventory.render(g);
	}

	public void PossibleTolevelUp() {
		if( xpToLevelUp <= xp) 
			levelUp = true;
	}
	
	public void PossibleAttackTolevelUp() {
		wood = inventory.getNumberOf(0);
		stone = inventory.getNumberOf(1);
		if( wood >= woodATT && stone >= stoneATT) 
			ATTlevelUp = true;
	}

	public void levelUp() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < (attackCooldown+100)) // return if the player try to click level up too fast (nothing happen)
			return;
		
		if(levelUp && clickL == 1) {
			setHealth(maxHealth+1);
			System.out.println("Player Health :" + getHealth());
			levelUp = false;
			xp = xp-xpToLevelUp;
			xpToLevelUp += 2 ;
			attackPower ++;
			System.out.println("Player Level Up");
			//System.out.println("xpToLevelUp : " +xpToLevelUp + " xp : " + xp + " attackPower : "+ attackPower);
		}else {
			System.out.println("Cant level Up, need more " + (xpToLevelUp-xp) +" xp");
		}
		attackTimer = 0;
	}
	
	public void attackLevelUp() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < (attackCooldown+100)) // return if the player try to click level up too fast (nothing happen)
			return;
		
		if (ATTlevelUp) {
			ATTlevelUp = false;
			inventory.removeItem(1);
			inventory.removeItem(0);
			attackPower++;
			System.out.println("attack power level Up, attack power:" + attackPower);
		}
		attackTimer = 0;
	}

	//Getters && Setters

	public int getAttackPower() {
		return attackPower;
	}

	public boolean isLevelUp() {
		return levelUp;
	}

	public void setLevelUp(boolean levelUp) {
		this.levelUp = levelUp;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getXpToLevelUp() {
		return xpToLevelUp;
	}

	public void setXpToLevelUp(int xpToLevelUp) {
		this.xpToLevelUp = xpToLevelUp;
	}

	
	
}// Player
