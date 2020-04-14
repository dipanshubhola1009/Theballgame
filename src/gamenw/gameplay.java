package gamenw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gameplay extends JPanel implements KeyListener,ActionListener{
public int score=0;
public int no_tiles=21;
public boolean play=false;
public boolean fail = false;
public boolean win=false;

public int ballposX=120;
public int ballposY=350;

public int posiP=300;


public int Xdir=-1;
public int Ydir=-2;
public int sXdir=-1;
public int sYdir=-2;
public Timer time;
public int delay=8;

public int row=3;
public int col=7;

//for main graphics
public int smallballX=400;
public int smallballY=300;

public int set=0;
public brickmaker NewMap;



//constructor gameplay
 gameplay(){
	 NewMap = new brickmaker(row,col);
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	time = new Timer(delay,this);
	time.start();
	}
 
public void paint(Graphics g)
{
	g.setColor(Color.yellow);
	g.fillRect(0,0,700,600);
	g.setColor(Color.black);
	g.fillRect(3,3,680,580);

	//for main menu
		if(!play)
		{   //background color
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD,40));
			g.drawString("Welcome to Ball game", 155, 50);
	
			//small rectangle
			g.drawRect(200,200,300,200);
			
			//small ball
			g.setColor(Color.yellow);
			g.fillOval(smallballX, smallballY, 40, 40);
			
			
			//base text
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("PRESS SPACE TO START", 220, 550);
			
			
			//if ball drops
			if(fail) {

				g.setColor(Color.white);
				g.setFont(new Font("serif",Font.BOLD,40));
				g.drawString("SCORE:"+score, 260 , 300);
			}
			//if win
			if(win) {
			    g.setColor(Color.white);
				g.setFont(new Font("serif",Font.BOLD,40));
				g.drawString("SCORE:"+score, 260 , 300);
		
				
				g.setColor(Color.red);
				g.setFont(new Font("serif",Font.BOLD,40));
				g.drawString("YOU WON", 260, 150);
				

				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("<-Press ENTER for next level->", 220, 450);
				}
				
				
				
			}

		if(play)
		{
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		g.setColor(Color.green);
		g.fillRect(posiP,550,100,50);
		NewMap.draw((Graphics2D)g);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,24));
		g.drawString(""+score, 600, 50);
		}
		
		
		if(ballposY>560)
		{
		    ballposY=350;
		    ballposX=120;
		    Xdir=-1;
		    Ydir=-2;
		    posiP=300;
			fail=true;
			win=false;
			play=false;
		}
		if(no_tiles==0)
		{
			
			win=true;
			play=false;
		}
		g.dispose();
		
		
		
		
}



@Override
public void actionPerformed(ActionEvent e) {
	time.start();
	repaint();
	
	
	
	if(play)
	{   ballposX+=Xdir;
	    ballposY+=Ydir;
	
		if(ballposX==10)
		{
			Xdir=-Xdir;
		}
		if(ballposY==10)
		{
			Ydir=-Ydir;
		}
		if(ballposX==670)
		{
			Xdir=-Xdir;
		}
	}
	else {
		smallballX+=sXdir;
		smallballY+=sYdir;
		if(smallballX==200 )
		{
			sXdir=-sXdir;
		}
		if(smallballY==200 )
		{
			sYdir=-sYdir;
		}
		if( smallballX==460)
		{
			sXdir=-sXdir;
		}
		if(smallballY==360)
		{
			sYdir=-sYdir;
		}
		
		
	}
	
	if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(posiP,550,100,80))) {
		Ydir=-Ydir;
		}
	
	 for(int i=0;i<NewMap.map.length;i++) {
			for(int j=0;j<NewMap.map[0].length;j++) {
				if(NewMap.map[i][j]>0)
				{
					int brickX= j * NewMap.brickW + 35;
					int brickY=i* NewMap.brickH + 50;
					int brickW= NewMap.brickW;
					int brickH= NewMap.brickH;
					
					Rectangle rect = new Rectangle(brickX,brickY,brickW,brickH);
					Rectangle ballrect= new Rectangle(ballposX,ballposY,20,20);
					Rectangle brickrect= rect;
					
					if(ballrect.intersects(brickrect)) {
						NewMap.setvalue(0,i,j);
						score+=5;
						no_tiles--; 
						if(ballposX+19<=brickrect.x||ballposX+1>=brickrect.x+brickrect.width)
						{
							Xdir=-Xdir;
						}
						else {
							Ydir=-Ydir;
						}
					}
					
				}
			}
	 }
	
	}
	





@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}




@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
	//for paddle press
	if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	{   if(posiP<=580) {
		posiP+=20;}
	else {
		posiP=580;
	}
	}
	
	//for left key function
	if(e.getKeyCode()==KeyEvent.VK_LEFT)
	{
		if(posiP>=20) {
			posiP-=20;
		}else {
			posiP=20;
		}
	}
	
	//for space to play and pause
	if(e.getKeyCode()==KeyEvent.VK_SPACE)
	{
		    NewMap=new brickmaker(row,col);
		    score=0;
		    no_tiles=0;
			play=true;
		
	}
	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		row+=2;
		col+=3;
		no_tiles=row*col;
		NewMap = new brickmaker(row,col);
		win=false;
		repaint();
		score=0;
	}
	
}




@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
