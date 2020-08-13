package dev.Game.worlds;

import java.awt.Graphics;

import dev.Game.tiles.Tile;
import dev.Game.utils.Utils;

public class World {

	private int width, height; //size of the map
	private int spawnX, spawnY;
	private int [][] mapTiles;

	
	public World(String path) {
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for (int y=0; y<height ;  y++ ) {
			for (int x =0; x<width; x++) {
				getTile(x,y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[mapTiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
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
	
	
	}//World
