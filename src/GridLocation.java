import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents a single grid location (i.e., one row/column position)
 * Within a 2D grid in a Graphical User Interface (GUI)
 */
public class GridLocation extends JPanel {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    
    protected int row;
    protected int col;

    protected Color backgroundColor;

    /**
     * Creates a new grid position at a specific location within the grid
     * with a white background color
     *
     * @param r, the row
     * @param c, the column
     */
    public GridLocation(int r, int c) {
	setBorder(new LineBorder(Color.BLACK));

	row = r;
	col = c;

	backgroundColor = Color.WHITE;
	setBackground(backgroundColor);
    }

    /**
     * @return int
     */
    public int getRow() {
	return row;
    }

    /**
     * @return int
     */
    public int getColumn() {
	return col;
    }

    /**
     * Changes the background color of this location
     *
     * @param color
     */
    public void setBackgroundColor(Color color) {
	backgroundColor = color;
	setBackground(backgroundColor);
	repaint();
    }

    /**
     * Simple String representation of this location's row and column
     *
     * @return String as (row, col)
     */
    @Override
    public String toString() {
	return "(" + row + ", " + col +")";
    }

    /**
     * Determines if two (presumably GridLocations) are same 
     * That is same row and column.
     *
     * @param o, some other Object (presumably a GridLocation) to compare with
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
	if (o == null)
	    return false;
	if (o == this)
	    return true;
	if (!(o instanceof GridLocation))
	    return false;

	GridLocation other = (GridLocation)o;
	return row == other.row && col == other.col;
    }
        
    /**
     * This method is used to help ensure that the size
     * of the grid location is always a very specific size<br>
     * YOU DO NOT NEED TO WORRY ABOUT THIS METHOD OR CALL IT EVER
     */
    @Override
    public Dimension getPreferredSize() {
	return new Dimension(WIDTH, HEIGHT);
    }
	
}
