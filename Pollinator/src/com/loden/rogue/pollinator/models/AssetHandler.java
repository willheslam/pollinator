package com.loden.rogue.pollinator.models;
import com.badlogic.gdx.assets.AssetManager;
import com.loden.rogue.pollinator.models.fonts.FontHandler;
import com.loden.rogue.pollinator.models.shaders.ShaderHandler;

public class AssetHandler {
	
	private static AssetManager manager;
	public static ImageHandler images;
	public static FontHandler fonts;
	public static ModelHandler models;
	public static ShaderHandler shaders;
	
	public static void load(){
		manager = new AssetManager();
		images = new ImageHandler();
		fonts = new FontHandler();
		models = new ModelHandler();
		shaders = new ShaderHandler();
		
		images.load(manager);
		fonts.load(manager);
		models.load(manager);
		shaders.load(manager);
	}
	
	public static void get(){
		
		manager.finishLoading();
		
		images.get(manager);
		fonts.get(manager);
		models.get(manager);
		shaders.get(manager);
		
	}
	
	public static void dispose(){
		manager.dispose();
		images.dispose();
		fonts.dispose();
		models.dispose();
		shaders.dispose();
	}
}
