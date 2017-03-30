package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Map {
	ModelInstance[][] cubes;
	Random seed;
	Vector3 temp = new Vector3();
	Vector3 mapVelocity;
	ModelInstance cube;
	Runner main = new Runner();
	
	public Map() {
		seed = new Random();
//		cubes = new ModelInstance[50][50];
//		seed.nextInt(10) + 10
		mapVelocity = new Vector3(-.1f, 0, 0);
	}
	
	public void set() {
		for(int row = 0; row < 50; row++) {
			for(int col = 0; col < 50; col++) {
				cubes[row][col] = cube;
			}
		}
	}
	
	public void update(float deltaTime) {
//		for(ModelInstance[] rows: cubes) {
//			for(ModelInstance cube: rows) {
//				main.batch.render(cube);
//			}
//		}
	}
}
