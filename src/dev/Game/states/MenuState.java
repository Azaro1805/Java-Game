package dev.Game.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.Game.Handler;
import dev.Game.gfx.Assets;
import dev.Game.ui.ClickListener;
import dev.Game.ui.UIImageButton;
import dev.Game.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager; 

	public  MenuState (Handler handler) {
		super(handler);
		uiManager =  new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObjects(new UIImageButton(350,200, 128, 64, Assets.btn_start, new ClickListener() {

			@Override
			// this is what happened if we click on the new button that we made right-now
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		} ));

	
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.MenuScreen, 0, 0, 900, 600, null);
		uiManager.render(g);

	}


}
