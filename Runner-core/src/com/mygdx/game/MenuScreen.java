package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;

public class MenuScreen implements Screen
{
	final Runner game;
	OrthographicCamera cam;
	SpriteBatch batch;
	
	public MenuScreen(final Runner game)
	{
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 600);
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		batch.begin();
		batch.draw(new Texture("badlogic.jpg"), 0, 0);
		batch.end();
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
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
