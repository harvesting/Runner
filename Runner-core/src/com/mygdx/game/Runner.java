package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;


public class Runner extends Game
{
	AssetManager manager;
	ModelBatch batch;
	Player player;
	Map map;
	Screen menu;
	PerspectiveCamera cam;
	
	
	public void create()
	{
		manager = new AssetManager();
		manager.load("ship.obj", Model.class);
		manager.load("Cube.obj", Model.class);
		manager.load("ground.obj", Model.class);
		manager.load("sky.obj", Model.class);
		manager.load("Fence.obj", Model.class);
		manager.load("menu.obj", Model.class);
		manager.finishLoading();
		batch = new ModelBatch();
		player = new Player((Model) manager.get("ship.obj"));
		map = new Map(this);	
		cam = new PerspectiveCamera(69, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 5, -5);
		cam.near = .1f;
		cam.far = 100;
		cam.fieldOfView = 65;
		menu = new MenuScreen(this);
		this.setScreen(menu);
	}
	
	public void render()
	{
		super.render();
	}
	
	public void dispose()
	{
		manager.dispose();
		batch.dispose();
		menu.dispose();
//		game.dispose();
	}
}