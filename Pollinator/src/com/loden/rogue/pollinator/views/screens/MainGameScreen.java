package com.loden.rogue.pollinator.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.loden.rogue.pollinator.Pollinator;
import com.loden.rogue.pollinator.models.AssetHandler;
import com.loden.rogue.pollinator.models.ImageHandler;
import com.loden.rogue.pollinator.models.ModelHandler;
import com.loden.rogue.pollinator.models.fonts.FontHandler;
import com.loden.rogue.pollinator.models.player.PlayerEntity;
import com.loden.rogue.pollinator.views.renderers.PlayerRenderer;
import com.loden.rogue.pollinator.views.renderers.TitleFontRenderer;

public class MainGameScreen implements Screen {
		private static final int VIRTUAL_WIDTH = 800;
		private static final int VIRTUAL_HEIGHT = 480;
		private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
		
		Pollinator game;
		
		private OrthographicCamera camera;
		private PerspectiveCamera camera3D;
		private Rectangle viewport;

		private SpriteBatch batch;
		
		private PlayerEntity player;
		private PlayerRenderer playerRenderer;
		
		private TitleFontRenderer titleFontRenderer;

		private Lights lights;
		
	    public ModelBatch modelBatch;
	    
		public void setUpInput(){
			
			InputProcessor touchInput = new InputProcessor(){

				@Override
				public boolean keyDown(int keycode) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean keyUp(int keycode) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean keyTyped(char character) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean touchDown(int screenX, int screenY, int pointer, int button) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean touchUp(int screenX, int screenY, int pointer, int button) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean touchDragged(int screenX, int screenY, int pointer) {
					
					Vector2 relCoords = new Vector2(screenX / (float) Gdx.graphics.getWidth(),screenY / (float) Gdx.graphics.getHeight());
					
					relCoords.sub(0.5f, 0.5f);
			
					relCoords.y = - relCoords.y;
					
					if(screenX / (float) Gdx.graphics.getWidth() > 0.5){
						//right side of screen

						player.setRightHandSide(relCoords);
					}else{
						//left side of screen
						player.setLeftHandSide(relCoords);
					}
					return false;
				}

				@Override
				public boolean mouseMoved(int screenX, int screenY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean scrolled(int amount) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			
			Gdx.input.setInputProcessor(touchInput);
		}
		
		public MainGameScreen(Pollinator game){
				this.game = game;
				
				setUpInput();
				
				AssetHandler.load();
				
				AssetHandler.get();
				
				titleFontRenderer = AssetHandler.fonts.getTitleFont();
				
				loadEntities();
				
				camera = new OrthographicCamera();
				camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
				batch = new SpriteBatch();
				batch.setProjectionMatrix(camera.combined);
				
				camera3D = new PerspectiveCamera(70, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
				camera3D.position.set(1f, 1f, 1f);
				camera3D.lookAt(0,0,0);
				camera3D.near = 0.1f;
				camera3D.far = 20f;
				camera3D.update();
				
				modelBatch = new ModelBatch();
				
				
		        lights = new Lights();
		        lights.ambientLight.set(0.2f,0.45f,0.05f, 1f);
		        lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		        
				
		}
	
		@Override
		public void render(float delta) {
				camera.update();
				camera3D.update();
				
				player.update();
	        
				Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
				Gdx.gl.glClearColor(0.2f,0.45f,0.05f,0f);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
				Gdx.gl20.glEnable(GL20.GL_BLEND);

				/*
				Gdx.gl20.glEnable(GL20.GL_CULL_FACE);
		        modelBatch.begin(camera3D);
				playerRenderer.render(modelBatch,lights);
				modelBatch.end();
				Gdx.gl20.glDisable(GL20.GL_CULL_FACE);
				*/
				
				Gdx.gl20.glEnable(GL20.GL_CULL_FACE);
				Gdx.gl20.glCullFace(GL20.GL_BACK);
				
				Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);
				Gdx.gl20.glDepthMask(true);
				Gdx.gl20.glDepthFunc(GL20.GL_LESS);
				
	            long time = System.currentTimeMillis();

	            long duration = 5000;
	            long lastDuration =  (((long) (time / (double) duration )) * duration);

	            float progressDuration = (float) Math.abs(((time - lastDuration) / (double) duration));
				
				ShaderProgram monkeyShader = AssetHandler.shaders.getMonkeyShader();
				Mesh monkeyMesh = AssetHandler.models.getMonkeyMesh();
				
				Matrix4 monkeyMatrix = new Matrix4();
				
				Matrix4 monkeyProj = new Matrix4().setToProjection(0.1f, 10.0f, 80, ASPECT_RATIO);
			
				Matrix4 monkeyView = new Matrix4().translate(0, 0, -4 * (Gdx.input.getX() / (float) Gdx.graphics.getWidth()));
				
				Matrix4 monkeyModel = new Matrix4().rotate(0, 1, 0, 360 * progressDuration).scale(0.25f, 0.25f, 0.25f);
				
				monkeyMatrix.mul(monkeyProj).mul(monkeyView).mul(monkeyModel);
				
				monkeyShader.begin();
				monkeyShader.setUniformMatrix("matrix", monkeyMatrix);
				
				monkeyMesh.render(monkeyShader,GL20.GL_TRIANGLES);
				
				monkeyShader.end();
				
				Gdx.gl20.glDisable(GL20.GL_CULL_FACE);
				Gdx.gl20.glDepthMask(false);
				
				
				
				batch.begin();
				titleFontRenderer.render(batch, 50, 450, 100, "The Pollinator", Color.YELLOW, Color.ORANGE, 2.5f);
				titleFontRenderer.render(batch, 50, 300, 50, "FPS: " + Gdx.graphics.getFramesPerSecond(), Color.CYAN, Color.RED, 2f);
				playerRenderer.render(batch);
				batch.end();
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
				batch.dispose();
				modelBatch.dispose();
		}
		
		public void loadEntities(){
				player =  new PlayerEntity();
				player.setPosition(new Vector2(VIRTUAL_WIDTH / 2 - player.tileSize / 2, player.tileSize));
				playerRenderer = new PlayerRenderer(player);
		}
}