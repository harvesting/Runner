package com.mygdx.game;

/**
 * The hitbox that wraps both the player and the cubes.
 * 
 * @author rafaelfajardo
 * 
 */
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
			return ((this.y + this.height/2) - (box.y + box.height/2) < 0 && (this.y + this.height/2) - (box.y - box.height/2) > 0 ||
					(this.y - this.height/2) - (box.y + box.height/2) < 0 && (this.y - this.height/2) - (box.y - box.height/2) > 0) &&
					((this.x + this.width/2) - (box.x + box.width/2) < 0 &&  (this.x + this.width/2) - (box.x - box.width/2) > 0 || 
					(this.x - this.width/2) - (box.x + box.width/2) < 0 &&  (this.x - this.width/2) - (box.x - box.width/2) > 0);
	}
}
