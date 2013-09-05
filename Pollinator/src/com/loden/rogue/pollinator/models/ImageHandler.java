package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ImageHandler implements Handler{
		private Texture playerSprite;
	
		public ImageHandler() {
		}

		public void load(AssetManager manager){				
				loadPlayerTexture();
		}
		public void get(AssetManager manager){
			
		}
	
		public void loadPlayerTexture(){
				playerSprite = new Texture("data/beewithvector.png");
		}
	
		public Texture getPlayerTexture(){
			return playerSprite;
		}
	
		public void disposePlayerTexture(){
				playerSprite.dispose();
		}
	
		public void dispose(){
				disposePlayerTexture();
		}
}