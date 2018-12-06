package mygame;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import classes.EntityA;
import classes.EntityB;

public class Controller {
	
	Random r=new Random();
	
	
	private Textures tex;
	
	private Game game;
	
	
	private LinkedList<EntityA> ea=new LinkedList<EntityA>();
	
	private LinkedList<EntityB> eb=new LinkedList<EntityB>();
	
	EntityA enta;
	
	EntityB entb;
	
	
	public Controller(Textures tex,Game game){
		
		this.tex=tex;
		
		
		this.game=game;
		
		
	}
	
	public void createEnemy(int enemy_count){
		
		for(int i=0;i < enemy_count;i++){
			
			
			this.addEntity(new Enemy(r.nextInt(604),-10,tex,this,game));
			
			
		}
		
		
		
	}
	
	
	public void tick(){
		
	
		for(int i=0 ;i< ea.size();i++){
			
			enta=ea.get(i);
			
			enta.tick();
			
			
			
		}
		
		
		for(int i=0 ;i< eb.size();i++){
			
			entb=eb.get(i);
			
			entb.tick();
			
			
			
		}
		
		
		
		
		
		
		
		
			
		}
		
		
		
		public void render(Graphics g){
			
			
			for(int i=0 ;i<ea.size();i++){
				
				enta=ea.get(i);
				
				enta.render(g);
				
				
				
			}
			
		
			for(int i=0 ;i<eb.size();i++){
				
				entb=eb.get(i);
				
				entb.render(g);
				
				
				
			}	
			
			
			
			
		}
		
		
		
	public void addEntity(EntityA Block){
		
		ea.add(Block);
		
		
	}
	
	public void removeEntity(EntityA Block){
		
		
		
		ea.remove(Block);
	}
	
	
	
	public void addEntity(EntityB Block){
		
		eb.add(Block);
		
		
	}
	
	public void removeEntity(EntityB Block){
		
		
		
		eb.remove(Block);
	}
	
	
	
	public LinkedList<EntityA> getEntityA(){
		
		
		return ea;
	}
	
	
	public LinkedList<EntityB> getEntityB(){
		
		
		return eb;
	}


}
