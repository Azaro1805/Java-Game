package dev.Game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import dev.Game.Handler;
import dev.Game.gfx.Assets;
import dev.Game.gfx.Text;
import dev.Game.items.Item;


public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;

	//inventory size screen
	private int invX = 64, invY= 48,  
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;

	private int invImageX = 452, invImageY= 82,  
			invImageWidth = 64, invImageHeight = 64;

	private int invCountX = 484, invCountY= 172;

	private int selectedItem = 0;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems =  new ArrayList<Item>();

		addItem(Item.Wood.createNew(1));
		addItem(Item.Rock.createNew(5));

	}

	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
			active = !active; 
		if(!active)
			return;

		//Movement in inventory
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) || 
				handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) ||
				handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;

		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}

	public void render (Graphics g) {
		if(!active)
			return;

		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

		int len = inventoryItems.size();
		if(len ==0) 
			return;
		for(int i = -5;i < 6;i++){
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}

		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
		//Text.drawString(g, "> Rock <", invListCenetrX, invListCenetrY, true, Color.white, Assets.font28);
	}

	// Inventory methods

	public void addItem(Item item){

		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;

			}
		}
		inventoryItems.add(item);
	}

	public void removeItem(int id){

		for(Item i : inventoryItems){
			if(i.getId() == id){
				i.setCount(i.getCount() -1);
				if(i.getId() == 0) 
					inventoryItems.remove(i);
				return;
			}
		}
	}

	//Getters && Setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public int getNumberOf (int id) {

		for( Item i : inventoryItems) {
			//System.out.println("i = " +  i + "countItem =" + countItem );
			if(i.getId() == id) {
				return i.getCount();
			}
		}
		return 0;
	}

}// Inventory
