package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Player extends ModelInstance{
	
	Vector3 newPosition = new Vector3(1, 0, 0);
	
	public Player(Model model) {
		super(model);
		
	}
	
	public void checkForInput() {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A) ) {
			this.transform.translate(newPosition);
			Runner.cam.position.x += 3;
		}
	}
}
