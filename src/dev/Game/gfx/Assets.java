package dev.Game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	// the size of every one tile
	private static final int width=35 , height =35;
	private static final int widthPlayer=40 , heightPlayer =70;
	private static final int widthEnemy=56 , heightEnemy =77;

	public static Font font28, font48;

	public static BufferedImage player, dirt, grass, stone, tree, stump, rock, timber, tree2, tree3;
	public static BufferedImage cave, inventoryScreen, MenuScreen, GameOverScreen;
	public static BufferedImage [] player_left, player_right, player_ud, player_Stand, player_StandL;
	public static BufferedImage [] player_attackLeft, player_attackRight, player_Hurt, player_Die, btn_start;
	public static BufferedImage [] Enemy_left, Enemy_right, Enemy_ud, Enemy_Stand, Enemy_StandL;
	public static BufferedImage [] Enemy_attackLeft, Enemy_attackRight, Enemy_Hurt, Enemy_Die, bars;
	

	public static void init () {
		SpriteSheet sheet = new SpriteSheet (ImageLoader.loadImage("/textures/objects22.png"));
		SpriteSheet sheetPlayer = new SpriteSheet (ImageLoader.loadImage("/textures/PlayerObjects.png"));
		SpriteSheet button_menu = new SpriteSheet (ImageLoader.loadImage("/textures/menu.png"));
		SpriteSheet sheetEnemy = new SpriteSheet (ImageLoader.loadImage("/textures/EnemyObjects.png"));
		SpriteSheet sheetBars = new SpriteSheet (ImageLoader.loadImage("/textures/bar.png"));
		SpriteSheet MenuScreenSheet = new SpriteSheet (ImageLoader.loadImage("/textures/MenuScreen.png"));
		SpriteSheet GameOverScreenSheet = new SpriteSheet (ImageLoader.loadImage("/textures/GameOverScreen.png"));

		
		//button_menu
		btn_start =  new BufferedImage[2];
		btn_start[0] = button_menu.crop(0, 0, 94, 40);
		btn_start[1] = button_menu.crop(0, 0, 94, 40);

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

		player_StandL = new BufferedImage[2];
		player_StandL[0] = sheetPlayer.crop(0, 4*heightPlayer, widthPlayer, heightPlayer); 
		player_StandL[1] = sheetPlayer.crop(widthPlayer, 4*heightPlayer, widthPlayer, heightPlayer);

		//Player Combat
		player_attackRight = new BufferedImage[6];
		player_attackRight[0] = sheetPlayer.crop(0, 5*heightPlayer, widthPlayer, heightPlayer); 
		player_attackRight[1] = sheetPlayer.crop(widthPlayer, 5*heightPlayer, widthPlayer, heightPlayer); 
		player_attackRight[2] = sheetPlayer.crop(2*widthPlayer, 5*heightPlayer, widthPlayer, heightPlayer); 
		player_attackRight[3] = sheetPlayer.crop(3*widthPlayer, 5*heightPlayer, widthPlayer, heightPlayer); 
		player_attackRight[4] = sheetPlayer.crop(4*widthPlayer, 5*heightPlayer, widthPlayer, heightPlayer); 
		player_attackRight[5] = sheetPlayer.crop(5*widthPlayer, 5*heightPlayer, widthPlayer, heightPlayer); 

		player_attackLeft = new BufferedImage[6];
		player_attackLeft[0] = sheetPlayer.crop(5*widthPlayer, 6*heightPlayer, widthPlayer, heightPlayer); 
		player_attackLeft[1] = sheetPlayer.crop(4*widthPlayer, 6*heightPlayer, widthPlayer, heightPlayer); 
		player_attackLeft[2] = sheetPlayer.crop(3*widthPlayer, 6*heightPlayer, widthPlayer, heightPlayer); 
		player_attackLeft[3] = sheetPlayer.crop(2*widthPlayer, 6*heightPlayer, widthPlayer, heightPlayer); 
		player_attackLeft[4] = sheetPlayer.crop(widthPlayer, 6*heightPlayer, widthPlayer, heightPlayer); 
		player_attackLeft[5] = sheetPlayer.crop(0, 6*heightPlayer, widthPlayer, heightPlayer); 
		
		player_Die = new BufferedImage[7];
		player_Die [0] = sheetPlayer.crop(0, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [1] = sheetPlayer.crop(widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [2] = sheetPlayer.crop(2*widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [3] = sheetPlayer.crop(3*widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [4] = sheetPlayer.crop(4*widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [5] = sheetPlayer.crop(5*widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 
		player_Die [6] = sheetPlayer.crop(6*widthPlayer, 7*heightPlayer, widthPlayer, heightPlayer); 

		player_Hurt = new BufferedImage[4];
		player_Hurt [0] =  sheetPlayer.crop(0, 8*heightPlayer, widthPlayer, heightPlayer); 
		player_Hurt [1] =  sheetPlayer.crop(widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer); 
		player_Hurt [2] =  sheetPlayer.crop(2*widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer);
		player_Hurt [3] =  sheetPlayer.crop(3*widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer); 


		//Enemy movement
		Enemy_right = new BufferedImage[3];
		Enemy_right[0] = sheetEnemy.crop(0, 0, widthEnemy, heightEnemy); 
		Enemy_right[1] = sheetEnemy.crop(widthEnemy, 0, widthEnemy, heightEnemy);
		Enemy_right[2] = sheetEnemy.crop(2*widthEnemy, 0, widthEnemy, heightEnemy); 

		Enemy_left = new BufferedImage[3];
		Enemy_left[0] = sheetEnemy.crop(2*widthEnemy, heightEnemy, widthEnemy, heightEnemy); 
		Enemy_left[1] = sheetEnemy.crop(1*widthEnemy, heightEnemy, widthEnemy, heightEnemy); 
		Enemy_left[2] = sheetEnemy.crop(0, heightEnemy, widthEnemy, heightEnemy); 
		
		Enemy_ud = new BufferedImage[3];
		Enemy_ud[0] = sheetEnemy.crop(0, 0, widthEnemy, heightEnemy); 
		Enemy_ud[1] = sheetEnemy.crop(widthEnemy, 0, widthEnemy, heightEnemy);
		Enemy_ud[2] = sheetEnemy.crop(2*widthEnemy, 0, widthEnemy, heightEnemy);  

		Enemy_Stand = new BufferedImage[2];
		Enemy_Stand[0] = sheetEnemy.crop(0, 2*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Stand[1] = sheetEnemy.crop(widthEnemy, 2*heightEnemy, widthEnemy, heightEnemy);

		Enemy_StandL = new BufferedImage[2];
		Enemy_StandL[0] = sheetEnemy.crop(0, 3*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_StandL[1] = sheetEnemy.crop(widthEnemy, 3*heightEnemy, widthEnemy, heightEnemy);

		//Enemy Combat
		Enemy_attackRight = new BufferedImage[3];
		Enemy_attackRight[0] = sheetEnemy.crop(0, 4*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_attackRight[1] = sheetEnemy.crop(widthEnemy, 4*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_attackRight[2] = sheetEnemy.crop(2*widthEnemy, 4*heightEnemy, widthEnemy, heightEnemy); 

		Enemy_attackLeft = new BufferedImage[3];
		Enemy_attackLeft[0] = sheetEnemy.crop(2*widthEnemy, 5*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_attackLeft[1] = sheetEnemy.crop(1*widthEnemy, 5*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_attackLeft[2] = sheetEnemy.crop(0, 5*heightEnemy, widthEnemy, heightEnemy); 

		Enemy_Die = new BufferedImage[7];
		Enemy_Die [0] = sheetEnemy.crop(0, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [1] = sheetEnemy.crop(widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [2] = sheetEnemy.crop(2*widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [3] = sheetEnemy.crop(3*widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [4] = sheetEnemy.crop(4*widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [5] = sheetEnemy.crop(5*widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 
		Enemy_Die [6] = sheetEnemy.crop(6*widthEnemy, 7*heightEnemy, widthEnemy, heightEnemy); 

		/*Enemy_Hurt = new BufferedImage[4];
		player_Hurt [0] =  sheetPlayer.crop(0, 8*heightPlayer, widthPlayer, heightPlayer); 
		player_Hurt [1] =  sheetPlayer.crop(widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer); 
		player_Hurt [2] =  sheetPlayer.crop(2*widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer);
		player_Hurt [3] =  sheetPlayer.crop(3*widthPlayer, 8*heightPlayer, widthPlayer, heightPlayer); 
		*/
		
		//Static entity
		stump = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(2*width, 0, width, height);
		rock = sheet.crop(3*width, 0, width, height);
		tree = sheet.crop(4*width, 0, width, 2*height);
		timber = sheet.crop(5*width, 0, width, height);
		tree2 = sheet.crop(6*width, 0, width, 2*height);
		tree3 = sheet.crop(7*width, 0, width, 2*height);
		cave =  sheet.crop(284, 0, 173, 140);

		//player = sheetPlayer.crop(0, 0, widthPlayer, heightPlayer);

		//Inventory Screen
		font28 = FontLoader.loadFont("res/Fonts/slkscr.ttf", 28);
		font48 = FontLoader.loadFont("res/Fonts/slkscr.ttf", 48);
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen2.png");
		
		//Bars
		bars = new BufferedImage[1];
		bars [0] = sheetBars.crop(0,0, 202, 74); 
		
		 MenuScreen = MenuScreenSheet.crop(0, 0, 900, 600); 
		 GameOverScreen = GameOverScreenSheet.crop(0, 0, 900, 600);
	}

}// Assets
