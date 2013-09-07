package com.loden.rogue.pollinator.models.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.loden.rogue.pollinator.models.Handler;

public class ShaderHandler implements Handler{
		ShaderProgram fontShader;
		
		ShaderProgram monkeyShader;
		
		public ShaderHandler(){
		}
		
		public void load(AssetManager man){
				loadFontShader();
		}
		
		public void get(AssetManager man){
			
		}
		
		public void loadFontShader(){
				fontShader = new ShaderProgram(Gdx.files.internal("data/shaders/font.vrt"),Gdx.files.internal("data/shaders/font.frg"));
		    
				if (fontShader.isCompiled() == false){
		    			Gdx.app.log("problem compiling font shader:", fontShader.getLog());
		    			System.exit(0);
				}
				
				monkeyShader = new ShaderProgram(Gdx.files.internal("data/shaders/monkey.vrt"),Gdx.files.internal("data/shaders/monkey.frg"));
			    
				if (monkeyShader.isCompiled() == false){
		    			Gdx.app.log("problem compiling monkey shader:", monkeyShader.getLog());
		    			System.exit(0);
				}
				
		}
		
		public ShaderProgram getMonkeyShader(){
			return monkeyShader;
		}
		
		public ShaderProgram getFontShader(){
				return fontShader;
		}
		
		public void dispose(){
				fontShader.dispose();
		}
}
