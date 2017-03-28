package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Player extends ModelInstance implements InputProcessor {
	
	Vector3 velocity = new Vector3(8f, 0, 0);
	Vector3 position = new Vector3();
	Vector3 oldPosition = new Vector3();
	Vector3 temp = new Vector3();
	int speed;
	
	public Player(Model model) {
		super(model, 0, 5, 0);
		this.transform.getTranslation(position);
		oldPosition.set(position);
		speed = 0;
	}
	
	public void update(float deltaTime) {
		this.transform.setTranslation(oldPosition.lerp(position, .1f));
		if (speed == 1 && position.x <= 30) {
			position.add(temp.set(velocity).scl(deltaTime));
		} else if (speed == -1 && position.x >= -30) {
			position.sub(temp.set(velocity).scl(deltaTime));
		}	
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT || keycode == Keys.A) {
			speed = 1;
			this.transform.lerp(this.transform.setToRotation(Vector3.Z, -25), .1f);
		}
		if (keycode == Keys.RIGHT || keycode == Keys.D) {
			speed = -1;
			this.transform.lerp(this.transform.setToRotation(Vector3.Z, 25), .1f);

		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if ( (keycode == Keys.LEFT || keycode == Keys.A) && (speed == 1) ) {
			speed = 0;
			this.transform.lerp(this.transform.setToRotation(Vector3.Z, 0), .1f);	
		}
		if ( (keycode == Keys.RIGHT || keycode == Keys.D) && (speed == -1) ) {
			speed = 0;
			this.transform.lerp(this.transform.setToRotation(Vector3.Z, 0), .1f);			
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
