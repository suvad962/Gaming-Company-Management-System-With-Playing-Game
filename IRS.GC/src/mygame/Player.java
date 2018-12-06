package mygame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.EntityA;
import classes.EntityB;

public class Player extends GameObject implements EntityA {
	
	
	
	private double velX=0;
	
	private double velY=0;
	
	public Game game;
	
	public Controller c;
	
	
	private BufferedImage player;
	
	private Textures tex;
	
	public Player(double x,double y,Textures tex,Game game,Controller c){
		
		super(x,y);
		
		this.x=x;
		
		this.y=y;
		
		this.tex=tex;
		
		this.game=game;
		
		this.c=c;
		
		
	}
	
	public void tick(){
		
		x+=velX;
		
		y+=velY;
		
		if(x<=0)
			
			x=0;
		
		if(x>=640-16)
			x=640-16;
		
		if(y<=0)
			y=0;
		
		if(y>=480-32)
			
			y=480-32;
			
		
		
		for(int i=0;i<game.eb.size();i++){
			
			
			
			EntityB tempEnt=game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				
				c.removeEntity(tempEnt);
				
				game.setEnemy_killed(game.getEnemy_killed()+1);
				
				Game.Health =Game.Health-10;
				
				
			}
			
			
			
			
		}
		
		
	}
	
	
	public void render(Graphics g){
		
		g.drawImage(tex.player,(int)x,(int)y,null);
	}
	
	public double getX(){
		
		return x;
	}
	
	public double getY(){
		
		return y;
	}
	
	public void setX(double x){
		
		
		this.x=x;
	}
	
	   public Rectangle getBounds(){
		   
		   
		   return new Rectangle((int)x,(int)y,32,32);
		   
	   }
	
	
	
	public void setY(double y)
	{
		
		
		this.y=y;
	}	

	
	public void setVelx(double velX){
	
		this.velX=velX;
	}
	
	
	public void setVely(double velY){
		
		this.velY=velY;
	}
}
