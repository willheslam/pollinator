package com.loden.rogue.pollinator;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pollinator";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		cfg.vSyncEnabled = true;
		
		
		new LwjglApplication(new Pollinator(), cfg);
	}
}
