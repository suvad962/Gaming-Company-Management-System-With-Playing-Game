package mygame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.EntityA;

public class Bullets extends GameObject implements EntityA {
	
	
	
	BufferedImage image;
	
	private Textures tex;
	
	private Game game;
	
	public Bullets(double x,double y,Textures tex,Game game){
		
		super(x,y);
		
		this.tex=tex;
		
		this.game=game;
		
	}
	
	public void tick(){
		
		y-=10;
		
		
		
		
		
	}
	
	
	public void render(Graphics g){
		
		
		
		g.drawImage(tex.missile, (int) x, (int) y,null);
	}
	
	public double getY(){
		
		 return this.y;
	}
	
	
	   public Rectangle getBounds(){
		   
		   
		   return new Rectangle((int)x,(int)y,32,32);
		   
	   }
	
	
	public double getX(){
		
		return this.x;
		
	}

 

}
