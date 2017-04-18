package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Runner extends ApplicationAdapter
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
	private float playerZ = 100;
	private float playerZCubes1 = 235;
		//235
		//355
		// r * 3f
		//playerZCubes1 += 225; for both
	private float playerZCubes2 = 355;
//	Hitbox testHitbox;
	Hitbox playerHitbox;
	private boolean onSecondRow;
	private boolean onFirstRow;

	@Override
	public void create()
	{
		manager = new AssetManager();
		manager.load("ship.obj", Model.class);
		manager.load("CubeBlue.obj", Model.class);
		manager.load("ground.obj", Model.class);
		manager.load("sky.obj", Model.class);
		manager.finishLoading();
		batch = new ModelBatch();
		cam = new PerspectiveCamera(69, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 5, -5);
		cam.near = .1f;
		cam.far = 100f;
		player = new Player((Model) manager.get("ship.obj"));
		sky = new ModelInstance((Model) manager.get("sky.obj"), 50, 20, 40);
		sky2 = new ModelInstance((Model) manager.get("sky.obj"), -50, 20, 40);
		sky.transform.rotate(Vector3.X, 100);
		sky2.transform.rotate(Vector3.X, 100);
//		testCube = new ModelInstance((Model) manager.get("Cube.obj"), 10, 2.7f, 80);
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
	}

	@Override
	public void render()
	{
//		System.out.println(testCube.transform.getTranslation(temp).x);
//		System.out.println(player.transform.getTranslation(temp).x);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		batch.begin(cam);
//		batch.render(testGround);
		batch.render(player);
//		batch.render(testCube);
		// batch.render(sky);
		// batch.render(sky2);
		map.drawFloor();
		batch.end();
		cam.position.set(player.transform.getTranslation(temp).add(0, 5, -5));
		cam.lookAt(player.transform.getTranslation(temp));
		cam.rotate(temp.set(1, 0, 0), -25);
		cam.update();
		player.update(Gdx.graphics.getDeltaTime());
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
//		
//		for (Hitbox[] row: map.cubeHitboxes)
//		{
//			for (Hitbox box: row)
//			{
//				if (box != null)
//				{
//					if (player.hitbox.didCollide(box))
//					{ 
//						System.out.println("End");
//					}
//				}
//			}
//		}
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
							System.out.println("End");
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
							System.out.println("End");
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

}