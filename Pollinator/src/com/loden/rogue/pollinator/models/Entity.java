package com.loden.rogue.pollinator.models;

import com.badlogic.gdx.math.Vector2;

public class Entity {
		protected Vector2 position;
		protected Vector2 dimensions;
		protected float width;
		protected float height;

		public Entity(float posX, float posY) {
				position = new Vector2(posX, posY);
		}

		public Entity() {
		}

		public Vector2 getPosition() {
				return position;
		}

		public void setPosition(Vector2 position) {
				this.position =  position;
		}

		public Vector2 getDimensions() {
				return dimensions;
		}

		public void setDimensions(Vector2 dimensions) {
				this.dimensions =  dimensions;
		}
}