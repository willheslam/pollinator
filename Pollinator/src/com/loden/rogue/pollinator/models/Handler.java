package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.assets.AssetManager;

public interface Handler {
	
	public void load(AssetManager manager);
	
	public void get(AssetManager manager);

	public void dispose();
}
