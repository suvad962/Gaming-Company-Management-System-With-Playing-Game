package mygame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import classes.EntityA;
import classes.EntityB;



public class Enemy extends GameObject implements EntityB{
	
	Random r =new Random();
	
	private Game game;
	
	private Controller c;
	
	
	int speed=r.nextInt(3)+1;

	
	
	private Textures tex;
	
	Enemy(double x,double y,Textures tex,Controller c,Game game){
		
		
		super(x,y);
		this.tex=tex;
		
		this.c=c;
		
		this.game=game;
		
	
		
		
	}
	
	public void tick(){
		
		y+=speed;
		
		
		if(y > (Game.HEIGHT * Game.SCALE)){
			
			x=r.nextInt(640);
			
			y=-10;
			
			//x=r.nextInt(Game.HEIGHT * Game.SCALE);
			
			
		}
		
		for(int i=0; i<game.ea.size();i++){
			
			
			EntityA tempEnt= game.ea.get(i);
			
			
			
			
			if(Physics.Collision(this, tempEnt)){
				
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				
				
				game.setEnemy_killed(game.getEnemy_killed()+1);
				
				Game.score=Game.score+1;
				
			}
			
			
			
		}
		
		
		
	
		
		
	}
	
	
	public void render(Graphics g){
		
		g.drawImage(tex.enemy, (int) x,(int) y, null);
		
	}
	
	public double getY(){
		
		
		
		return this.y;
	}
	
	public void setY(double y){
		
		this.y=y;
		
		
	}

	
	   public Rectangle getBounds(){
		   
		   
		   return new Rectangle((int)x,(int)y,32,32);
		   
	   }
	
	
	
	public double getX() {
		
		return this.x;
	}
	
}
