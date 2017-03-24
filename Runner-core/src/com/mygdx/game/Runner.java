package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Runner extends ApplicationAdapter {
	static PerspectiveCamera cam;
	Player player;
	ModelBatch batch;
	Vector3 cameraX = new Vector3();
	AssetManager manager;
	ModelInstance ground;
	
	@Override
	public void create () {
		manager = new AssetManager();
		manager.load("ship.obj", Model.class);
		manager.load("Ground.obj", Model.class);
		batch = new ModelBatch();
		cam = new PerspectiveCamera(69, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 5, -5);
		cam.near = .1f;
		cam.far = 100f;
		manager.finishLoading();
		player = new Player( (Model) manager.get("ship.obj"));
		ground = new ModelInstance( (Model) manager.get("Ground.obj"), new Vector3(-30, 0, 20));	
		cam.lookAt(player.transform.getTranslation(cameraX));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor( 1, 1, 1, 1 );
		batch.begin(cam);
		batch.render(player);
		batch.render(ground);
		batch.end();
		cam.position.set(0, 5, -5);
//		cam.translate(player.newPosition);
//		cam.rotate(cameraX.set(1, 0, 0), -25);
		player.checkForInput();
		cam.update();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}

}
