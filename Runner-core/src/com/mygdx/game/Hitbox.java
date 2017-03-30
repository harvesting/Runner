package com.mygdx.game;

public class Hitbox
{
	public float x, y, width, height;

	public Hitbox(float width, float height)
	{
		this.width = width;
		this.height = height;
	}

	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public boolean didCollide(Hitbox box)
	{

		return false;
	}

}
