package dev.Game.states;

import java.awt.Graphics;

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

		uiManager.addObjects(new UIImageButton(200,200, 128, 64, Assets.btn_start, new ClickListener() {

			@Override
			// this is what happened if we click on the new button that we made right-now
			public void onClick() {
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
		uiManager.render(g);
	}


}
