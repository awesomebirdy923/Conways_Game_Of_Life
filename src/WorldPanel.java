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

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(60 * 10, this);
		this.cellsPerRow = cpr;

		// calculate the cellSize

		cellSize = w / cpr;

		// initialize the cells array

		cells = new Cell[cpr][cpr];

		// initialize each cell in the array

		for (int i = 0; i < cpr; i++) {
			for (int j = 0; j < cpr; j++) {
				cells[i][j] = new Cell(i, j, cellSize);
			}
		}

	}

	public void randomizeCells() {
		// make each cell alive or dead randomly
		repaint();

		int randomLifeState = new Random().nextInt(6);

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				if (randomLifeState == 1) {
					cells[i][j].isAlive = true;
				} else {
					cells[i][j].isAlive = false;
				}
				randomLifeState = new Random().nextInt(6);
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
				cells[i][j].liveOrDie(numLivingNbors[i][j]);
			}
		}

		repaint();
	}

	// returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {

		int livingNeighbors = 0;

		if (x > 0 && y > 1 && x < cells.length - 1 && y < cells.length - 1) {
			if (cells[x - 1][y].isAlive) {
				livingNeighbors++;
			} else if (cells[x + 1][y].isAlive) {
				livingNeighbors++;
			} else if (cells[x][y - 1].isAlive) {
				livingNeighbors++;
			} else if (cells[x][y + 1].isAlive) {
				livingNeighbors++;
			} else if (cells[x + 1][y + 1].isAlive) {
				livingNeighbors++;
			} else if (cells[x - 1][y - 1].isAlive) {
				livingNeighbors++;
			} else if (cells[x + 1][y - 1].isAlive) {
				livingNeighbors++;
			} else if (cells[x - 1][y + 1].isAlive) {
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
		// get the location of the mouse

		// toggle the cell at that location to either alive or dead
		// based on its current state
		repaint();
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
