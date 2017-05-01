package com.mygdx.game;

/**
 * The class that holds all attributes of the player (the ship ModelInstance). It is also responsible for updating there attributes.
 * 
 * @author rafaelfajardo
 * 
 */
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Player extends ModelInstance implements InputProcessor
{

	Vector3 velocityLeftRight = new Vector3(20f, 0, 0);
	Vector3 velocityForward = new Vector3(0, 0, 20f);
	Vector3 position = new Vector3();
	Vector3 oldPosition = new Vector3();
	Quaternion oldRotation = new Quaternion();
	Quaternion rotation = new Quaternion();
	Vector3 temp = new Vector3();
	int speed;
	Hitbox hitbox;

	public Player(Model model)
	{
		super(model, 0, 2, 0);
		this.transform.getTranslation(position);
		oldPosition.set(position);
		speed = 0;
		this.transform.scl(3);
		hitbox = new Hitbox(2f, 2f);
	}

	/**
	 * Updates player position 
	 * @param deltaTime - change in time
	 * @param leftRight - whether or not player should be able to move left or right; 1 = yes, anything else = no
	 */
	public void update(float deltaTime, int leftRight)
	{
		position.add(temp.set(velocityForward).scl(deltaTime));
		
		if (leftRight == 1)
		{
			if (speed == 1 && position.x <= 64) //right boundary
			{
				position.add(temp.set(velocityLeftRight).scl(deltaTime));
			} else if (speed == -1 && position.x >= -64) //left boundary
			{
				position.sub(temp.set(velocityLeftRight).scl(deltaTime));
			}
			
			// Spherically interpolates to new rotation
			transform.getRotation(oldRotation).slerp(rotation, .1f);
		}
		
		// Linearly interpolates from old position to new position
		oldPosition.lerp(position, .1f); //alpha of .1 for smoothest results
		transform.set(oldPosition, oldRotation);
		hitbox.setPosition(position.x, position.z);
	}
	
	/**
	 * Updates player's velocity on the z axis
	 * @param scale - the amount added to a player's current velocity on the z axis
	 */
	public void updateForwardSpeed(int scale)
	{
		velocityForward.add(0, 0, scale);
	}
	
	/**
	 * Updates player's velocity on the x axis
	 * @param scale - the amount added to a player's current velocity on the x axis
	 */
	public void updateLeftRightSpeed(int scale)
	{
		velocityLeftRight.add(scale, 0, 0);
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.LEFT || keycode == Keys.A)
		{
			speed = 1;
			rotation.set(Vector3.Z, -25);
		}
		if (keycode == Keys.RIGHT || keycode == Keys.D)
		{
			speed = -1;
			rotation.set(Vector3.Z, 25);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if ((keycode == Keys.LEFT || keycode == Keys.A) && (speed == 1))
		{
			speed = 0;
			rotation.set(Vector3.Z, 0);
		}
		if ((keycode == Keys.RIGHT || keycode == Keys.D) && (speed == -1))
		{
			speed = 0;
			rotation.set(Vector3.Z, 0);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) { return false; }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; } 

	@Override
	public boolean scrolled(int amount) { return false; }
}
