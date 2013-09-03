package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.graphics.Texture;

public class ImageHandler {
		private Texture playerSprite;
	
		public ImageHandler() {
		}

		public void loadTextures(){
				loadPlayerTexture();
		}
	
		public void loadPlayerTexture(){
				playerSprite = new Texture("data/bee.png");
		}
	
		public Texture getPlayerTexture(){
			return playerSprite;
		}
	
		public void disposePlayerTexture(){
				playerSprite.dispose();
		}
	
		public void disposeTextures(){
				disposePlayerTexture();
		}
}