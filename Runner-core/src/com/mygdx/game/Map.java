package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Map
{
	Random seed;
	Vector3 temp = new Vector3();
	Vector3 mapVelocity;
	ModelInstance cube;
	ModelInstance groundLeft, groundMid, groundRight;
	ModelInstance sky, sky2, sky3;
	ModelInstance[][] floor;
	Runner main;
	int zOfFirstRow = 25;
	Hitbox[][] cubeHitboxes;
	ModelInstance[][] cubes;
	ModelInstance[][] test = new ModelInstance[25][25];
	private int difficulty;
	int lastRow;
	
	public Map(Runner runner)
	{
		main = runner;
		seed = new Random();
		sky = new ModelInstance((Model)main.manager.get("sky.obj"), 100, 15 , 30);
		sky2 = new ModelInstance((Model)main.manager.get("sky.obj"), 0, 15 , 30);
		sky3 = new ModelInstance((Model)main.manager.get("sky.obj"), -100, 15, 30);
		sky.transform.rotate(Vector3.X, 90);
		sky2.transform.rotate(Vector3.X, 90);
		sky3.transform.rotate(Vector3.X, 90);
		floor = new ModelInstance[3][3];
		cubeHitboxes = new Hitbox[30][30];
		cubes = new ModelInstance[30][30];
		difficulty = 5;
		lastRow = 100;
	}
	
	public void drawSky()
	{
		main.batch.render(sky);
		main.batch.render(sky2);
		main.batch.render(sky3);
	}
	
	
	public void set()
	{
		for (int row = 2; row >= 0; row--)
		{
			floor[row][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 100, 0, row * 100);
			
			floor[row][1] = new ModelInstance((Model) main.manager.get("ground.obj"), 0, 0, row * 100);
			
			floor[row][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -100, 0, row * 100);
		}
		
		
//		int x = 75;
//		int z = 150;
//		for (int r = 0; r < 10; r++) 
//		{
//			for (int c = 0; c < 30; c++) 
//			{
////				cubeHitboxes[r][c] = new Hitbox(r - (scl * 3), 2.7f, z);
////				cubes[r][c] = new ModelInstance((Model) main.manager.get("Cube.obj"), x, 2.7f, z);
//				x -= 5;
////				cubes[seed.nextInt(24)][seed.nextInt(24)] = new ModelInstance((Model) main.manager.get("Cube.obj"), seed.nextInt(200) - 100, 2.7f, z);
//			}
//			z -= 8;
//			x = 75;
//		}
		int z = 100;
		for (int i = 0; i < difficulty; i++) 
		{
			for (int x = 0; x < difficulty; x++) 
			{
				cubes[seed.nextInt(29)][seed.nextInt(29)] = new ModelInstance((Model) main.manager.get("Cube.obj"), seed.nextInt(200) - 100, 2.7f, z);
			}
			z -= 5;
		}	
	}
	
	public void print(ModelInstance[][] array) 
	{
		for (ModelInstance[] row: array)
		{
			for (ModelInstance cube: row)
			{
				System.out.print(cube + ", ");
			}
			System.out.println();
		}
	}
	
	public void drawFloor()
	{
//		print(cubes);
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance floor : rows)
			{
				main.batch.render(floor);
			}
		}
		
		for (ModelInstance[] row : cubes)
		{
			for (ModelInstance cube : row)
			{
				if (cube != null)
				{
					main.batch.render(cube);
				}
			}
		}
	}

	public void update()
	{
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance ground : rows)
			{
				ground.transform.translate(0, 0, 100);
			}
		}
		
	}
	public void updateCubes() {
		for (int i = 0; i < 30; i++) 
		{
			if (cubes[29][i] != null)
			{
				lastRow += 25;
				cubes[29][i].transform.setTranslation(temp.set(seed.nextInt(200) - 100, 2.7f, lastRow));
			}
		}
	}
}
