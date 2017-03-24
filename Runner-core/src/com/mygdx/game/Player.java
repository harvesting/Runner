package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Player extends ModelInstance{
	Model model;
	public Player(Model model) {
		super(model);
		this.model = model;
	}
	
	public void checkForInput() {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			System.out.println("cool shits");
		}
	}
}
