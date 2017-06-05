package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

/**
 * The screen used for when the game ends.
 * 
 * @author liaderlich
 * 
 */
public class EndScreen implements Screen
{
	private final SpriteBatch batch;
	private final Texture pic;
	private final BitmapFont font;
	private final GameScreen gameScreen;
	private final Runner game;
	private Vector3 temp;
	private Random seed;
	
	public EndScreen(GameScreen gameScreen, Runner game)
	{
		this.gameScreen = gameScreen;
		this.game = game;
		batch = new SpriteBatch();
		pic = new Texture("lose-screen.png");
		font = new BitmapFont(Gdx.files.internal("Tekton.fnt"), Gdx.files.internal("Tekton.png"), false);
		font.setColor(Color.WHITE);
		temp = new Vector3();
		seed = new Random();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		draw();
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			reset();
			game.setScreen(game.menu);
		}
	}
	
	/**
	 * Reset everything to begin from start.
	 */
	public void reset()
	{
		game.cam.fieldOfView = 75;
		game.player.rotation.set(0, 0, 0, 0);
		game.player.position.set(0, 2, 0);			
		game.player.transform.getRotation(game.player.oldRotation).set(game.player.rotation);
		game.player.oldPosition.set(game.player.position);
		game.player.transform.set(game.player.oldPosition, game.player.oldRotation);
		game.player.hitbox.setPosition(game.player.position.x, game.player.position.z);
		game.player.update(Gdx.graphics.getDeltaTime(), 0);
		
		for (int row = 2; row >= 0; row--)
		{
			game.map.floor[row][0].transform.setToTranslation(100, 0, row * 100);
			game.map.floor[row][1].transform.setToTranslation(-0, 0, row * 100); 					
			game.map.floor[row][2].transform.setToTranslation(-100, 0, row * 100);
		}
		
		for (int r = 0; r < 80; r++)
		{
			for (int c = 0; c < 7; c++)
			{
				game.map.cubes[r][c] = null;
				game.map.cubeHitboxes[r][c] = null;
			}
		}
		
		game.map.difficulty = 0;
		game.map.lastRow = 350;
		game.player.velocityForward.set(temp.set(0, 0, 20f));
		game.player.velocityLeftRight.set(temp.set(20f, 0, 0));			
		game.player.speed = 0;
		
		for (int r = 0; r < 80; r++)
		{
			for (int c = 0; c <= game.map.difficulty; c++) //difficulty is how many random cubes per row; max is about 2 to 3
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				game.map.cubes[r][col] = new ModelInstance((Model) game.manager.get("CubeGreen.obj"), temp.set(randX, 2.7f, game.map.lastRow - (r * 3f)));
				game.map.cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				game.map.cubeHitboxes[r][col].setPosition(randX, game.map.lastRow - (r * 3f));
			}
		}
		
		game.menu.playerZ = 100;
		game.game.playerZ = 100;
		game.game.playerZCubes1 = 235;
		game.game.playerZCubes2 = 355;
		game.map.fenceForeground.transform.setToTranslation(temp.set(0, 0, 110));
		game.map.fenceBackground.transform.setToTranslation(temp.set(0, 0, 210));
		game.map.onForeground = true;
	}
	
	/**
	 * Draws end screen.
	 */
	public void draw()
	{		
		batch.begin();
		batch.draw(pic, 75, 75);
		font.draw(batch, Integer.toString(gameScreen.scoreInt), 398, 198);
		batch.end();
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

	@Override
	public void dispose() {}
}
