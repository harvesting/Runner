package com.mygdx.game;

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
	private float playerZ;
//	private float playerZCubes1;
//	private float playerZCubes2;
//	private boolean onFirstRow;
//	private boolean onSecondRow;
	
	public MenuScreen(Runner game)
	{
		this.game = game;
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		Gdx.input.setInputProcessor(game.player);
		game.map.set();
		playerZ = 100;
//		playerZCubes1 = 235;
//		playerZCubes2 = 355;
		menu = new ModelInstance((Model) game.manager.get("menu.obj"));
		menu.transform.rotate(Vector3.Y, 180);
		menu.transform.rotate(Vector3.Z, -180);
		menu.transform.rotate(Vector3.X, 20);
//		onFirstRow = true;
	}
	
	@Override
	public void show() 
	{
		
	}

	@Override
	public void render(float delta) 
	{	
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		game.batch.begin(game.cam);
		game.batch.render(game.player);
		game.map.drawFloor();
		game.batch.render(menu);
		game.batch.end();
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
//			player.transform.setToTranslation(0, 2, 0);
//			player.transform.getTranslation(temp);
//			player.oldPosition = temp;
//			player.hitbox.setPosition(player.position.x, player.position.z);
//			
			game.player.rotation.set(0, 0, 0, 0);
			game.player.position.set(0, 2, 0);
//			player.transform.set(temp.set(0, 2, 0), player.rotation);
			
			game.player.transform.getRotation(game.player.oldRotation).slerp(game.player.rotation, .1f);
			game.player.oldPosition.lerp(game.player.position, .7f);
			game.player.transform.set(game.player.oldPosition, game.player.oldRotation);
			game.player.hitbox.setPosition(game.player.position.x, game.player.position.z);
			
			
			for (int row = 2; row >= 0; row--)
			{
				game.map.floor[row][0].transform.setToTranslation(100, 0, row * 100);
				game.map.floor[row][1].transform.setToTranslation(-0, 0, row * 100); 					
				game.map.floor[row][2].transform.setToTranslation(-100, 0, row * 100);
			}
			playerZ = 100;
//			playerZCubes1 = 235;
//			playerZCubes2 = 355;
//			onFirstRow = true;
//			onSecondRow = false;
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
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
		menu.transform.setTranslation(0, 87, game.player.transform.getTranslation(temp).z + 65);
		game.cam.position.set(game.player.transform.getTranslation(temp).add(0, 5, -5));
		game.cam.lookAt(game.player.transform.getTranslation(temp));
		game.cam.rotate(temp.set(1, 0, 0), -25);
		game.cam.update();
		game.player.update(Gdx.graphics.getDeltaTime(), game.map, 0);
		
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

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
	}
}
