package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class ModelHandler implements Handler{
	
	private Model playerModel;
	private Mesh monkeyMesh;
	

	public void load(AssetManager man){		
        ModelLoader loader = new ObjLoader();
        monkeyMesh = loader.loadModel(Gdx.files.internal("data/models/monkey.obj")).meshes.get(0);
		man.load("data/models/bee.g3dj", Model.class);
	}
	public void get(AssetManager man){
		playerModel = man.get("data/models/bee.g3dj", Model.class);
	}
	public Mesh getMonkeyMesh(){
		return monkeyMesh;
	}
	public Model getPlayerModel(){
		return playerModel;
	}

	public void dispose(){
		
	}

}
