package dev.Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean [] keys;
	public boolean up, upw, down, downs, left, lefta, right, rightd;

	public KeyManager () {
		keys = new boolean[256];
	}

	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		upw = keys[KeyEvent.VK_W];
		downs = keys[KeyEvent.VK_S];
		lefta = keys[KeyEvent.VK_A];
		rightd = keys[KeyEvent.VK_D];
		
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
