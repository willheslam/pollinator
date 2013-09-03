package com.loden.rogue.pollinator.views.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.loden.rogue.pollinator.models.ImageHandler;
import com.loden.rogue.pollinator.models.player.PlayerEntity;

public class PlayerRenderer implements EntityRenderer {
		private SpriteBatch batch;
		private PlayerEntity player;
		private ImageHandler imageHandler;
	
		public PlayerRenderer(PlayerEntity player, ImageHandler imageHandler){
				this.player = player;
				this.imageHandler = imageHandler;
				batch = new SpriteBatch();
		}
	
		@Override
		public void render(OrthographicCamera camera) {
				batch.setProjectionMatrix(camera.combined);
				batch.begin();

				batch.draw(imageHandler.getPlayerTexture(), 
						   player.getPosition().x,
						   player.getPosition().y, 
						   player.getDimensions().x,
						   player.getDimensions().y);

				batch.end();
		}

		@Override
		public void disposeBatch() {
				batch.dispose();
		}

}
