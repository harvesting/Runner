package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Map
{
	Random seed;
	Vector3 temp = new Vector3();
	Vector3 mapVelocity;
	ModelInstance cube;
	ModelInstance groundLeft, groundMid, groundRight;
	ModelInstance sky;
	ModelInstance[][] floor;
	ModelInstance fenceBackgroundSmall;
	ModelInstance fenceForegroundSmall;
	Runner main;
	int zOfFirstRow = 25;
	Hitbox[][] cubeHitboxes;
	ModelInstance[][] cubes;
	private int difficulty;
	float lastRow;
	float fenceZ;
	private boolean onForeground;
	int level = 0;
	private ModelInstance fenceForegroundBig;
	private ModelInstance fenceBackgroundBig;
	
	public Map(Runner runner)
	{
		main = runner;
		seed = new Random();
		sky = new ModelInstance((Model) main.manager.get("sky.obj"), 0, 40, 90);
//		sky.transform.rotate(rotation.set(Vector3.Y, 90));
//		sky.transform.set(rotation.set(Vector3.Y, 90));
		sky.transform.rotate(Vector3.X, 90);
		sky.transform.rotate(Vector3.Y, 180);
		sky.transform.rotate(Vector3.X, 180);
		sky.transform.rotate(Vector3.Z, 90);
		sky.transform.rotate(Vector3.X, 90);
//		sky.transform.setToRotation(Vector3.X, 45);
		floor = new ModelInstance[3][3];
		cubeHitboxes = new Hitbox[80][7];
		cubes = new ModelInstance[80][7];
		fenceForegroundSmall = new ModelInstance((Model) main.manager.get("FenceSmall.obj"), 0, 0, 100);
		fenceBackgroundSmall = new ModelInstance((Model) main.manager.get("FenceSmall.obj"), 0, 0, 200);
		fenceForegroundBig = new ModelInstance((Model) main.manager.get("FenceBig.obj"), 0, 0, 100);
		fenceBackgroundBig = new ModelInstance((Model) main.manager.get("FenceBig.obj"), 0, 0, 200);
		difficulty = 0;
		lastRow = 350;
		fenceZ = 125;
		onForeground = true;
	}
	
	public void drawSky()
	{
		main.batch.render(sky);
	}
	
	public void set()
	{
		for (int row = 2; row >= 0; row--)
		{
			floor[row][0] = new ModelInstance((Model) main.manager.get("ground.obj"), 100, 0, row * 100);
			
			floor[row][1] = new ModelInstance((Model) main.manager.get("ground.obj"), 0, 0, row * 100);
			
			floor[row][2] = new ModelInstance((Model) main.manager.get("ground.obj"), -100, 0, row * 100);
		}
		for (int r = 0; r < 80; r++)
		{
			for (int c = 0; c <= difficulty; c++)
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) main.manager.get("CubeBlue.g3db"), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}
	}
	
	public void print(ModelInstance[][] array) 
	{
		for (ModelInstance[] row: array)
		{
			for (ModelInstance cube: row)
			{
				if (cube == null)
				{
					System.out.print("0 ");
				} else 
				{
					System.out.print("1 ");
				}
			}
			System.out.println();
		}
	}
	
	public void drawFloor(Environment env, int size)
	{
		if (size == 1)
		{
			main.batch.render(fenceForegroundSmall);
			main.batch.render(fenceBackgroundSmall);
		} else if (size == 2)
		{
			main.batch.render(fenceForegroundBig);
			main.batch.render(fenceBackgroundBig);
		}
		
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance floor : rows)
			{
				main.batch.render(floor, env);
			}
		}
	}
	
	public void drawCubes(Environment env)
	{
		for (ModelInstance[] row : cubes)
		{
			for (ModelInstance cube : row)
			{
				if (cube != null)
				{
					main.batch.render(cube, env);
				}
			}
		}
	}

	public void update()
	{
		if (onForeground)
		{
			if (main.beginning)
			{
				fenceForegroundSmall.transform.setTranslation(0, 0, fenceBackgroundSmall.transform.getTranslation(temp).z + 100);
				onForeground = false;
			} 
			
			if (main.middle)
			{
				fenceForegroundSmall.transform.setTranslation(0, 0, fenceBackgroundSmall.transform.getTranslation(temp).z + 100);
				onForeground = false;
			}
		} else 
		{
			if (main.beginning)
			{
				fenceBackgroundBig.transform.setTranslation(0, 0, fenceForegroundSmall.transform.getTranslation(temp).z + 100);
				onForeground = true;
			} 
			
			if (main.middle)
			{
				fenceBackgroundSmall.transform.setTranslation(0, 0, fenceForegroundSmall.transform.getTranslation(temp).z + 100);
				onForeground = true;
			}
		}
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance ground : rows)
			{
				ground.transform.translate(0, 0, 100);
			}
		}
	}
	
	public void updateFirstRow() 
	{	
		for (int r = 40; r < 80; r++)
		{
			for (int c = 0; c < 7; c++)
			{
				cubes[r][c] = null;
				cubeHitboxes[r][c] = null;
			}
		}
//		cubes[r][c].transform.translate(0, 0, 93);
		lastRow += 225;
		for (int r = 40; r < 80; r++)
		{
			for (int c = 0; c <= difficulty; c++)
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) main.manager.get("CubeBlue.g3db"), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}
		
		/*old method*/
//		ModelInstance tempArray[] = cubes[0];
//		for (int i = 0; i < 30; i++) 
//		{
//			if (cubes[29][i] != null)
//			{
//				lastRow += 10;
//				cubes[29][i].transform.setTranslation(temp.set(seed.nextInt(200) - 100, 2.7f, lastRow));
//			}
//		}
//		cubes[0] = cubes[29];
//		for (int r = 29; r > 1; r--)
//		{
//			ModelInstance temp = cubes[r + 1][c];
//			cubes[r] = cubes[r - 1];
//		}
//		print(cubes);
		
	}
	
	public void updateSecondRow()
	{
		for (int r = 0; r < 40; r++)
		{
			for (int c = 0; c < 7; c++)
			{
				cubes[r][c] = null;
				cubeHitboxes[r][c] = null;
			}
			
		}
		for (int r = 0; r < 40; r++)
		{
			for (int c = 0; c <= difficulty; c++)
			{
//				int choose = seed.nextInt(2);
//				if (choose == 0)
//				{
//					int randX = seed.nextInt(130) - 65;
//				} else 
//				{
//					
//				}
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) main.manager.get("CubeBlue.g3db"), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}
	}
	
	public int checkForNewLevel()
	{
		return 3;
	}
	
	
}
