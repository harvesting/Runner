package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Map extends ModelInstance{
	ModelInstance[] cubes;
	Random seed;
	Vector3 temp = new Vector3();
	Vector3 mapVelocity;
	
	public Map(Model cube) {
		super(cube);
		seed = new Random();
		cubes = new ModelInstance[seed.nextInt(10) + 10];
		mapVelocity = new Vector3(-.1f, 0, 0);
	}
	
	public void update(float deltaTime) {
		for(ModelInstance cube: cubes) {
			cube.transform.scl(temp.set(mapVelocity).scl(deltaTime));
		}
	}
}
