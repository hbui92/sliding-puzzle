package slidingPuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener {
	private boolean play;
	private BoardGenerator board;
	public Gameplay(){
		board = new BoardGenerator(4,4);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(0,0,700,600);
		
		//board
		board.draw(g);
		
		play = check(board.map);
		if (!play){
			g.setColor(Color.red);
			g.drawString("YOU WIN", 325, 280);
			g.drawString("Press Enter to Restart", 290, 300);
		}
	}
	
	public boolean check(int[][] map){
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map.length; j++){
				int num = i*4 + j + 1;
				if ((map[i][j] != 0) && (map[i][j] != num)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (play){
			if(key.getKeyCode() == KeyEvent.VK_RIGHT){
				if (board.zeroCol > 0){
					board.map[board.zeroRow][board.zeroCol] = board.map[board.zeroRow][board.zeroCol - 1];
					board.map[board.zeroRow][board.zeroCol - 1] = 0;
					board.zeroCol=board.zeroCol - 1;
				}
				repaint();
			}
			if(key.getKeyCode() == KeyEvent.VK_LEFT){
				if (board.zeroCol < 3){
					board.map[board.zeroRow][board.zeroCol] = board.map[board.zeroRow][board.zeroCol + 1];
					board.map[board.zeroRow][board.zeroCol + 1] = 0;
					board.zeroCol=board.zeroCol + 1;
			}
				repaint();
			}
			if(key.getKeyCode() == KeyEvent.VK_UP){
				if (board.zeroRow < 3){
					board.map[board.zeroRow][board.zeroCol] = board.map[board.zeroRow + 1][board.zeroCol];
					board.map[board.zeroRow + 1][board.zeroCol] = 0;
					board.zeroRow=board.zeroRow + 1;
				}
				repaint();
			}
			if(key.getKeyCode() == KeyEvent.VK_DOWN){
				if (board.zeroRow > 0){
					board.map[board.zeroRow][board.zeroCol] = board.map[board.zeroRow - 1][board.zeroCol];
					board.map[board.zeroRow - 1][board.zeroCol] = 0;
					board.zeroRow=board.zeroRow - 1;
				}
				repaint();
			}
		}
		else if(key.getKeyCode() == KeyEvent.VK_ENTER){
			board = new BoardGenerator(4, 4);
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
