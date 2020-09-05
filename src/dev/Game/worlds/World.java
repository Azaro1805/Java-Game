package dev.Game.worlds;

import java.awt.Graphics;

import dev.Game.Handler;
import dev.Game.Entities.EntityManager;
import dev.Game.Entities.Creatures.Enemy;
import dev.Game.Entities.Creatures.Player;
import dev.Game.Entities.Statics.Tree;
import dev.Game.Entities.Statics.Tree2;
import dev.Game.Entities.Statics.Tree3;
import dev.Game.items.ItemManager;
import dev.Game.tiles.Tile;
import dev.Game.utils.Utils;

public class World {

	private Handler handler;
	private int width, height; //size of the map
	private int spawnX, spawnY;
	private int [][] mapTiles;
	
	//Entities
	private EntityManager entityManager;

	//Item 
	private ItemManager itemManager;
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Tree2(handler, 100, 350));
		entityManager.addEntity(new Tree3(handler, 100, 450));
		//entityManager.addEntity(new Enemy(handler, 200, 200, 5 , 1));
		entityManager.addEntity(new Enemy(handler, 150, 150, 5 , 1));

		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		//for (int i = 0 ; i<entityManager.getEntities().size(); i++){
		//System.out.println(entityManager.getEntities().get(i));}
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH );
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILEWIDTH +1 );
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT );
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT +1 );;
		
		for (int y=yStart; y<yEnd ;  y++ ) {
			for (int x = xStart; x<xEnd; x++) {
				getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset() ));
			}
		}
		
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height )
			return Tile.grassTile;
		
		Tile t = Tile.tiles[mapTiles[x][y]];
		if (t == null)
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		 String [] tokens  = file.split("\\s+");
		 width = Utils.parseInt(tokens[0]);
		 height = Utils.parseInt(tokens[1]);
		 spawnX = Utils.parseInt(tokens[2]);	
		 spawnY = Utils.parseInt(tokens[3]);
		 
		 mapTiles = new int [width][height];
		 for (int y=0 ; y<height; y++) {
			 for(int x=0; x<width; x++) {
				 mapTiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // add 4 = (width,height,spawns)
			 }
		 }

	}

	//Getters & Setters
	
	public int getWidth() {
		return width;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	}//World
