package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * The screen used for when the game ends.
 * 
 * @author rafaelfajardo
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
	
	public EndScreen(GameScreen gameScreen, Runner game)
	{
		this.gameScreen = gameScreen;
		this.game = game;
		batch = new SpriteBatch();
		pic = new Texture("lose-screen.png");
		font = new BitmapFont(Gdx.files.internal("Tekton.fnt"), Gdx.files.internal("Tekton.png"), false);
		font.setColor(Color.WHITE);
		temp = new Vector3();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		// Draw ending screen and score
		batch.begin();
		batch.draw(pic, 75, 75);
		font.draw(batch, Integer.toString(gameScreen.scoreInt), 398, 198);
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			// Reset positions of player, map, and fence, and then set screen to menu
			game.cam.fieldOfView = 75;
			game.player.rotation.set(0, 0, 0, 0);
			game.player.position.set(0, 2, 0);			
			game.player.transform.getRotation(game.player.oldRotation).set(game.player.rotation);
			game.player.oldPosition.set(game.player.position);
			game.player.transform.set(game.player.oldPosition, game.player.oldRotation);
			game.player.hitbox.setPosition(game.player.position.x, game.player.position.z);
			game.map.fenceForeground.transform.setToTranslation(temp.set(0, 0, 110));
			game.map.fenceBackground.transform.setToTranslation(temp.set(0, 0, 210));
			
			for (int row = 2; row >= 0; row--)
			{
				game.map.floor[row][0].transform.setToTranslation(100, 0, row * 100);
				game.map.floor[row][1].transform.setToTranslation(-0, 0, row * 100); 					
				game.map.floor[row][2].transform.setToTranslation(-100, 0, row * 100);
			}
			
			game.setScreen(game.menu);
		}
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
