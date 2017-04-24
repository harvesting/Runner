package com.mygdx.game;

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

	public void update(float deltaTime, Map map)
	{
		position.add(temp.set(velocityForward).scl(deltaTime));
		if (speed == 1 && position.x <= 64)
		{
			position.add(temp.set(velocityLeftRight).scl(deltaTime));
		} else if (speed == -1 && position.x >= -64)
		{
			position.sub(temp.set(velocityLeftRight).scl(deltaTime));
		}
		transform.getRotation(oldRotation).slerp(rotation, .1f);
		oldPosition.lerp(position, .1f);
		transform.set(oldPosition, oldRotation);
		hitbox.setPosition(position.x, position.z);
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
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
