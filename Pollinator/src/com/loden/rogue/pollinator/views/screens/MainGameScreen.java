package com.loden.rogue.pollinator.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.loden.rogue.pollinator.Pollinator;
import com.loden.rogue.pollinator.models.ImageHandler;
import com.loden.rogue.pollinator.models.ImageHandler.DistanceFieldFont;
import com.loden.rogue.pollinator.models.player.PlayerEntity;
import com.loden.rogue.pollinator.views.renderers.PlayerRenderer;

public class MainGameScreen implements Screen {
		private static final int VIRTUAL_WIDTH = 800;
		private static final int VIRTUAL_HEIGHT = 480;
		private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
		
		Pollinator game;
		private OrthographicCamera camera;
		private Rectangle viewport;
		private ImageHandler imageHandler;
		
		private PlayerEntity player;
		private PlayerRenderer playerRenderer;
		
		public MainGameScreen(Pollinator game){
				this.game = game;
				
				imageHandler = new ImageHandler();
				imageHandler.loadTextures();
				loadEntities();
				
				camera = new OrthographicCamera();
				camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		}
	
		@Override
		public void render(float delta) {
				camera.update();
	        
				Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
				playerRenderer.render(camera);
				
				Gdx.gl20.glEnable(GL20.GL_BLEND);
				
				DistanceFieldFont titleFont = imageHandler.getTitleFont();
				titleFont.draw(imageHandler.getSpriteBatch(), 50,450, 100, "The Pollinator", Color.CYAN, Color.ORANGE, 2.5f);
				//		
				imageHandler.getSpriteBatch().end();
		}

		@Override
		public void resize(int width, int height) {		
				/******** Neat little trick to resize the game to ********/
				/******** fit in any window size ********/
				float aspectRatio = (float)width/(float)height;
				float scale = 1f;
				Vector2 crop = new Vector2(0f, 0f);
			
				if(aspectRatio > ASPECT_RATIO){
						scale = (float)height/(float)VIRTUAL_HEIGHT;
						crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
				}
				else if(aspectRatio < ASPECT_RATIO){
	            		scale = (float)width/(float)VIRTUAL_WIDTH;
	            		crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
				}
				else {
	        			scale = (float) width / (float) VIRTUAL_WIDTH;
				}
	 
				float w = (float)VIRTUAL_WIDTH*scale;
				float h = (float)VIRTUAL_HEIGHT*scale;
				viewport = new Rectangle(crop.x, crop.y, w, h);
		}

		@Override
		public void show() {
		}

		@Override
		public void hide() {
		}

		@Override
		public void pause() {
		}

		@Override
		public void resume() {
		}

		@Override
		public void dispose() {
				imageHandler.disposeTextures();
		}
		
		public void loadEntities(){
				player =  new PlayerEntity();
				player.setPosition(new Vector2(VIRTUAL_WIDTH / 2 - player.tileSize / 2, player.tileSize));
				playerRenderer = new PlayerRenderer(player, imageHandler);
		}
}