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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen
{
	Runner game;
	private Vector3 temp = new Vector3();
	private ModelInstance testCube;
	private ModelInstance sky;
	private ModelInstance sky2;
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
	SpriteBatch batch;
	Texture score;
	int scoreInt;
	
	public GameScreen(Runner game)
	{
		this.game = game;
		batch = new SpriteBatch();
//		testCube = new ModelInstance((Model) manager.get("Cube.g3db"), 10, 2.7f, 80);
//		testCube.transform.scl(10f);
//		testHitbox = new Hitbox(2.7f, 2.7f);
//		testHitbox.setPosition(testCube.transform.getTranslation(temp).x, testCube.transform.getTranslation(temp).z);
		playerHitbox = new Hitbox(2.6f, 2.6f);
		playerHitbox.setPosition(game.player.transform.getTranslation(temp).x, game.player.transform.getTranslation(temp).y);
//		test.get(i).setPosition(floor[row][2].transform.getTranslation(temp).x, floor[row][2].transform.getTranslation(temp).y);
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
//		menu.transform.setToRotation(Vector3.Y, 180);
//		menu.transform.rotate(Vector3.Z, 180);
//		menu.transform.rotate(Vector3.Z, 180);
//		menu.transform.rotate(Vector3.Y, -90);
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().scale(.15f);
		score = new Texture("Score.png");
		scoreInt = 0;
//		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
//		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
//		parameter.size = 12;
//		BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
//		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(232/255f, 244/255f, 248/255f, 1);
		game.batch.begin(game.cam);
		game.map.drawCubes(environment);
		game.map.drawFloor();
		game.batch.render(game.player);
		game.batch.end();
		batch.begin();
		scoreInt = (int) game.player.transform.getTranslation(temp).z / 10;
//		(int) game.player.transform.getTranslation(temp).z
		batch.draw(score, 0, 0);
		font.draw(batch, Integer.toString(scoreInt), 70, 462);
		batch.end();
//		batch.render(testCube);
		// batch.render(sky);
		// batch.render(sky2);
		if (game.player.transform.getTranslation(temp).z >= playerZ)
		{
			playerZ += 100;
			game.map.update();
//			if (playerZ != 100)
//			{
//				System.out.println("update");
//				map.updateCubes();
//			}
		}
//		map.drawSky();
//		menu.transform.rotate(Vector3.X, 1);
		//339
//		menu.transform.setToTranslation(0, 0, 1);
		game.cam.position.set(game.player.transform.getTranslation(temp).add(0, 5, -5));
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		game.cam.rotate(temp.set(1, 0, 0), -25);
		game.cam.update();
		game.player.update(Gdx.graphics.getDeltaTime(), game.map, 1);
			
		if (game.player.transform.getTranslation(temp).z >= playerZCubes1)
		{
			playerZCubes1 += 225;
			onSecondRow = true;
			onFirstRow = false;
			game.map.updateFirstRow();
		}
		
		if (game.player.transform.getTranslation(temp).z >= playerZCubes2)
		{
			playerZCubes2 += 225;
			onSecondRow = false;
			onFirstRow = true;
			game.map.updateSecondRow();
		}
			
		if (onSecondRow)
		{
			for (int row = 0; row < 40; row++)
			{
				for (int col = 0; col < 7; col++)
				{
					if (game.map.cubeHitboxes[row][col] != null)
					{
						if (game.player.hitbox.didCollide(game.map.cubeHitboxes[row][col]))
						{
//							this.setScreen(new EndScreen());
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
					if (game.map.cubeHitboxes[row][col] != null)
					{
						if (game.player.hitbox.didCollide(game.map.cubeHitboxes[row][col]))
						{
//							this.setScreen(new EndScreen());
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
		game.batch.dispose();
		game.manager.dispose();
	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		
	}
}
