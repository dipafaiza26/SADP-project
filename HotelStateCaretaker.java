import java.util.Stack;

public class HotelStateCaretaker {
    private Stack<HotelStateMemento> mementoHistory = new Stack<>();

    public void saveState(HotelDataState state) {
        mementoHistory.push(state.saveToMemento());
    }

    public void restoreState(HotelDataState state) {
        if (!mementoHistory.isEmpty()) {
            HotelStateMemento memento = mementoHistory.pop();
            state.restoreFromMemento(memento);
        }
    }

    public boolean hasHistory() {
        return !mementoHistory.isEmpty();
    }
}
