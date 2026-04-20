import java.util.ArrayList;

public class AddReservationCommand implements Command {
    private HotelDataState state;
    private Reservation reservation;

    public AddReservationCommand(HotelDataState state, Reservation reservation) {
        this.state = state;
        this.reservation = reservation;
    }

    @Override
    public void execute() {
        ArrayList<Reservation> reservations = state.getReservations();
        reservations.add(reservation);
        state.setReservations(reservations);
    }
}
