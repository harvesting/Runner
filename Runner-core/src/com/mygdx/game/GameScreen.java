package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.math.Vector3;

/**
 * The screen that runs the game.
 * 
 * @author liaderlich
 *
 */
public class GameScreen implements Screen
{
	Runner game;
	private ModelInstance level1Sign1;
	private ModelInstance level1Sign2;
	private ModelInstance level1Sign3;
	private ModelInstance level2Sign1;
	private ModelInstance level2Sign2;
	private ModelInstance level2Sign3;
	private ModelInstance level3Sign1;
	private ModelInstance level3Sign2;
	private ModelInstance level3Sign3;
	private ModelInstance level4Sign1;
	private ModelInstance level4Sign2;
	private ModelInstance level4Sign3;
	private ModelInstance level5Sign1;
	private ModelInstance level5Sign2;
	private ModelInstance level5Sign3;
	private ModelInstance level6Sign1;
	private ModelInstance level6Sign2;
	private ModelInstance level6Sign3;
	
	float playerZ = 100;
	float playerZCubes1 = 235;
	float playerZCubes2 = 355;
	private boolean onSecondRow;
	private boolean onFirstRow;
	boolean playerSpeedNeedsUpdate;
	private boolean redSpeedNeedsUpdate;
	private boolean yellowSpeedNeedsUpdate;
	private boolean orangeSpeedNeedsUpdate;
	private boolean purpleSpeedNeedsUpdate;
	private boolean blueSpeedNeedsUpdate;
	private Vector3 temp;
	int scoreInt;
	int scale = 0;
	String cube = "";
	
	Hitbox playerHitbox;
	Environment environment;
	DirectionalLight whiteLight;
	DirectionalLight yellowLight;
	SpotLight lit;
	BitmapFont font;
	SpriteBatch batch;
	Texture score;
	
