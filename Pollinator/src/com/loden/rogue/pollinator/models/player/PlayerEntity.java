package com.loden.rogue.pollinator.models.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.loden.rogue.pollinator.models.Entity;

public class PlayerEntity extends Entity {
		public float tileSize;
		public float speed;
		public Vector2 velocity;
		private Vector2 tmpVelocity;

		private Vector2 leftHand;
		private Vector2 rightHand;
		private Vector2 tmpHanded;
		
		public float angle = 0;
		
		public PlayerEntity() {
				resize();
				velocity =  new Vector2(0, 0);
				tmpVelocity = new Vector2(0, 0);
				tmpHanded = new Vector2(0, 0);
				leftHand = new Vector2(0, 0f);
				rightHand = new Vector2(1, 0f);
				
		}

		public void resize() {
				tileSize = 64f;
				width = tileSize;
				height = tileSize;
				speed = tileSize / 10f;
			
				setDimensions(new Vector2(width, height));
		}
		
		public void applyVelocity(Vector2 deltaV){
			velocity.add(deltaV);
		}
		
		public void setRightHandSide(Vector2 rightHand){
				this.rightHand = rightHand;
	
		}
		public void setLeftHandSide(Vector2 leftHand){
				this.leftHand = leftHand;
		}
		
		public void setVelocity(Vector2 velocity){
				this.velocity = velocity;
		}
	
		public void update(){
			
				float dx = rightHand.x - leftHand.x;
				float dy = rightHand.y - leftHand.y;
				
				Vector2 normal = new Vector2(-dy,dx);	
				
			
				velocity.scl(0.8f);
			
				tmpHanded.set(leftHand);
				tmpHanded.add(rightHand);
				
				angle += normal.angle() - 90;
				angle /= 2.0; //bit of smoothing
		
				
				velocity.add(-angle * 0.05f,tmpHanded.y * 4f);
				
				tmpVelocity.set(velocity);
				getPosition().add(tmpVelocity.scl(Gdx.graphics.getDeltaTime() * speed));
		}
}
