public class HotelFacade {
    private HotelDataState state;
    private HotelStateCaretaker caretaker;
    private StorageStrategy storageStrategy;

    public HotelFacade(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
        this.caretaker = new HotelStateCaretaker();
        // Load state using Strategy
        this.state = this.storageStrategy.load();
    }

    // Save Data
    public void saveData() {
        storageStrategy.save(state);
    }

    // Undo functionality (Memento)
    public void undoLastAction() {
        if (caretaker.hasHistory()) {
            caretaker.restoreState(state);
            saveData();
        }
    }

    private void executeCommand(Command command) {
        caretaker.saveState(state); // Save snapshot before executing
        command.execute();
        saveData(); // Save to persistent storage after change
    }

    // --- Customer Operations ---

    public Iterator<Customer> getCustomers() {
        return new HotelCollection<>(state.getCustomers()).createIterator();
    }

    public void addCustomer(Customer customer) {
        executeCommand(new AddCustomerCommand(state, customer));
    }

    public void deleteCustomer(int index) {
        executeCommand(new DeleteCustomerCommand(state, index));
    }

    // --- Room Operations ---

    public Iterator<Room> getRooms() {
        return new HotelCollection<>(state.getRooms()).createIterator();
    }

    public void addRoom(Room room) {
        executeCommand(new AddRoomCommand(state, room));
    }

    public void deleteRoom(int index) {
        executeCommand(new DeleteRoomCommand(state, index));
    }

    // --- Reservation Operations ---

    public Iterator<Reservation> getReservations() {
        return new HotelCollection<>(state.getReservations()).createIterator();
    }

    public void addReservation(Reservation reservation) {
        executeCommand(new AddReservationCommand(state, reservation));
    }

    public void deleteReservation(int index) {
        executeCommand(new DeleteReservationCommand(state, index));
    }
}