	public GameScreen(Runner game)
	{
		this.game = game;
		batch = new SpriteBatch();
		temp = new Vector3();
		playerHitbox = new Hitbox(2.6f, 2.6f);
		playerHitbox.setPosition(game.player.transform.getTranslation(temp).x, game.player.transform.getTranslation(temp).y);
		onFirstRow = true;
		whiteLight = new DirectionalLight();
		yellowLight = new DirectionalLight();
		environment = new Environment();
		environment.add(whiteLight.set(Color.WHITE, temp.set(.5f, -1f, -1f)));
		environment.add(yellowLight.set(Color.WHITE, temp.set(-.5f, 1f, 1f)));
		
		level1Sign1 = new ModelInstance((Model) game.manager.get("Level1.obj"), 0, 13, 110);
		level1Sign1.transform.rotate(Vector3.Y, 180);
		level1Sign1.transform.rotate(Vector3.Z, 180);
		level1Sign2 = new ModelInstance((Model) game.manager.get("Level1.obj"), -40, 13, 110);
		level1Sign2.transform.rotate(Vector3.Y, 180);
		level1Sign2.transform.rotate(Vector3.Z, 180);
		level1Sign3 = new ModelInstance((Model) game.manager.get("Level1.obj"), 40, 13, 110);
		level1Sign3.transform.rotate(Vector3.Y, 180);
		level1Sign3.transform.rotate(Vector3.Z, 180);
		
		level2Sign1 = new ModelInstance((Model) game.manager.get("Level2.obj"), 0, 13, 570);
		level2Sign1.transform.rotate(Vector3.Y, 180);
		level2Sign1.transform.rotate(Vector3.Z, 180);
		level2Sign2 = new ModelInstance((Model) game.manager.get("Level2.obj"), -40, 13, 570);
		level2Sign2.transform.rotate(Vector3.Y, 180);
		level2Sign2.transform.rotate(Vector3.Z, 180);
		level2Sign3 = new ModelInstance((Model) game.manager.get("Level2.obj"), 40, 13, 570);
		level2Sign3.transform.rotate(Vector3.Y, 180);
		level2Sign3.transform.rotate(Vector3.Z, 180);
		
		level3Sign1 = new ModelInstance((Model) game.manager.get("Level3.obj"), 0, 13, 1460);
		level3Sign1.transform.rotate(Vector3.Y, 180);
		level3Sign1.transform.rotate(Vector3.Z, 180);
		level3Sign2 = new ModelInstance((Model) game.manager.get("Level3.obj"), -40, 13, 1460);
		level3Sign2.transform.rotate(Vector3.Y, 180);
		level3Sign2.transform.rotate(Vector3.Z, 180);
		level3Sign3 = new ModelInstance((Model) game.manager.get("Level3.obj"), 40, 13, 1460);
		level3Sign3.transform.rotate(Vector3.Y, 180);
		level3Sign3.transform.rotate(Vector3.Z, 180);
		
		level4Sign1 = new ModelInstance((Model) game.manager.get("Level4.obj"), 0, 13, 2600);
		level4Sign1.transform.rotate(Vector3.Y, 180);
		level4Sign1.transform.rotate(Vector3.Z, 180);
		level4Sign2 = new ModelInstance((Model) game.manager.get("Level4.obj"), -40, 13, 2600);
		level4Sign2.transform.rotate(Vector3.Y, 180);
		level4Sign2.transform.rotate(Vector3.Z, 180);
		level4Sign3 = new ModelInstance((Model) game.manager.get("Level4.obj"), 40, 13, 2600);
		level4Sign3.transform.rotate(Vector3.Y, 180);
		level4Sign3.transform.rotate(Vector3.Z, 180);
		
		level5Sign1 = new ModelInstance((Model) game.manager.get("Level5.obj"), 0, 13, 4170);
		level5Sign1.transform.rotate(Vector3.Y, 180);
		level5Sign1.transform.rotate(Vector3.Z, 180);
		level5Sign2 = new ModelInstance((Model) game.manager.get("Level5.obj"), -40, 13, 4170);
		level5Sign2.transform.rotate(Vector3.Y, 180);
		level5Sign2.transform.rotate(Vector3.Z, 180);
		level5Sign3 = new ModelInstance((Model) game.manager.get("Level5.obj"), 40, 13, 4170);
		level5Sign3.transform.rotate(Vector3.Y, 180);
		level5Sign3.transform.rotate(Vector3.Z, 180);
		
		level6Sign1 = new ModelInstance((Model) game.manager.get("Level6.obj"), 0, 13, 5750);
		level6Sign1.transform.rotate(Vector3.Y, 180);
		level6Sign1.transform.rotate(Vector3.Z, 180);
		level6Sign2 = new ModelInstance((Model) game.manager.get("Level6.obj"), -40, 13, 5750);
		level6Sign2.transform.rotate(Vector3.Y, 180);
		level6Sign2.transform.rotate(Vector3.Z, 180);
		level6Sign3 = new ModelInstance((Model) game.manager.get("Level6.obj"), 40, 13, 5750);
		level6Sign3.transform.rotate(Vector3.Y, 180);
		level6Sign3.transform.rotate(Vector3.Z, 180);
		
		font = new BitmapFont(Gdx.files.internal("Tekton.fnt"), Gdx.files.internal("Tekton.png"), false);
		font.setColor(Color.WHITE);
		font.getData().scale(.03f);
		score = new Texture("Score.png");
		scoreInt = 0;
		
		playerSpeedNeedsUpdate = false;
		redSpeedNeedsUpdate = true;
		blueSpeedNeedsUpdate = true;
		yellowSpeedNeedsUpdate = true;
		orangeSpeedNeedsUpdate = true;
		purpleSpeedNeedsUpdate = true;
		
		game.map.set();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(232/255f, 244/255f, 248/255f, 1);
		draw();
		renderSigns();

		game.cam.position.set(game.player.transform.getTranslation(temp).add(0, 5, -5));
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		game.cam.rotate(temp.set(1, 0, 0), -25);
		game.cam.update();
		game.player.update(Gdx.graphics.getDeltaTime(), 1);

		// Updates first row and sets new cubes
		if (game.player.transform.getTranslation(temp).z >= playerZCubes1)
		{
			playerZCubes1 += 225;
			onSecondRow = true;
			onFirstRow = false;
			setCubes();
			game.map.updateFirstSet(cube);
		}
	
		// Updates player speed
		if (playerSpeedNeedsUpdate)
		{
			if (game.player.transform.getTranslation(temp).z > game.map.lastRow - (79 * 3) - 15)
			{
				game.player.updateForwardSpeed(6);
				game.player.updateLeftRightSpeed(6);
				playerSpeedNeedsUpdate = false;
			}
		}
		
		// Updates map and fence
		if (game.player.transform.getTranslation(temp).z >= playerZ)
		{
			playerZ += 100;
			game.map.updateAll();
		}
		
		// Update second rows of cubes
		if (game.player.transform.getTranslation(temp).z >= playerZCubes2)
		{
			playerZCubes2 += 225;
			onSecondRow = false;
			onFirstRow = true;
			game.map.updateSecondSet(cube);
		}
			
		checkCollision();
	}
	
