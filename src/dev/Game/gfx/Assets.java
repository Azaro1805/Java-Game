package dev.Game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	// the size of every one tile
	private static final int width=35 , height =35;
	private static final int widthPlayer=46 , heightPlayer =74;
	
	
	public static BufferedImage player, dirt, grass, stone, tree, stump, rock;	
	public static BufferedImage [] player_down;
	
	public static void init () {
		SpriteSheet sheet = new SpriteSheet (ImageLoader.loadImage("/textures/objects22.png"));
		SpriteSheet sheetPlayer = new SpriteSheet (ImageLoader.loadImage("/textures/PlayerObjects.png"));

		
		player_down = new BufferedImage[2];
		player_down[0] = sheetPlayer.crop(3*width, 0, width, height); 
		player_down[1] = sheetPlayer.crop(3*width, 0, width, height); 
		stump = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(2*width, 0, width, height);
		rock = sheet.crop(3*width, 0, width, height);
		//player = sheetPlayer.crop(0, 0, widthPlayer, heightPlayer);

	}
	
}// Assets
