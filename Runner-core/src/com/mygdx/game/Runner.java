package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * This class is the main game class that loads everything and then sets the screen to main menu.
 * 
 * @author liaderlich
 * @version 1.0
 */
public class Runner extends Game
{
	AssetManager manager;
	ModelBatch batch;
	Player player;
	Map map;
	MenuScreen menu;
	EndScreen end;
	GameScreen game;
	PerspectiveCamera cam;
	Vector3 temp = new Vector3();
	
	public void create()
	{
		manager = new AssetManager();
		manager.load("ship.obj", Model.class);
		manager.load("CubeBlue.obj", Model.class);
		manager.load("CubeGreen.obj", Model.class);
		manager.load("CubeRed.obj", Model.class);
		manager.load("CubePurple.obj", Model.class);
		manager.load("CubeYellow.obj", Model.class);
		manager.load("CubeOrange.obj", Model.class);
		manager.load("ground.obj", Model.class);
		manager.load("Fence.g3db", Model.class);
		manager.load("menu.obj", Model.class);
		manager.load("Level1.obj", Model.class);
		manager.load("Level2.obj", Model.class);
		manager.load("Level3.obj", Model.class);
		manager.load("Level4.obj", Model.class);
		manager.load("Level5.obj", Model.class);
		manager.load("Level6.obj", Model.class);
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
		game = new GameScreen(this);
		end = new EndScreen(game, this);
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
	}
}