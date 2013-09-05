package com.loden.rogue.pollinator.views.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.math.Matrix4;
import com.loden.rogue.pollinator.models.AssetHandler;
import com.loden.rogue.pollinator.models.ImageHandler;
import com.loden.rogue.pollinator.models.player.PlayerEntity;

public class PlayerRenderer implements EntityRenderer {
		private PlayerEntity player;

	
		private ModelInstance modelInstance;
		
		public PlayerRenderer(PlayerEntity player){
				this.player = player;

				
				this.modelInstance = new ModelInstance(AssetHandler.models.getPlayerModel());

				
		}
	
		public void render(ModelBatch batch,Lights lights){

            long time = System.currentTimeMillis();

            long duration = 4000;
            long lastDuration =  (((long) (time / (double) duration )) * duration);

            float progressDuration = (float) Math.abs(((time - lastDuration) / (double) duration));
            
			modelInstance.transform = new Matrix4().trn(-5, -5, -5).rotate(0, 1,0,135 - player.angle);
			
				batch.render(modelInstance,lights);
			
		}
		
		@Override
		public void render(SpriteBatch batch) {
			
			Matrix4 trans = new Matrix4().setToTranslation(player.getPosition().x, player.getPosition().y, 0);
			trans.rotate(0, 0, 1, player.angle);
			
			batch.setTransformMatrix(trans);
			
			
			batch.draw(AssetHandler.images.getPlayerTexture(), 
					   - player.getDimensions().x / 2f,
					   - player.getDimensions().y / 2f, 
					   player.getDimensions().x,
					   player.getDimensions().y);
			
			batch.setTransformMatrix(trans.idt());
				/*batch.draw(imageHandler.getPlayerTexture(), 
						   player.getPosition().x,
						   player.getPosition().y, 
						   player.getDimensions().x,
						   player.getDimensions().y);
						   */
		}
}
