package com.loden.rogue.pollinator.models.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderHandler {
		ShaderProgram fontShader;
		
		public ShaderHandler(){
		}
		
		public void loadShaders(){
				loadFontShader();
		}
		
		public void loadFontShader(){
				fontShader = new ShaderProgram(Gdx.files.internal("data/shaders/font.vrt"),Gdx.files.internal("data/shaders/font.frg"));
		    
				if (fontShader.isCompiled() == false){
		    			Gdx.app.log("problem compiling font shader:", fontShader.getLog());
		    			System.exit(0);
				}
		}
		
		public ShaderProgram getFontShader(){
				return fontShader;
		}
		
		public void dispose(){
				fontShader.dispose();
		}
}
