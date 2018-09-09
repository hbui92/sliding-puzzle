package slidingPuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;

public class BoardGenerator {
	public int map[][];
	public int zeroRow;
	public int zeroCol;
	public BoardGenerator(int row, int col){
		map = new int[row][col];
		Random r = new Random();
		HashSet<Integer> list = new HashSet<Integer>();
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				do {
					map[i][j] = r.nextInt(16);
				}
				while (list.contains(map[i][j]));
				list.add(map[i][j]);
				if (map[i][j] == 0){
					zeroRow = i;
					zeroCol = j;
				}
			}
		}
	}
	public void draw(Graphics g){
		int posX = 150, xWidth = 100;
		int poxY = 100, yWidth = 100;
		//draw border
		g.setColor(Color.blue);
		g.fillRect(145, 100, 5 , 403);
		g.fillRect(150, 498, 403 , 5);
		g.fillRect(548, 100, 5 , 403);
		g.fillRect(145, 95, 408 , 5);
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j]>0){
					//draw block
					g.setColor(Color.white);	
					g.fillRect(posX + j*xWidth, poxY + i*yWidth, xWidth - 2, yWidth - 2);
					
					//draw number
					g.setColor(Color.black);
					g.drawString(String.valueOf(map[i][j]), posX + ((2*j+1)*xWidth/2) - 5, poxY + ((2*i+1)*yWidth/2) + 5);
				}
			}
		}
	}
}