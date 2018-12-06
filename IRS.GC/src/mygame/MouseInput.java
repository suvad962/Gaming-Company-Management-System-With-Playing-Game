package mygame;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		
		int mx=e.getX();
		
		int my=e.getY();
		
		
		
		/* 	public Rectangle playButton =new Rectangle(Game.WIDTH/2 + 120,150,100,50);
	
	        public Rectangle helpButton =new Rectangle(Game.WIDTH/2 + 120,250,100,50);
	
	
	        public Rectangle quitButton =new Rectangle(Game.WIDTH/2 + 120,350,100,50); */
		
		
		if(mx >= Game.WIDTH/2+120 && mx <= Game.WIDTH/2+220){
			
			
			if(my >= 150 && my<=200){
				
			Game.state=Game.state.GAME;
				
				
			}
			
			
		}	if(mx >= Game.WIDTH/2+120 && mx <= Game.WIDTH/2+220){
			
			
			if(my >= 250 && my<=300){
				
			Game.state=Game.state.GAME;
			
			Game.score=0;
				
				
			}
			
		
		
		}
		
		if(mx >= Game.WIDTH/2+120 && mx <= Game.WIDTH/2+220){
			
			
			if(my >= 350 && my<=400){
				
                  System.exit(1);
				
				
			}
			
			
		}
		
		
	
		
		
		
		
		
		
		
		
		
	}

	
	


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
