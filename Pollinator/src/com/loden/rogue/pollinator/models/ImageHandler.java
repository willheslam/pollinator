package com.loden.rogue.pollinator.models;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ImageHandler {
		private Texture playerSprite;
		private AssetManager assetManager;
		
		private DistanceFieldFont titleFont;
		private ShaderProgram drawFont;
		private SpriteBatch spriteBatch;
		
		public DistanceFieldFont getTitleFont(){
			return titleFont;
		}
		
		public SpriteBatch getSpriteBatch(){
			return spriteBatch;
		}
		
		public class DistanceFieldFont{
			public float spread; //this is a value selected when creating the font in the first place, so it's essentially hardcoded
			public BitmapFont font;
			
			public void draw(SpriteBatch batch,float posX,float posY,float size,String text,Color textColor,Color shadowColor,float shadowIntensity){
				
		
				
				batch.setShader(drawFont);
				batch.begin();
		
				
				batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				
				float scale = font.getCapHeight() / size;
				drawFont.setUniformf("scale", scale);
				drawFont.setUniformf("spread",spread);
				

				drawFont.setUniformf("shadowColour",shadowColor.r,shadowColor.g,shadowColor.b,shadowColor.a);;
				drawFont.setUniformf("shadowIntensity",shadowIntensity);
				
			//	batch.setColor(textColor);
				
				font.setColor(textColor);
				

		        font.draw(batch, text, posX, posY);
		        
		        batch.flush();
				
			}
		}
	
		public ImageHandler() {
				assetManager = new AssetManager();
				/*make the asset manager here for now - if it's used to load music etc. later,
				 it will make more sense to create this elsewhere
				 */
			
				spriteBatch = new SpriteBatch();
		}

		public void loadTextures(){
				loadShaders();
				loadFonts();
				
				loadPlayerTexture();
		}
		
		public void loadShaders(){
				//probably should put in some nice shader loading/disposing manager
			
				{
		        ShaderProgram newShader = new ShaderProgram(Gdx.files.internal("data/shaders/font.vrt"),Gdx.files.internal("data/shaders/font.frg"));
		        if (newShader.isCompiled() == false){
		            Gdx.app.log("problem compiling font shader:", newShader.getLog());
		            System.exit(0);
		        }
		        drawFont = newShader;
				}
			
		}
		
		public void loadFonts(){
				
				//maximum prettiness - can probably afford to do this for title screen fonts
		        TextureParameter paramMaxMip = new TextureParameter();
		        paramMaxMip.minFilter = TextureFilter.MipMapLinearLinear;
		        paramMaxMip.magFilter = TextureFilter.MipMapLinearLinear;
		        paramMaxMip.genMipMaps = true;
		        
	   
		        assetManager.load("data/fonts/jura-regular-70-signed.png",Texture.class,paramMaxMip);
		        assetManager.load("data/fonts/jura-regular-70-signed.fnt",BitmapFont.class);
		        
		        assetManager.finishLoading();
		        
		        titleFont = new DistanceFieldFont();
	            titleFont.font = assetManager.get("data/fonts/jura-regular-70-signed.fnt",BitmapFont.class);
	            titleFont.spread = 5;
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
			
				assetManager.dispose();
				disposePlayerTexture();
		}
}