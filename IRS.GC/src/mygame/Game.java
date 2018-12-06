package mygame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;

import classes.EntityA;
import classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 320;
	
	public static final int HEIGHT= WIDTH/12*9;
	
	public static final int SCALE = 2;
	
	public static String TITLE="space game";
	
    private Menu menu;
    
    
    public static int score=0;
    
    
	
	private boolean running=false;
	
	private Thread thread;
	
	private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	private BufferedImage spritesheet=null;
	
	public static int Health=100 * 2;
	
	public LinkedList<EntityA> ea;
	
	public LinkedList<EntityB> eb;
	
	public static enum STATE{
		
		MENU,
		
		GAME,
		
		GAMEOVER
		
		
	};
	
	public static STATE state=STATE.MENU;
	
	private BufferedImage Background=null;
	
	boolean is_shooting =false;
	
	private int enemy_count=1;
	
	private int enemy_killed=0;
	
	
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	private Player p;
	
	private Controller c;
	
	private Textures tex;
	
	private BufferedImage player;
	
	public void  init() throws IOException{
		
		requestFocus();
		
		BufferedImageLoader loader=new BufferedImageLoader();
		
		
			spritesheet=loader.loadImage("/spritesheet.png");
			
			Background=loader.loadImage("/bg.jpg");
		
		   addKeyListener(new KeyInput(this));
			
			
		   tex=new Textures(this);
			
			
			c=new Controller(tex,this);
			
			p=new Player(200,200,tex,this,c);
			
			menu = new Menu();
			
			ea=c.getEntityA();
			
			eb=c.getEntityB();
			
			addKeyListener(new KeyInput(this));
			
			this.addMouseListener(new MouseInput());
			
			c.createEnemy(enemy_count);
			
			
		
	}
	
	private synchronized  void start(){
		
		if(running)
			return;
		
		running=true;
		
		Thread thread=new Thread(this);
		
		thread.start();
		
		
		
	}
	
	
	private synchronized void stop() {
		
		if(!running)
			return;
		
		running=false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(1);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void run() {
		
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double lasttime=System.nanoTime();
				
		final double ammountofticks = 60.0;
		
		double ns= 1000000000/ammountofticks;
		
		double delta = 0;
		
		int updates= 0;
		
		int frames = 0;
		
		long timer = System.currentTimeMillis();
		
		while(running){
			
			
			long now=System.nanoTime();
			
			delta += (now - lasttime) / ns;
			
			lasttime=now;
			
			if(delta >= 1){
				
				
				tick();
				
				updates++;
				
				delta--;
			}
			
			render();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				
				timer += 1000;
				
				System.out.println(updates + " Ticks FPS " + frames);
				
				updates=0;
				
				frames=0;
				
				
			}
			
		}
		
		stop();
		
	}
	
	
	
	private void tick(){
		
		if(state ==STATE.GAME){
		
		p.tick();
		
		c.tick();
		
		}
		
		if(enemy_killed >= enemy_count){
			
			enemy_count=enemy_count+2;
			
			enemy_killed=0;
			
			c.createEnemy(enemy_count);
			
			
			
			
		}
		
	}
	
	private void render(){
		
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			
			createBufferStrategy(3);
			
			return;
			
		}
		
		
		Graphics g=bs.getDrawGraphics();
		
		/////////////////////////////////////
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
		
		
		g.drawImage(Background, 0, 0, null);
		
		if(state ==STATE.GAME){
		
		p.render(g);
		c.render(g);
		
		g.setColor(Color.gray);
		
		g.fillRect(5, 5, 200, 50);
		
		g.setColor(Color.green);
		
		g.fillRect(5, 5, Health, 50);
		
		
		g.setColor(Color.white);
		
		g.drawRect(5, 5, 200, 50);
		
		
		if(Health==0){
			
			this.state=STATE.GAMEOVER;
			
			
		}
		
		
		Graphics2D g2d =(Graphics2D) g; 
		
		Font fnt0 =new Font("arial",Font.BOLD,50);
		
		g.setFont(fnt0);
		
		g.setColor(Color.white);
		
		String s=Integer.toString(score);
	
		g.drawString(s, Game.WIDTH/2+400, 50);
		
		
	
		
		
		}else if(state ==STATE.MENU){
			
			menu.render(g);
			
			
			
			
			
		}else if(state ==STATE.GAMEOVER){
			
			
			Graphics2D g2d =(Graphics2D) g; 
			
			Font fnt0 =new Font("arial",Font.BOLD,50);
			
			g.setFont(fnt0);
			
			g.setColor(Color.white);
			
			g.drawString("Game Over", Game.WIDTH/2+10, 100);
			
		    String gs=Integer.toString(score);
			
			g.drawString("Your score is :"+ gs, Game.WIDTH/2+10, 200);
			
			
			
			Font fnt1 =new Font("arial",Font.BOLD,30);
			
			g.setFont(fnt1);
			g.drawString("Retry", menu.helpButton.x + 10, menu.helpButton.y + 30);
			g2d.draw(menu.helpButton);
			

		    this.Health=200;
		    
		    this.enemy_count=1;
		    
		    
		    
		    this.enemy_killed=0;
		    
		    
				
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				
		
			
		}
		
		////////////////////////////////////
		g.dispose();
		bs.show();
		
		
		
		
		
		
	};
	
	public void keyPressed(KeyEvent e){
		
		int key=e.getKeyCode();
		
		if(state ==STATE.GAME){
		
		if(key==KeyEvent.VK_RIGHT){
			
			p.setVelx(5);
			
		}else if(key==KeyEvent.VK_LEFT){
			
			p.setVelx(-5);
			
		}
		else if(key==KeyEvent.VK_DOWN){
			
			p.setVely(5);
			
		}
		else if(key==KeyEvent.VK_UP){
			
			p.setVely(-5);
			
		}else if(key==KeyEvent.VK_SPACE && !is_shooting){
			
			is_shooting=true;
			c.addEntity(new Bullets((int)p.getX(),(int)p.getY(),tex,this));
		}
		
		
		}
	}
	
	
	
	public void keyReleased(KeyEvent e){
		
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_RIGHT){
			
			p.setVelx(0);
			
		}else if(key==KeyEvent.VK_LEFT){
			
			p.setVelx(0);
			
		}
		else if(key==KeyEvent.VK_DOWN){
			
			p.setVely(0);
			
		}
		else if(key==KeyEvent.VK_UP){
			
			p.setVely(0);
			
		}else if(key==KeyEvent.VK_SPACE){
			
			
	     is_shooting=false;
		}
		
		
		
	}
	
	
	
	public static void main(String[] args){
		
		Game game=new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		
		game.setMaximumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		
		game.setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		
		JFrame frame =new JFrame(game.TITLE);
		
		frame.add(game);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		
		game.start();
		
		
	}
	
	public BufferedImage getspritsheet(){
		
		return spritesheet;
		
	}
	
	
	/*public void setScore(int score){
		
		this.score=score;
		
	}
	
	public int getScore(){
		
		
		return this.score;
	}
	
	
	*/
	
	

}
