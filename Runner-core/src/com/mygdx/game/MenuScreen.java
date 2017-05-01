package com.mygdx.game;

/**
 * This class is the menu screen.
 * 
 * @author rafaelrajardo
 * 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen
{
	Runner game;
	Vector3 temp = new Vector3();
	ModelInstance menu;
	float playerZ;
	
	public MenuScreen(Runner game)
	{
		this.game = game;
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		Gdx.input.setInputProcessor(game.player);
		game.map.set();
		playerZ = 100;
		menu = new ModelInstance((Model) game.manager.get("menu.obj"));
		menu.transform.rotate(Vector3.Y, 180);
		menu.transform.rotate(Vector3.Z, -180);
		menu.transform.rotate(Vector3.X, 20);
		game.cam.fieldOfView = 75;
	}

	@Override
	public void render(float delta) 
	{	
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		draw();
		update();
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			reset();
			game.setScreen(game.game);
		}
		
		if (game.player.transform.getTranslation(temp).z >= playerZ)
		{
			playerZ += 100;
			game.map.updateFloor();
		}
	}
	
	/**
	 * Renders player, floor, and menu.
	 */
	public void draw()
	{
		game.batch.begin(game.cam);
		game.batch.render(game.player);
		game.map.drawFloor();
		game.batch.render(menu);
		game.batch.end();
	}
	
	/**
	 * Updates player, camera, and menu positions.
	 */
	public void update()
	{
		menu.transform.setTranslation(0, 87, game.player.transform.getTranslation(temp).z + 65);
		game.cam.position.set(game.player.transform.getTranslation(temp).add(0, 5, -5));
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		game.cam.rotate(temp.set(1, 0, 0), -25);
		game.cam.update();
		game.player.update(Gdx.graphics.getDeltaTime(), 0);
	}
	
	/**
	 * Resets player and floor locations.
	 */
	public void reset()
	{
		game.player.rotation.set(0, 0, 0, 0);
		game.player.position.set(0, 2, 0);			
		game.player.transform.getRotation(game.player.oldRotation).set(game.player.rotation);
		game.player.oldPosition.set(game.player.position);
		game.player.transform.set(game.player.oldPosition, game.player.oldRotation);
		game.player.hitbox.setPosition(game.player.position.x, game.player.position.z);
		
		for (int row = 2; row >= 0; row--)
		{
			game.map.floor[row][0].transform.setToTranslation(100, 0, row * 100);
			game.map.floor[row][1].transform.setToTranslation(-0, 0, row * 100); 					
			game.map.floor[row][2].transform.setToTranslation(-100, 0, row * 100);
		}
		
		playerZ = 100;
		game.cam.fieldOfView = 65;
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
