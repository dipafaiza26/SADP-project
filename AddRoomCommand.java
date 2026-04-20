import java.util.ArrayList;

public class AddRoomCommand implements Command {
    private HotelDataState state;
    private Room room;

    public AddRoomCommand(HotelDataState state, Room room) {
        this.state = state;
        this.room = room;
    }

    @Override
    public void execute() {
        ArrayList<Room> rooms = state.getRooms();
        rooms.add(room);
        state.setRooms(rooms);
    }
}
