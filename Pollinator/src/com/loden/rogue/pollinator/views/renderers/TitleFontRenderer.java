package com.loden.rogue.pollinator.views.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.loden.rogue.pollinator.models.shaders.ShaderHandler;

public class TitleFontRenderer {
		public float spread; //this is a value selected when creating the font in the first place, so it's essentially hardcoded
		public BitmapFont font;
		private ShaderProgram drawFontShader;
		private float scale;
		private ShaderHandler shaderHandler;
		SpriteBatch batch;
		
		public TitleFontRenderer(){
				shaderHandler = new ShaderHandler();
				shaderHandler.loadFontShader();
				drawFontShader = shaderHandler.getFontShader();
		}
		
		public void render(SpriteBatch batch, float posX, float posY, float size, String text, Color textColor, Color shadowColor, float shadowIntensity){
				batch.setShader(drawFontShader);
				batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			
				scale = font.getCapHeight() / size;
				drawFontShader.setUniformf("scale", scale);
				drawFontShader.setUniformf("spread",spread);
				drawFontShader.setUniformf("shadowColour",shadowColor.r,shadowColor.g,shadowColor.b,shadowColor.a);;
				drawFontShader.setUniformf("shadowIntensity",shadowIntensity);
			
				font.setColor(textColor);
				font.draw(batch, text, posX, posY);
				
				/******** So others aren't affected by the shader ********/
				batch.setShader(null);
				/******** No need to flush. Previous shader does that for us. ****/
		}
		
		public void dispose(){
				drawFontShader.dispose();
		}
}
