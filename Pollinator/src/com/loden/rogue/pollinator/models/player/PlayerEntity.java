package com.loden.rogue.pollinator.models.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.loden.rogue.pollinator.models.Entity;

public class PlayerEntity extends Entity {
		public float tileSize;
		public float speed;
		public Vector2 velocity;
		private Vector2 tmpVelocity;

		public PlayerEntity() {
				resize();
				velocity =  new Vector2(0, 0);
				tmpVelocity = new Vector2(0, 0);
		}

		public void resize() {
				tileSize = 128f;
				width = tileSize;
				height = tileSize;
				speed = tileSize / 10f;
			
				setDimensions(new Vector2(width, height));
		}
		
		public void setVelocity(Vector2 velocity){
				this.velocity = velocity;
		}
	
		public void update(){
				tmpVelocity.set(velocity);
				getPosition().add(tmpVelocity.scl(Gdx.graphics.getDeltaTime()
							  				* speed));
		}
}