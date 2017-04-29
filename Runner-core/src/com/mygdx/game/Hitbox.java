package com.mygdx.game;
//Liad

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
		

			
		
//		&&  (this.y + this.height/2) - (box.y - box.height/2) > 0
		
//		((this.y - this.height/2) - (box.y + box.height/2) < 0 &&  (this.y - this.height/2) - (box.y - box.height/2) > 0);

		
		
//		(this.y - this.height/2)
		
		
//		if ship is between box on -x axis
//		return ((this.x + this.width/2) - (box.x + box.width/2) < 0 &&  (this.x + this.width/2) - (box.x - box.width/2) > 0) || 
//					((this.x - this.width/2) - (box.x + box.width/2) < 0 &&  (this.x - this.width/2) - (box.x - box.width/2) > 0);
		
//		if left side of the ship is between cube
//		return ( (this.x + this.width/2) - (box.x + box.width/2) < 0 &&  (this.x + this.width/2) - (box.x - box.width/2) > 0  );
		
//		if right side of ship is between cube
//		return ( (this.x - this.width/2) - (box.x + box.width/2) < 0 &&  (this.x - this.width/2) - (box.x - box.width/2) > 0  );
		

		
		//right side
//		( (this.x - this.width/2) - (box.x + box.width/2) < 0)
		//left side
//		( (this.x + this.width/2) - (box.x + box.width/2) < 0)
		
//		(box.x + box.width) - (this.x + this.width) > 0
//		if right side of ship is between box
//		((this.x + this.width) - (box.x + box.width) < 0) && (this.x - box.x > 0)
//		if left side of ship is between box
//		(this.x - (box.x - box.width) > 0 && this.x - box.x < 0)		

	}

}
