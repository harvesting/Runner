package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

/**
 * This class holds all map attributes including floor and cubes. It is responsible for updating these as well.
 * 
 * @author liaderlich
 *
 */
public class Map
{
	private Runner game;
	private Random seed;
	private Vector3 temp;
	
	ModelInstance fenceBackground;
	ModelInstance fenceForeground;
	ModelInstance[][] cubes;
	ModelInstance[][] floor;
	Hitbox[][] cubeHitboxes;
	
	int difficulty;
	float lastRow;
	boolean onForeground;
	
	public Map(Runner runner)
	{
		game = runner;
		seed = new Random();
		fenceForeground = new ModelInstance((Model) game.manager.get("Fence.g3db"), 0, 0, 110);
		fenceBackground = new ModelInstance((Model) game.manager.get("Fence.g3db"), 0, 0, 210);
		floor = new ModelInstance[3][3];
		cubeHitboxes = new Hitbox[80][7];
		cubes = new ModelInstance[80][7];
		difficulty = 0;
		lastRow = 350;
		onForeground = true;
		temp = new Vector3();
	}
	
	/**
	 * Creates floor models, cubes, and the cubes' hitboxes and sets them into separate 2d arrays. 
	 * The cubes are assigned to a spot randomly.
	 */
	public void set()
	{
		for (int row = 2; row >= 0; row--)
		{
			floor[row][0] = new ModelInstance((Model) game.manager.get("ground.obj"), 100, 0, row * 100);
			
			floor[row][1] = new ModelInstance((Model) game.manager.get("ground.obj"), 0, 0, row * 100);
			
			floor[row][2] = new ModelInstance((Model) game.manager.get("ground.obj"), -100, 0, row * 100);
		}
		
		for (int r = 0; r < 80; r++)
		{
			for (int c = 0; c <= difficulty; c++) //difficulty is how many random cubes per row; max is 2
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) game.manager.get("CubeGreen.obj"), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}
	}
	
	/**
	 * Prints a 2d array. Each value is represented with a 1 or 0 to represent if that space is null or not.
	 * @param array - array to be printed
	 */
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

	/**
	 * Render floor and cubes with specified environment.
	 * @param env - the environment the floor and cubes should be rendered with.
	 */
	public void drawFloor(Environment env)
	{
		game.batch.render(fenceForeground);
		game.batch.render(fenceBackground);
				
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance floor : rows)
			{
				game.batch.render(floor, env);
			}
		}
	}
	
	/**
	 * Render floor and fence without environment.
	 */
	public void drawFloor()
	{
		game.batch.render(fenceForeground);
		game.batch.render(fenceBackground);
				
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance floor : rows)
			{
				game.batch.render(floor);
			}
		}
	}
	
	/**
	 * Draws cubes with specified environment.
	 * @param env - the environment to draw the cubes with
	 */
	public void drawCubes(Environment env)
	{
		for (ModelInstance[] row : cubes)
		{
			for (ModelInstance cube : row)
			{
				if (cube != null)
				{
					game.batch.render(cube, env);
				}
			}
		}
	}

	/**
	 * Update both fence and floor.
	 */
	public void updateAll()
	{
		if (onForeground)
		{
			fenceForeground.transform.setTranslation(0, 0, fenceBackground.transform.getTranslation(temp).z + 100);
			onForeground = false;
		} else 
		{
			fenceBackground.transform.setTranslation(0, 0, fenceForeground.transform.getTranslation(temp).z + 100);
			onForeground = true;
		}
		
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance ground : rows)
			{
				ground.transform.translate(0, 0, 100);
			}
		}
	}
	
	/**
	 * Update floor.
	 */
	public void updateFloor()
	{
		for (ModelInstance[] rows : floor)
		{
			for (ModelInstance ground : rows)
			{
				ground.transform.translate(0, 0, 100);
			}
		}
	}
	
	/**
	 * Update first set of cubes.
	 * @param cube - the type of cube to be put into the array
	 */
	public void updateFirstSet(String cube) 
	{	
		for (int r = 40; r < 80; r++)
		{
			for (int c = 0; c < 7; c++)
			{
				cubes[r][c] = null;
				cubeHitboxes[r][c] = null;
			}
		}
		
		lastRow += 225;
		for (int r = 40; r < 80; r++)
		{
			for (int c = 0; c <= difficulty; c++) //difficulty is how many random cubes per row; max is about 2 to 3
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) game.manager.get(cube), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}	
	}
	
	/**
	 * Update second set of cubes.
	 * @param cube - the type of cube to be put into the array
	 */
	public void updateSecondSet(String cube)
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
			for (int c = 0; c <= difficulty; c++) //difficulty is how many random cubes per row; max is about 2 to 3
			{
				int col = seed.nextInt(6);
				int randX = seed.nextInt(130) - 65;
				cubes[r][col] = new ModelInstance((Model) game.manager.get(cube), temp.set(randX, 2.7f, lastRow - (r * 3f)));
				cubeHitboxes[r][col] = new Hitbox(2.7f, 2.7f);
				cubeHitboxes[r][col].setPosition(randX, lastRow - (r * 3f));
			}
		}
	}
}
