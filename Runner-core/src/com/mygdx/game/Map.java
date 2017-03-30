package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Map {
	Random seed;
	Vector3 temp = new Vector3();
	Vector3 mapVelocity;
	ModelInstance cube;
	ModelInstance groundLeft, groundMid, groundRight;
	ModelInstance[][] floor;
	Runner main;
	
	public Map(Runner runner) {
		main = runner;
		seed = new Random();
		floor = new ModelInstance[3][3];
//		cubes = new ModelInstance[50][50];
//		seed.nextInt(10) + 10
	}

	public void set() {
		for(int row = 2; row >= 0; row--) {
			floor[row][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 16, 0, 25);
			floor[row][1] = new ModelInstance((Model) main.manager.get("ground.obj"), -14, 0, 25);
			floor[row][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -45, 0, 25);
		}
//		groundLeft = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), 16, 0, 25);
//		groundMid = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), -14, 0, 25);
//		groundRight = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), -45, 0, 25);
	}
	
	public void update(float deltaTime) {
		for(ModelInstance[] rows: floor) {
			for(ModelInstance cube: rows) {
				main.batch.render(cube);
			}
		}
//		main.batch.render(groundLeft);
//		Runner.batch.render(groundMid);
//		Runner.batch.render(groundRight);
	}
}
