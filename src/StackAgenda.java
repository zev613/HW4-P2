import java.util.ArrayList;
import java.util.Stack;

public class StackAgenda extends Agenda {
    private Stack<GridLocation> stack;

    //Used for testing only
    /*
    public static void main(String[] args) {
        StackAgenda agenda = new StackAgenda();
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridLocation tempLoc = new GridLocation(i,j);
                agenda.addLocation(tempLoc);
            }
        }
        System.out.println("agenda toString: " + agenda.toString());
        StackAgenda agenda2 = new StackAgenda();
        agenda2.addLocation(agenda.getLocation());
        agenda2.addLocation(agenda.getLocation());
        agenda2.addLocation(agenda.getLocation());
        agenda2.addLocation(agenda.getLocation());
        agenda2.addLocation(agenda.getLocation());
        System.out.println("agenda toString(): " + agenda.toString());
        System.out.println("agenda2 to String(): " + agenda2.toString());
        System.out.println("agendas2 getLocation: " + agenda2.getLocation());
        System.out.println("size agenda2: " + agenda2.size());
        System.out.println("agenda isEmpty(): " + agenda.isEmpty());
        agenda.clear();
        System.out.println("agenda isEmpty() (after clear): " + agenda.isEmpty());
        System.out.println("agenda toString(): " + agenda.toString());
    }*/

    /**
     *StackAgenda Constructor
     *
     */
    public StackAgenda() {
        stack = new Stack<GridLocation>();
    }

    /**
     * Pushes the new location to the agenda
     * @param loc
     */
    @Override
    public void addLocation(GridLocation loc) {
        this.stack.add(this.size(), loc); //push the location to the agenda
    }

    /**
     * Pops the top item from the agenda
     * @return popped item
     */
    @Override
    public GridLocation getLocation() {
        return this.stack.remove(this.size() - 1); //pop the item from the top of the agenda
    }

    /**
     * Returns the size of the stack agenda
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        if (this.stack.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Clears the agenda
     */
    @Override
    public void clear() {
        this.stack.clear();
    }

    /**
     *
     * @return String representing agenda
     */
    @Override
    public String toString() {
        return this.stack.toString();
    }
}
