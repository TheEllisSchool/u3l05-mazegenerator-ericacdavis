

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cell extends JPanel {
	private static int SIZE = 20;
	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;
	private int row = -1;
	private int col = -1;
	private boolean [] wall = {true, true, true, true};
    
	private boolean current = false;
	private boolean end = false;
    
	private boolean [] path = {false, false, false, false};
    
    
	public Cell(int r, int c){
		row = r;
		col = c;
	}
    
	public int getRow(){
		return row;
	}
    
	public int getCol(){
		return col;
	}
    
	public boolean isWall(int index){ // is there a wall there?
		return wall[index];
	}
    
	public boolean hasAllWalls(){ // use when randomizing maze so that there are no traps
		boolean allWalls = isWall(TOP) && isWall(BOTTOM) && 
				isWall(LEFT) && isWall(RIGHT);
		return allWalls;
	}
    
	public void removeWall(int w){ //if there's a trap, remove a wall
		wall[w] = false;
		repaint();
	}
    
    
	public void openTo(Cell neighbor){ //make sure there's an end
		if (row < neighbor.getRow()){
			removeWall(BOTTOM);
			neighbor.removeWall(TOP);
		} else if (row > neighbor.getRow()){
			removeWall(TOP);
			neighbor.removeWall(BOTTOM);
		} else if (col < neighbor.getCol()){
			removeWall(RIGHT);
			neighbor.removeWall(LEFT);
		} else if (col > neighbor.getCol()){
			removeWall(LEFT);
			neighbor.removeWall(RIGHT);
		}
	}
	
	 
	public void setCurrent(boolean curr){
		current = curr;
		repaint();
	}
    
	public void setEnd(boolean e){
		end = e;
		repaint();
	}
    
	public void addPath(int side){
		path[side] = true;
		repaint();
	}
    
	@Override
	public Dimension getPreferredSize(){
		return new Dimension (SIZE, SIZE);
	}
    
	@Override
	 
	public void paintComponent(Graphics g){
		//draw the background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE, SIZE);
		g.setColor(Color.BLACK);
        
		//draw the walls
//		if (isWall(RIGHT)) {
//			System.out.println("draw right wall");
//			g.drawLine(SIZE, 0, SIZE, SIZE); //side line
//		}
		if (isWall(LEFT)) {
			//System.out.println("draw left wall");
			g.drawLine(0, 0, 0, SIZE); //side line
		}
		if (isWall(TOP)) {
			g.drawLine(0, 0, SIZE, 0); //top line
		}
		
		

		//draw the path
		
		if (path[BOTTOM]) {
			g.setColor(Color.MAGENTA);
			g.drawLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
		}
		if (path[TOP]) {
			g.setColor(Color.BLUE);
			g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);
		}
		if (path[RIGHT]) {
			g.setColor(Color.GREEN);
			g.drawLine(SIZE/2, SIZE/2, SIZE, SIZE/2);
		}
		if (path[LEFT]) {
			g.setColor(Color.YELLOW);
			g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
		}
		ImageIcon unicorn = new ImageIcon("unicorn-2007266_960_720.png");
		Image image2 = unicorn.getImage();
		//draw the balls
		if (current == true) {  
			//g.setColor(Color.GREEN);
			//g.fillOval(0 + SIZE/4, 0 + SIZE/4, SIZE - SIZE/2, SIZE - SIZE/2);
			g.drawImage(image2, 0, 0, SIZE, SIZE, null);
		}
		if (end == true) {
			g.setColor(Color.RED);
			g.fillOval(0 + SIZE/4, 0 + SIZE/4, SIZE - SIZE/2, SIZE - SIZE/2);
		}
    }
    
    
}