	/**
	 * Sets type of cube depending on score player is on.
	 */
	public void setCubes()
	{
		if (scoreInt < 38)
		{
			cube = "CubeGreen.obj";
		} else if (scoreInt > 38 && scoreInt < 133)
		{
			cube = "CubeYellow.obj";
			scale = 0;	
			if (yellowSpeedNeedsUpdate)
			{
				playerSpeedNeedsUpdate = true;
				yellowSpeedNeedsUpdate = false;
			}
		} else if (scoreInt > 133 && scoreInt < 245) 
		{
			cube = "CubeOrange.obj";
			scale = 0;
			if (orangeSpeedNeedsUpdate)
			{
				playerSpeedNeedsUpdate = true;
				orangeSpeedNeedsUpdate = false;
				game.map.difficulty++;
			}
		} else if (scoreInt > 245 && scoreInt < 393)
		{
			cube = "CubeBlue.obj";
			scale = 0;
			if (blueSpeedNeedsUpdate)
			{
				playerSpeedNeedsUpdate = true;
				blueSpeedNeedsUpdate = false;
			}
		} else if (scoreInt > 393 && scoreInt < 555) 
		{
			cube = "CubeRed.obj";
			scale = 0;
			if (redSpeedNeedsUpdate)
			{
				playerSpeedNeedsUpdate = true;
				redSpeedNeedsUpdate = false;
				game.map.difficulty++;
			}
		} else if (scoreInt > 555)
		{
			cube = "CubePurple.obj";
			scale = 0;
			if (purpleSpeedNeedsUpdate)
			{
				playerSpeedNeedsUpdate = true;
				purpleSpeedNeedsUpdate = false;
			}
		}
	}
	
	/**
	 * Check collision for background or foreground cubes.
	 */
	public void checkCollision()
	{
		// Check collision for background rows of cubes
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
							game.setScreen(new EndScreen(this, game));
						}
					}
				}
			}
		
		}
			
		// Check collision for foreground rows of cubes
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
							game.setScreen(new EndScreen(this, game));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Render signs depending on what score player is on. 
	 */
	public void renderSigns()
	{
		if (scoreInt < 12)
		{
			game.batch.render(level1Sign1);
			game.batch.render(level1Sign2);
				game.batch.render(level1Sign3);
		}
				
		if (scoreInt < 58 && scoreInt > 45)
		{
			game.batch.render(level2Sign1);
			game.batch.render(level2Sign2);
			game.batch.render(level2Sign3);
		}
			
		if (scoreInt < 150 && scoreInt > 133)
		{
			game.batch.render(level3Sign1);
			game.batch.render(level3Sign2);
			game.batch.render(level3Sign3);
		}
			
		if (scoreInt < 265 && scoreInt > 248)
		{
			game.batch.render(level4Sign1);
			game.batch.render(level4Sign2);
			game.batch.render(level4Sign3);
		}
					
		if (scoreInt < 426 && scoreInt > 405)
		{
			game.batch.render(level5Sign1);
			game.batch.render(level5Sign2);
			game.batch.render(level5Sign3);
		}
				
		if (scoreInt < 576 && scoreInt > 560)
		{
			game.batch.render(level6Sign1);
			game.batch.render(level6Sign2);
			game.batch.render(level6Sign3);
		}
	}
	
	/**
	 * Draw all 3d models and score to screen.
	 */
	public void draw()
	{
		game.batch.begin(game.cam);
		game.map.drawCubes(environment);
		game.map.drawFloor();
		game.batch.render(game.player);
		game.batch.end();
		batch.begin();
		scoreInt = (int) game.player.transform.getTranslation(temp).z / 10;
		batch.draw(score, 0, 0);
		font.draw(batch, Integer.toString(scoreInt), 94, 463);
		batch.end();
	}

	@Override
	public void dispose()
	{
		game.batch.dispose();
		game.manager.dispose();
	}

	@Override
	public void show() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}
}
