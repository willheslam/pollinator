package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

public class ModelHandler implements Handler{
	
	private Model playerModel;
	

	public void load(AssetManager man){				
		man.load("data/models/bee.g3dj", Model.class);
	}
	public void get(AssetManager man){
		playerModel = man.get("data/models/bee.g3dj", Model.class);
	}
	
	public Model getPlayerModel(){
		return playerModel;
	}

	public void dispose(){
		
	}

}
