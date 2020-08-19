package dev.Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean [] keys;
	public boolean up, down, left, right ; //movement control arrows
	public boolean upw, downs, lefta, rightd; //movement control2 wasd
	public boolean aUp, aDown, aRight, aLeft, c; //attack control (using wasd)

	public KeyManager () {
		keys = new boolean[256];
	}

	public void tick() {
		
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
		keys[e.getKeyCode()] = true;
		System.out.println("press");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

}
