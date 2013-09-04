package com.loden.rogue.pollinator.views.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.loden.rogue.pollinator.models.ImageHandler;
import com.loden.rogue.pollinator.models.player.PlayerEntity;

public class PlayerRenderer implements EntityRenderer {
		private PlayerEntity player;
		private ImageHandler imageHandler;
	
		public PlayerRenderer(PlayerEntity player, ImageHandler imageHandler){
				this.player = player;
				this.imageHandler = imageHandler;
		}
	
		@Override
		public void render(SpriteBatch batch) {
				batch.draw(imageHandler.getPlayerTexture(), 
						   player.getPosition().x,
						   player.getPosition().y, 
						   player.getDimensions().x,
						   player.getDimensions().y);
		}
}
