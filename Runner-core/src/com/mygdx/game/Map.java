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
	private ModelInstance[] tempArray;
	
	public Map(Runner runner) {
		main = runner;
		seed = new Random();
		floor = new ModelInstance[3][3];
		tempArray = new ModelInstance[3];
	}

	public void set() {
		for (int row = 2; row >= 0; row--) {
			floor[row][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 100, 0, 25);
			floor[row][1] = new ModelInstance((Model) main.manager.get("ground.obj"), 0, 0, 25);
			floor[row][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -100, 0, 25);
			//floors are 30 distance away in z plane
		}
	}
	
	public void drawFloor() {
		for (ModelInstance[] rows: floor) {
			for (ModelInstance floor: rows) {
				main.batch.render(floor);
			}
		}
	}
	
	public void update() {	
		for (ModelInstance[] rows: floor) {
			for (ModelInstance ground: rows) {
				ground.transform.translate(0, 0, 25);
			}
		}
	}
}
