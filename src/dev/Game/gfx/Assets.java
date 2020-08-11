package dev.Game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	// the size of every one tile
	private static final int width=35 , height =35; 
	
	public static BufferedImage player, dirt, grass, stone, tree , stump;	
	
	public static void init () {
		SpriteSheet sheet = new SpriteSheet (ImageLoader.loadImage("/textures/objects22.png"));
		
		stump = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(2*width, 0, width, height);
		player = sheet.crop(0, height+1, width, 2*height);
	}
	
}// Assets
