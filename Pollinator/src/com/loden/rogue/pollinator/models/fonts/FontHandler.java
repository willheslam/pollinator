package com.loden.rogue.pollinator.models.fonts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.loden.rogue.pollinator.models.Handler;
import com.loden.rogue.pollinator.views.renderers.TitleFontRenderer;

public class FontHandler implements Handler{
		private TitleFontRenderer titleFont;
		private TextureParameter paramMaxMip;
	
		public FontHandler(){

		}

		public void get(AssetManager man){
			titleFont = new TitleFontRenderer();
        	titleFont.font = man.get("data/fonts/jura-regular-70-signed.fnt", BitmapFont.class);
        	titleFont.spread = 5;
		}
		
		public void load(AssetManager man){
				/************************
				 * maximum prettiness - * 
				 * Can probably afford  * 
				 * to do this for 		*
				 * title screen fonts	*
				 ************************/
			
	        	paramMaxMip = new TextureParameter();
	        	paramMaxMip.minFilter = TextureFilter.MipMapLinearLinear;
	        	paramMaxMip.magFilter = TextureFilter.MipMapLinearLinear;
	        	paramMaxMip.genMipMaps = true;
	           
	        	man.load("data/fonts/jura-regular-70-signed.png", Texture.class, paramMaxMip);
	        	man.load("data/fonts/jura-regular-70-signed.fnt", BitmapFont.class);
	        
		}
		
		public TitleFontRenderer getTitleFont(){
				return titleFont;
		}
		
		public void dispose(){
				titleFont.dispose();
		}
}