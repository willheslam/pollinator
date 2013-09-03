package com.loden.rogue.pollinator.views.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface EntityRenderer {
		
		public void render(OrthographicCamera camera);
		
		public void disposeBatch();
}
