import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class MazeSolver {
    //This class contains a Main Method that creates new instances of StackAgenda and Maze, as well as new instance of MazeSolver.
    //The solveMaze method is called by the MazeSolver constructor. It determines if the given Maze can be solved.
    //If it can, it creates an ArrayList called pathSeq, which will be the correct sequence of locations from start to goal.
    private Agenda agenda;

    private MazeGUI mazeGUI;

    private GridLocation[][] fakeMaze; //This is a second maze-like 2D array. Instead of each element being its GridLocation with (row and col),
    //for each GridLocation in maze, it's corresponding element in fakeMaze is set to the location that added it to the agenda.
    // This gives us a direct path to follow backwards from the goal.

    private Maze maze; //instance variable for maze

    public boolean mazeSolvable = false; //boolean that is set to true when/if the goal location is reached
    private ArrayList<GridLocation> added; //will keep track of locations already added,
    private ArrayList<GridLocation> currentNeighbors; //will be updated with the neighbors of a location

    private static ArrayList<GridLocation> pathSeq; //ArrayList that will store the correct path sequence, if maze can be solved.

    /**
     * Main Method that creates instances of StackAgenda and MazeSolver, and prints the pathSequence if maze can be solved
     * @param args (command-line arguments)
     */
    public static void main(String[] args) {
        Maze maze = null;
        try { //If file not found, throw FNF exception
            maze = new Maze(args[0]); //gets maze filename from command-line argument
        } catch (FileNotFoundException e) {
            System.out.println("Error! File was not Found!!");
            e.printStackTrace();
            System.exit(0);
        }


        MazeSolver solver = new MazeSolver((args[1] == "q" ? new QueueAgenda() : new StackAgenda())); //create new instance of MazeSolver, solver.
        solver.solveMaze(maze, new MazeGUI(maze));

        if (pathSeq.size() >= 1) { //if there was a sequence of correct locations
            System.out.println("Maze solved, this is the correct sequence of locations: ");
            System.out.println(pathSeq);
        }
        if (pathSeq.size() == 0) { //if pathSeq is empty, it means there is no solution to the maze.
            System.out.println("Sorry, Maze could not be solved.");
        }
    }

    /**
     * MazeSolver Constructor
     * @param agenda
     */
    public MazeSolver(Agenda agenda) {

        this.agenda = agenda;
    }

    /**
     * Gets the 4 adjacent, neighboring GridLocations for a specific loc
     * @param loc
     */
    public void getNeighbors(GridLocation loc) {
        currentNeighbors = new ArrayList<GridLocation>();
        //each of the following will get one of the adjacent neighboring locations to loc
        currentNeighbors.add(new GridLocation(loc.getRow() + 1, loc.getColumn()));
        currentNeighbors.add(new GridLocation(loc.getRow(), loc.getColumn() +1));
        currentNeighbors.add(new GridLocation(loc.getRow() - 1, loc.getColumn()));
        currentNeighbors.add(new GridLocation(loc.getRow(), loc.getColumn() - 1));
    }

    /**
     * Checks if the maze is solvable, and returns a list of the path if so
     * @param maze
     * @return pathSeq, an ArrayList of the correct path if its solvable, otherwise empty list
     */
    public ArrayList<GridLocation> solveMaze(Maze maze, MazeGUI gui) {
        this.maze = maze;
        fakeMaze = new GridLocation[maze.getNumRows()][maze.getNumColumns()];
        fakeMaze[maze.getStartLocation().getRow()][maze.getStartLocation().getColumn()] = null;
        this.agenda.clear(); //Start by clearing agenda
        this.added = new ArrayList<GridLocation>(); //create empty added ArrayList
        GridLocation start = maze.getStartLocation(); //following are just placeholder vars to make things easier
        GridLocation goal = maze.getGoalLocation();
        Integer numRows = maze.getNumRows();
        Integer numColumns = maze.getNumColumns();
        agenda.addLocation(start); //first add the start loc to agenda and added
        added.add(start);
        gui.addLocToAgenda(start);
        while (! agenda.isEmpty() && ! mazeSolvable) {
            GridLocation currentLoc = agenda.getLocation();
            gui.pause(1000);
            gui.visitLoc(currentLoc);
            getNeighbors(currentLoc); //generate a list of the 4 Neighbors of the current Location (to the N,S,E,W)
            for(GridLocation testLoc : currentNeighbors) { //test the following conditions on each of the 4 neighboring locations
                //this for loop should have time complexity of O(1), because it almost always iterates 4 times.
                // I used a for each so I can refer directly to testLoc to shorten the code
                if (!added.contains(testLoc) && testLoc.getRow() < numRows && testLoc.getColumn() < numColumns
                        && testLoc.getRow() >= 0 && testLoc.getColumn() >= 0 || testLoc.equals(goal)) { // each of these conditionals test whether
                    if (testLoc.equals(goal)) {
                        fakeMaze[testLoc.getRow()][testLoc.getColumn()] = currentLoc;
                        mazeSolvable = true;
                    }
                    else if (maze.getSquare(testLoc) != '#') {
                        agenda.addLocation(testLoc);
                        added.add(testLoc);
                        gui.addLocToAgenda(testLoc);
                        fakeMaze[testLoc.getRow()][testLoc.getColumn()] = currentLoc;
                    }
                        //check that testLoc is not a wall, has not been already added and is within the boundaries of the maze
                        //(its row and column are <= the max row and max col
                }
            }
        }

        if(!mazeSolvable) {
            pathSeq = new ArrayList<>();
            System.out.println("Your maze can't be solved!");
        }
        else {
            pathSeq = new ArrayList<>();
            pathSeq.add(maze.getGoalLocation());
            gui.addLocToPath(maze.getGoalLocation());
            GridLocation loc = fakeMaze[goal.getRow()][goal.getColumn()];
            while (! maze.getLocation(loc.getRow(), loc.getColumn()).equals(maze.getStartLocation())) {
                pathSeq.add(maze.getLocation(loc.getRow(), loc.getColumn()));
                gui.addLocToPath(maze.getLocation(loc.getRow(), loc.getColumn()));
                loc = fakeMaze[loc.getRow()][loc.getColumn()];
            }
            pathSeq.add(maze.getStartLocation());
            gui.addLocToPath(maze.getStartLocation());
            Collections.reverse(pathSeq);
        }
        return pathSeq;
    }
}
