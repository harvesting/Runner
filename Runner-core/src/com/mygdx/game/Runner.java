package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.math.Vector3;

public class Runner extends Game implements Screen
{
	PerspectiveCamera cam;
	private Player player;
	ModelBatch batch;
	private Vector3 temp = new Vector3();
	AssetManager manager;
	private ModelInstance testCube;
	private ModelInstance sky;
	private ModelInstance sky2;
	private Map map;
	boolean beginning, middle, end;
		//235
		//355
		// r * 3f
		//playerZCubes1 += 225; for both
	private float playerZ = 100;
	private float playerZCubes1 = 235;
	private float playerZCubes2 = 355;
//	Hitbox testHitbox;
	Hitbox playerHitbox;
	private boolean onSecondRow;
	private boolean onFirstRow;
	Environment environment;
	DirectionalLight whiteLight;
	DirectionalLight yellowLight;
	SpotLight lit;
	BitmapFont font;
	ModelInstance menu;
	
	@Override
	public void create()
	{
		manager = new AssetManager();
		manager.load("ship.obj", Model.class);
		manager.load("CubeBlue.g3db", Model.class);
		manager.load("ground.obj", Model.class);
		manager.load("sky.obj", Model.class);
		manager.load("FenceSmall.obj", Model.class);
		manager.load("FenceBig.obj", Model.class);
		manager.load("menu.obj", Model.class);
		manager.finishLoading();
		batch = new ModelBatch();
		cam = new PerspectiveCamera(69, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 5, -5);
		cam.near = .1f;
		cam.far = 100f;
		cam.fieldOfView = 75;
		player = new Player((Model) manager.get("ship.obj"));
		beginning = true;
		middle = false;
		end = false;
//		testCube = new ModelInstance((Model) manager.get("Cube.g3db"), 10, 2.7f, 80);
//		testCube.transform.scl(10f);
//		testHitbox = new Hitbox(2.7f, 2.7f);
//		testHitbox.setPosition(testCube.transform.getTranslation(temp).x, testCube.transform.getTranslation(temp).z);
		playerHitbox = new Hitbox(2.6f, 2.6f);
		playerHitbox.setPosition(player.transform.getTranslation(temp).x, player.transform.getTranslation(temp).y);
//		test.get(i).setPosition(floor[row][2].transform.getTranslation(temp).x, floor[row][2].transform.getTranslation(temp).y);
		Gdx.input.setInputProcessor(player);
		cam.lookAt(player.transform.getTranslation(temp));
		map = new Map(this);
		map.set();
		onFirstRow = true;
//		map.print(map.cubes);
		whiteLight = new DirectionalLight();
		yellowLight = new DirectionalLight();
		environment = new Environment();
		environment.add(whiteLight.set(Color.WHITE, temp.set(.5f, -1f, -1f)));
		environment.add(yellowLight.set(Color.WHITE, temp.set(-.5f, 1f, 1f)));
//		lit = new SpotLight();
//		environment.add(lit.set(Color.WHITE, temp.set(0, -1f, 1f), Vector3.Z, 1, 90, 0));
//		whiteLight.set
//		Light lig = new Light();
		menu = new ModelInstance((Model) manager.get("menu.obj"));
//		menu.transform.setToRotation(Vector3.Y, 180);
//		menu.transform.rotate(Vector3.Z, 180);
//		menu.transform.rotate(Vector3.Z, 180);
//		menu.transform.rotate(Vector3.Y, -90);
		menu.transform.rotate(Vector3.Y, 180);
		menu.transform.rotate(Vector3.Z, -180);
		menu.transform.rotate(Vector3.X, 20);
	}

	@Override
	public void render()
	{
		super.render();
//		System.out.println(player.transform.getTranslation(temp).z);
//		lit.setTarget(player.oldPosition);
//		System.out.println(testCube.transform.getTranslation(temp).x);
//		System.out.println(player.transform.getTranslation(temp).x);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(232/255f, 244/255f, 248/255f, 1);
		batch.begin(cam);
//		batch.render(renderableProviders);
//		batch.render(testGround);
		batch.render(player);
//		batch.render(testCube);
		// batch.render(sky);
		// batch.render(sky2);
		if (player.transform.getTranslation(temp).z >= playerZ)
		{
			playerZ += 100;
			map.update();
//			if (playerZ != 100)
//			{
//				System.out.println("update");
//				map.updateCubes();
//			}
		}

		if (beginning)
		{
			menu.transform.setTranslation(0, 87, player.transform.getTranslation(temp).z + 65);
			batch.render(menu);
			map.drawFloor(environment, 2);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				beginning = false;
				middle = true;
//				player.transform.setToTranslation(0, 2, 0);
//				player.transform.getTranslation(temp);
//				player.oldPosition = temp;
//				player.hitbox.setPosition(player.position.x, player.position.z);
//				
				player.rotation.set(0, 0, 0, 0);
				player.position.set(0, 2, 0);
//				player.transform.set(temp.set(0, 2, 0), player.rotation);
				
				player.transform.getRotation(player.oldRotation).slerp(player.rotation, .1f);
				player.oldPosition.lerp(player.position, .7f);
				player.transform.set(player.oldPosition, player.oldRotation);
				player.hitbox.setPosition(player.position.x, player.position.z);
				
				
				for (int row = 2; row >= 0; row--)
				{
					map.floor[row][0].transform.setToTranslation(100, 0, row * 100);
					map.floor[row][1].transform.setToTranslation(-0, 0, row * 100); 					
					map.floor[row][2].transform.setToTranslation(-100, 0, row * 100);
				}
				playerZ = 100;
				playerZCubes1 = 235;
				playerZCubes2 = 355;
				onFirstRow = true;
				onSecondRow = false;
			}
		}
//		map.drawSky();
//		menu.transform.rotate(Vector3.X, 1);
		//339
//		menu.transform.setToTranslation(0, 0, 1);
		batch.end();
		cam.position.set(player.transform.getTranslation(temp).add(0, 5, -5));
		cam.lookAt(player.transform.getTranslation(temp));
		cam.rotate(temp.set(1, 0, 0), -25);
		cam.update();
		player.update(Gdx.graphics.getDeltaTime(), map);
		if (middle)
		{
			map.drawCubes(environment);
			map.drawFloor(environment, 1);
			
			if (player.transform.getTranslation(temp).z >= playerZCubes1)
			{
				playerZCubes1 += 225;
				onSecondRow = true;
				onFirstRow = false;
				map.updateFirstRow();
			}
			
			if (player.transform.getTranslation(temp).z >= playerZCubes2)
			{
				playerZCubes2 += 225;
				onSecondRow = false;
				onFirstRow = true;
				map.updateSecondRow();
			}
			
			if (onSecondRow)
			{
				for (int row = 0; row < 40; row++)
				{
					for (int col = 0; col < 7; col++)
					{
						if (map.cubeHitboxes[row][col] != null)
						{
							if (player.hitbox.didCollide(map.cubeHitboxes[row][col]))
							{
								this.setScreen(new EndScreen());
								Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
								Gdx.gl.glClearColor(1, 1, 1, 1);
								middle = false;
							}
						}
					}
				}
			}
			if (onFirstRow)
			{
				for (int row = 40; row < 80; row++)
				{
					for (int col = 0; col < 7; col++)
					{
						if (map.cubeHitboxes[row][col] != null)
						{
							if (player.hitbox.didCollide(map.cubeHitboxes[row][col]))
							{
								this.setScreen(new EndScreen());
								Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
								Gdx.gl.glClearColor(1, 1, 1, 1);
								middle = false;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		manager.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}