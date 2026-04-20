import java.util.Date;

public class HotelFactory {
    public static Customer createCustomer(int id, String name, Gender gender) {
        return new Customer(id, name, gender);
    }

    public static Room createRoom(int roomNo, RoomType type, double price) {
        return new Room(roomNo, type, price);
    }

    public static Reservation createReservation(int resId, int roomNo, int customerId, Date date) {
        return new Reservation(resId, roomNo, customerId, date);
    }
}
