import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private int numRows = 0;
    private int numCols = 0;

    private GridLocation [][] maze;

    private GridLocation start;
    private GridLocation goal;

    private ArrayList<GridLocation> spaces;
    private ArrayList<String> lines;

    public static void main(String[] args) {
        try { //If file not found, throw FNF exception
            Maze testMaze = new Maze(args[0]); //gets maze filename from command-line argument
            System.out.println("testMaze toString(): \n" + testMaze.toString()); //test toString() method, if its working, everything else should be too.
        } catch (FileNotFoundException e) {
            System.out.println("Error! File was not Found!!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Maze Constructor, creates a maze from a filename
     * @param fileName
     * @throws FileNotFoundException
     */
    public Maze(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanFile = new Scanner(file);
        String[] lineSplit = scanFile.nextLine().split(" ");
        numRows = Integer.parseInt(lineSplit[0]); //use 1st value from world file for rows
        numCols = Integer.parseInt(lineSplit[1]); //use 2nd value from world file for cols

        maze = new GridLocation[numRows][numCols]; //create new instance of world

        spaces = new ArrayList<GridLocation>(0);
        lines = new ArrayList<String>(0);

        for (int i = 0; i < numRows; i++) { //iterate for num of Rows, processing each char as part of the maze
            String tempLine = scanFile.nextLine();
            lines.add(tempLine);
            String[] thisLine = tempLine.split(""); //Split the current line into individual chars
            for (int j = 0; j < numCols; j++) { //iterate for num of Columns, processing each char as a part of the maze
                maze[i][j] = new GridLocation(i, j); //make new location in maze from current indices
                if (thisLine[j].equals("o")) {
                    start = new GridLocation(i,j);
                    //is Start Location
                }
                else if (thisLine[j].equals("*")) {
                    goal = new GridLocation(i,j);
                    //is Goal location
                }
                else if (thisLine[j].equals(".")) {
                    spaces.add(new GridLocation(i, j)); //add that location to the list of spaces
                    //is an empty Space location
                }
                else {
                    //is a Wall, don't do anything. Anything that isn't the start, goal or an open space is set to a wall anyway.
                }
            }
        }
    }

    /**
     * returns the Number of Coumns in this maze
     * @return n, num of columns
     */
    public Integer getNumColumns() {
        int n = numCols;
        return n;
    }

    /**
     * Returns the number of rows in this maze.
     * @return n, num of rows
     */
    public Integer getNumRows() {
        int n = numRows;
        return n;
    }

    /**
     * Returns the start location for this maze.
     * @return loc, the start location
     */
    public GridLocation getStartLocation() {
        GridLocation loc = start;
        return loc;
    }

    /**
     * Returns the goal location for this maze.
     * @return loc, the goal location.
     */
    public GridLocation getGoalLocation() {
        GridLocation loc = goal;
        return loc;
    }

    /**
     * Gets a GridLocation of the maze from row and col int values
     * @param row
     * @param col
     * @return the corresponding location from maze.
     */
    public GridLocation getLocation(int row, int col) {
        return this.maze[row][col];
    }

    /**
     * Returns the character representing any given location
     * @param loc
     * @return the corresponding character
     */
    public Character getSquare(GridLocation loc) {
        int r = loc.getRow();
        int c = loc.getColumn();
        if (maze[r][c].equals(this.getStartLocation())) {
            return 'o';
        }
        else if (maze[r][c].equals(this.getGoalLocation())) {
            return '*';
        }
        else if (spaces.contains(maze[r][c])) {
            return '.';
        }
        else {
            return '#';
        }
    }

    /**
     * Returns a String representing the Maze
     * @return s, a String representation of the maze
     */
    public String toString() {
        String s = "";
        for(int i = 0; i < getNumRows(); i++) {
                s+=lines.get(i) + "\n";
            }
        return s;
    }
}