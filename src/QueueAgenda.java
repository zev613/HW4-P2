import java.util.LinkedList;
import java.util.Queue;

public class QueueAgenda extends Agenda {
    LinkedList<GridLocation> list;
    int numItems;

    public QueueAgenda() {
        Queue queue = new LinkedList<GridLocation>();
    }

    @Override
    public void addLocation(GridLocation loc) {
        list.add(numItems, loc);
        numItems++;
    }

    @Override
    public GridLocation getLocation() {
        numItems--;
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        if (numItems == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        list = new LinkedList<GridLocation>();
        numItems = 0;
    }

    @Override
    public String toString() {
        String s = list.toString();
        return s;
    }
}