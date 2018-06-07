import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private int cellsPerRow;
	private int cellSize;
	private Cell[][] cells;
	private Timer timer;
	private int livingNeighbors;
	private int mouseX, mouseY;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(60, this);
		this.cellsPerRow = cpr;

		// calculate the cellSize

		cellSize = w / cpr;

		// initialize the cells array

		cells = new Cell[cpr][cpr];

		// initialize each cell in the array

		System.out.println(cellSize);

		for (int i = 0; i < cpr; i++) {
			for (int j = 0; j < cpr; j++) {
				cells[i][j] = new Cell(i * cellSize, j * cellSize, cellSize);
			}
		}

	}

	public void randomizeCells() {
		// make each cell alive or dead randomly
		repaint();

		int randomLifeState = new Random().nextInt(3);

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				if (randomLifeState == 1) {
					cells[i][j].isAlive = true;
				} else {
					cells[i][j].isAlive = false;
				}
				randomLifeState = new Random().nextInt(3);
			}
		}
	}

	public void clearCells() {
		// set isAlive to false for all cells
		repaint();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[i][j].isAlive = false;
			}
		}
		stopAnimation();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// iterate through the cells and draw them
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[i][j].draw(g);
			}
		}
	}

	// advances world one step
	public void step() {
		// initialize the numLivingNbors variable to be the same size as the cells
		int[][] numLivingNbors = new int[cells.length][cells.length];
		// iterate through the cells and populate the numLivingNbors array with their
		// neighbors

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				numLivingNbors[i][j] = getLivingNeighbors(i, j);
			}
		}

		for (int i = 0; i < numLivingNbors.length; i++) {
			for (int j = 0; j < numLivingNbors.length; j++) {
				cells[i][j].liveOrDie(numLivingNbors[i][j]);
			}
		}

		repaint();
	}

	// returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {

		livingNeighbors = 0;

		if (x > 0 && y > 0 && x < cells.length - 1 && y < cells.length - 1) {
			if (cells[x - 1][y].isAlive) {
				livingNeighbors++;

			}
			if (cells[x + 1][y].isAlive) {
				livingNeighbors++;

			}
			if (cells[x][y - 1].isAlive) {
				livingNeighbors++;

			}
			if (cells[x][y + 1].isAlive) {
				livingNeighbors++;

			}
			if (cells[x + 1][y + 1].isAlive) {
				livingNeighbors++;

			}
			if (cells[x - 1][y - 1].isAlive) {
				livingNeighbors++;

			}
			if (cells[x + 1][y - 1].isAlive) {
				livingNeighbors++;

			}
			if (cells[x - 1][y + 1].isAlive) {
				livingNeighbors++;
			}
		}
		// add 1 to livingNeighbors for each
		// neighboring cell that is

		return livingNeighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// IGNORE
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// IGNORE

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// IGNORE

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// // get the location of the mouse
		// mouseX = e.getX();
		// mouseY = e.getY();
		// // toggle the cell at that location to either alive or dead
		// if (cells[mouseX][mouseY].isAlive) {
		// cells[mouseX][mouseY].isAlive = false;
		// } else {
		// cells[mouseX][mouseY].isAlive = true;
		// }
		// // based on its current state
		// repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// IGNORE

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
