package dev.Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean [] keys, justPressed, cantPress;
	public boolean up, down, left, right ; //movement control arrows
	public boolean upw, downs, lefta, rightd; //movement control2 wasd
	public boolean aUp, aDown, aRight, aLeft, c; //attack control (using wasd)

	public KeyManager () {
		keys = new boolean[256];
		justPressed =  new boolean[keys.length];
		cantPress = new boolean [keys.length];
	}

	public void tick() {

		for (int i = 0; i < keys.length; i++ ) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}


		//movement 
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		//upw = keys[KeyEvent.VK_W];
		//downs = keys[KeyEvent.VK_S];
		//lefta = keys[KeyEvent.VK_A];
		//rightd = keys[KeyEvent.VK_D];

		//attack
		aUp = keys[KeyEvent.VK_W];
		aDown = keys[KeyEvent.VK_S];
		aLeft = keys[KeyEvent.VK_A];
		aRight = keys[KeyEvent.VK_D];
		c = keys[KeyEvent.VK_C];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}


	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}

}
