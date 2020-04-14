package gamenw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class brickmaker {
	public int map[][];
	public int row=3;
	public int col=7;
	public int brickW;
	public int brickH;
	public int noTiles=row*col;
	
	public brickmaker(int row, int col) {
	map = new int[row][col];
	
	
	for(int i=0;i<map.length;i++) {
		for(int j=0;j<map[0].length;j++) {
			map[i][j]=1;
		}
	}
	
	brickW=620/col;
	brickH=150/row;
	noTiles=row*col;
	
	}
	
	  void draw(Graphics2D g){
		  for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[0].length;j++) {
					if(map[i][j]>0)
					{
						g.setColor(Color.white);
						g.fillRect(j*brickW +35,i*brickH + 50,brickW,brickH);
						g.setColor(Color.black);
						g.setStroke(new BasicStroke(3));
						g.drawRect(j*brickW +35,i*brickH + 50,brickW,brickH);
						
					}
				}
			}
		  
	  }
	  
	  void setvalue(int value,int row, int col) {
		  map[row][col]=value;
	  }
	  

}
