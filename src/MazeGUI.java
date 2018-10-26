import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.io.FileNotFoundException;

public class MazeGUI extends JPanel {
    GridBagConstraints gbc;
    
    private GridLocation [][] squares;

    private GridLocation startLocation;
    private GridLocation goalLocation;
    
    public MazeGUI(Maze maze) {
	int rows = maze.getNumRows();
	int cols = maze.getNumColumns();
	squares = new GridLocation[rows][cols];

	// Initializes the GUI
	gbc = new GridBagConstraints();
	setLayout(new GridBagLayout());

	/* Sets up the grid */
	for (int row = 0; row < rows; ++row) {
	    for (int col = 0; col < cols; ++col) {
		gbc.gridx = col;
		gbc.gridy = row;
		//Square sqr = new Square(600/cols, 600/rows);
		GridLocation loc = new GridLocation(row, col);

		char symbol = maze.getSquare(loc);
		if (symbol == '#')
		    loc.setBackgroundColor(Color.BLACK);
		else if (symbol == 'o') {
		    loc.setBackgroundColor(Color.GREEN);
		    startLocation = new GridLocation(row, col);
		}
		else if (symbol == '*') {
		    loc.setBackgroundColor(Color.RED);
		    goalLocation = new GridLocation(row, col);
		}
		add(loc, gbc);
		squares[row][col] = loc;
	    }
	}
    }

    /*
     * Called when a location added to agenda in solveMaze
     */
    public void addLocToAgenda(GridLocation loc) {
	if ((!loc.equals(startLocation)) && (!loc.equals(goalLocation)))
	    squares[loc.getRow()][loc.getColumn()].setBackgroundColor(Color.LIGHT_GRAY);
    }

    /*
     * Called after a location has been visited in solveMaze
     */
    public void visitLoc(GridLocation loc) {
	if ((!loc.equals(startLocation)) && (!loc.equals(goalLocation)))
	    squares[loc.getRow()][loc.getColumn()].setBackgroundColor(Color.DARK_GRAY);
    }

    /* 
     * Called as solution path constructed in solveMaze
     */
    public void addLocToPath(GridLocation loc) {
	if ((!loc.equals(startLocation)) && (!loc.equals(goalLocation)))
	    squares[loc.getRow()][loc.getColumn()].setBackgroundColor(Color.YELLOW);
    }

    public void pause(int delay) {
	try {
	    Thread.sleep(delay);
	}
	catch (InterruptedException ie) {
	}
    }

    /*
     * Print out a usage message instead of erroring
     * with incorrect number or values for arguments to main
     */
    public static void printUsage() {
	System.out.println("Usage: java MazeGUI <maze file> <agenda type>");
	System.out.println("\tWhere agenda type should be s (for stack) or q (for queue)");
    }
    
    public static void main(String [] args) {
	if (args.length != 2) {
	    printUsage();
	    System.exit(1);
	}
	
	JFrame frame = new JFrame("Maze Solver");

	// Create a new maze based on maze file given
	Maze maze = null;
	try {
	   maze = new Maze(args[0]);
	}
	catch(FileNotFoundException fnfe) {
	   System.out.println("Cannot find maze file: " + args[0]);
	   System.exit(1);
	}
	
	// Now setup the GUI
	MazeGUI mazeGraphics = new MazeGUI(maze);
	
	frame.getContentPane().add("Center", mazeGraphics);

	mazeGraphics.setFocusable(true);
	mazeGraphics.requestFocusInWindow();

	frame.setSize(600, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	// Create an appropriate type of Agenda and a MazeSolver
	// Then solve the maze
	Agenda agenda = null;

	if (args[1].equalsIgnoreCase("s"))
	    agenda = new StackAgenda();
	else if (args[1].equalsIgnoreCase("q"))
	    agenda = new QueueAgenda();
	else {
	    printUsage();
	    System.exit(1);
	}
	
	MazeSolver solver = new MazeSolver(agenda);
	solver.solveMaze(maze, mazeGraphics);

    }
}
