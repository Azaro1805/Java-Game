package dev.Game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	// the size of every one tile
	private static final int width=35 , height =35;
	private static final int widthPlayer=40 , heightPlayer =70;
	
	
	
	public static BufferedImage player, dirt, grass, stone, tree, stump, rock;	
	public static BufferedImage [] player_left, player_right, player_ud, player_Stand;
	
	public static void init () {
		SpriteSheet sheet = new SpriteSheet (ImageLoader.loadImage("/textures/objects22.png"));
		SpriteSheet sheetPlayer = new SpriteSheet (ImageLoader.loadImage("/textures/PlayerObjects.png"));

		//player movement
		player_right = new BufferedImage[8];
		player_right[0] = sheetPlayer.crop(0, 0, widthPlayer, heightPlayer); 
		player_right[1] = sheetPlayer.crop(widthPlayer, 0, widthPlayer, heightPlayer);
		player_right[2] = sheetPlayer.crop(2*widthPlayer, 0, widthPlayer, heightPlayer); 
		player_right[3] = sheetPlayer.crop(3*widthPlayer, 0, widthPlayer, heightPlayer); 
		player_right[4] = sheetPlayer.crop(4*widthPlayer, 0, widthPlayer, heightPlayer); 
		player_right[5] = sheetPlayer.crop(5*widthPlayer, 0, widthPlayer, heightPlayer); 
		player_right[6] = sheetPlayer.crop(6*widthPlayer, 0, widthPlayer, heightPlayer); 
		player_right[7] = sheetPlayer.crop(7*widthPlayer, 0, widthPlayer, heightPlayer); 
		
		player_left = new BufferedImage[8];
		player_left[0] = sheetPlayer.crop(7*widthPlayer, heightPlayer, widthPlayer, heightPlayer); 
		player_left[1] = sheetPlayer.crop(6*widthPlayer, heightPlayer, widthPlayer, heightPlayer); 
		player_left[2] = sheetPlayer.crop(5*widthPlayer, heightPlayer, widthPlayer, heightPlayer); 
		player_left[3] = sheetPlayer.crop(4*widthPlayer, heightPlayer, widthPlayer, heightPlayer); 
		player_left[4] = sheetPlayer.crop(3*widthPlayer, heightPlayer, widthPlayer, heightPlayer);
		player_left[5] = sheetPlayer.crop(2*widthPlayer, heightPlayer, widthPlayer, heightPlayer); 
		player_left[6] = sheetPlayer.crop(widthPlayer, heightPlayer, widthPlayer, heightPlayer);
		player_left[7] = sheetPlayer.crop(0, heightPlayer, widthPlayer, heightPlayer);
		
		player_ud = new BufferedImage[3];
		player_ud[0] = sheetPlayer.crop(0, 2*heightPlayer, widthPlayer, heightPlayer); 
		player_ud[1] = sheetPlayer.crop(widthPlayer, 2*heightPlayer, widthPlayer, heightPlayer);
		player_ud[2] = sheetPlayer.crop(2*widthPlayer, 2*heightPlayer, widthPlayer, heightPlayer); 
		
		player_Stand = new BufferedImage[2];
		player_Stand[0] = sheetPlayer.crop(0, 3*heightPlayer, widthPlayer, heightPlayer); 
		player_Stand[1] = sheetPlayer.crop(widthPlayer, 3*heightPlayer, widthPlayer, heightPlayer);
		
		//Static entity
		
		tree = sheet.crop(4*width, 0, width, 2*height);
		stump = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(2*width, 0, width, height);
		rock = sheet.crop(3*width, 0, width, height);
		//player = sheetPlayer.crop(0, 0, widthPlayer, heightPlayer);

	}
	
}// Assets
