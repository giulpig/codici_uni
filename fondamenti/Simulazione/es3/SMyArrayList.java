////Giulio Coduti Matricola 2008795

public class SMyArrayList<E extends Comparable<E>> extends MyArrayList<E> {
    @Override
    public E min() {

        if(isEmpty())
            return null;

        return elementAt(0);
            
    }
}
