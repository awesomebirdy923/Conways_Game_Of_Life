import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 *  1. Check out the Wikipedia page on Conway's Game of Life to familiarize yourself
 *     with the concept.
 *     
 *	https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 */

/*
 *  2. Run the ConwaysGOL.jar to see a demo of the final product.
 */

/* 
 *  3. Create the program on your own or fill in the code under the comments to complete the project.
 *
 */

public class ConwaysGameOfLife extends JPanel implements ActionListener, MouseListener {
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static final int CELLS_PER_ROW = 350;

	private boolean isRunning = false;

	private JFrame window;
	private JPanel inputPanel;
	private JButton startStopButton;
	private JButton randomizeButton;
	private JButton clearButton;
	private JLabel speedLabel;
	private JTextField speedField;

	private WorldPanel gamePanel;

	public static void main(String[] args) {
		new ConwaysGameOfLife().launchGame();
	}

	public void launchGame() {
		// build the window and start the simulation
		window = new JFrame();
		inputPanel = new JPanel();
		gamePanel = new WorldPanel(WIDTH, HEIGHT, CELLS_PER_ROW);
		startStopButton = new JButton("Start");
		randomizeButton = new JButton("Randomize");
		clearButton = new JButton("Clear");
		speedLabel = new JLabel();
		speedField = new JTextField("60");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startStopButton.addActionListener(this);
		randomizeButton.addActionListener(this);
		clearButton.addActionListener(this);
		inputPanel.add(startStopButton);
		inputPanel.add(randomizeButton);
		inputPanel.add(clearButton);
		inputPanel.add(speedLabel);
		inputPanel.add(speedField);
		JPanel newPanel = new JPanel();
		newPanel.add(inputPanel);
		newPanel.add(gamePanel);
		window.add(newPanel);
		window.addMouseListener(gamePanel);
		window.setVisible(true);
		window.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if startStopButton is pressed,
		// toggle isRunning to the opposite of its current state
		// start or stop the animation based on the state of isRunning
		JButton buttonPressed = (JButton) e.getSource();
		if (startStopButton == buttonPressed) {
			System.out.println("Start-Stop Button pressed");
			int newDelay = Integer.parseInt(speedField.getText());
			gamePanel.setAnimationDelay(newDelay);
			if (isRunning == true) {
				isRunning = false;
				gamePanel.stopAnimation();
				startStopButton.setText("Start");
			} else if (isRunning == false) {
				isRunning = true;

				gamePanel.startAnimation();
				startStopButton.setText("Stop");
			}
			int delay = Integer.parseInt(speedField.getText());
			gamePanel.setAnimationDelay(delay);
		} else if (randomizeButton == buttonPressed) {
			System.out.println("Randomize Button pressed");
			gamePanel.randomizeCells();
		} else if (clearButton == buttonPressed) {
			System.out.println("Clear Button pressed");
			gamePanel.clearCells();
		}
		// if ranomizeButton is pressed
		// call randomizeCells

		// if clearButton is pressed
		// call clearCells
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
