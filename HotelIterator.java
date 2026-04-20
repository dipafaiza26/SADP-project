import java.util.List;
import java.util.NoSuchElementException;

public class HotelIterator<T> implements Iterator<T> {
    private List<T> items;
    private int position;

    public HotelIterator(List<T> items) {
        this.items = items;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return items.get(position++);
    }
}
