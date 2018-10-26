public abstract class Agenda {
    //This abstract class only has abstract methods that are implemented in StackAgenda
    public abstract void addLocation(GridLocation loc);

    public abstract GridLocation getLocation();

    public abstract boolean isEmpty();

    public abstract void clear();

    public abstract String toString();
}
