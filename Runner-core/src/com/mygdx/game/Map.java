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
	int zOfFirstRow = 25;
	
	public Map(Runner runner) {
		main = runner;
		seed = new Random();
		floor = new ModelInstance[3][3];
//		cubes = new ModelInstance[50][50];
//		seed.nextInt(10) + 10
	}

	public void set() {
		for (int row = 2; row >= 0; row--) {
			floor[row][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 30, 0, zOfFirstRow);
			floor[row][1] = new ModelInstance((Model) main.manager.get("ground.obj"), -40, 0, zOfFirstRow);
			floor[row][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -110, 0, zOfFirstRow);
			zOfFirstRow += 25;
			//floors are 30 distance away in z plane
		}
		zOfFirstRow = 25;
//		groundLeft = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), 16, 0, 25);
//		groundMid = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), -14, 0, 25);
//		groundRight = new ModelInstance ( (Model) Runner.manager.get("ground.obj"), -45, 0, 25);
	}
	
	public void drawCubes() {
		for (ModelInstance[] rows: floor) {
			for (ModelInstance floor: rows) {
				main.batch.render(floor);
			}
		}
//		main.batch.render(groundLeft);
//		Runner.batch.render(groundMid);
//		Runner.batch.render(groundRight);
	}
	
	public void update() {
		floor[2][0] = null;
		floor[2][1] = null;
		floor[2][2] = null;
		
		zOfFirstRow += 25;
		
		ModelInstance[] temp = new ModelInstance[3];
		for (int i = 0; i <=2; i++) {
			temp[i] = floor[1][i];
		}
		for (int col = 0; col <=2; col++) {
			floor[1][col] = floor[0][col];
			floor[2][col] = temp[col];
		}
		
		floor[0][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 30, 0, zOfFirstRow + 50);
		floor[0][1] = new ModelInstance((Model) main.manager.get("ground.obj"), -40, 0, zOfFirstRow + 50);
		floor[0][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -110, 0, zOfFirstRow + 50);	
	}
}
