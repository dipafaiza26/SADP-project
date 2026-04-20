import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

// =============================================
// COMMAND PATTERN
// =============================================

interface ReservationCommand {
    void execute();
}

class AddReservationCommand implements ReservationCommand {
    private ArrayList<Reservation> list;
    private Reservation reservation;

    public AddReservationCommand(ArrayList<Reservation> list, Reservation r) {
        this.list        = list;
        this.reservation = r;
    }

    public void execute() {
        list.add(reservation);
        persist(list);
    }

    private void persist(ArrayList<Reservation> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("reservations.dat"));
            oos.writeObject(list);
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}

class DeleteReservationCommand implements ReservationCommand {
    private ArrayList<Reservation> list;
    private int index;

    public DeleteReservationCommand(ArrayList<Reservation> list, int index) {
        this.list  = list;
        this.index = index;
    }

    public void execute() {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            persist(list);
        }
    }

    private void persist(ArrayList<Reservation> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("reservations.dat"));
            oos.writeObject(list);
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}

// Invoker: just calls execute() on whatever command it receives
class ReservationInvoker {
    public void run(ReservationCommand command) {
        command.execute();
    }
}

// =============================================
// Reservation - data class
// =============================================
public class Reservation implements Serializable {

    private int  reservationId;
    private int  roomNo;
    private int  customerId;
    private Date reservationDate;

    public Reservation(int reservationId, int roomNo, int customerId, Date reservationDate) {
        this.reservationId   = reservationId;
        this.roomNo          = roomNo;
        this.customerId      = customerId;
        this.reservationDate = reservationDate;
    }

    public int  getReservationId()                       { return reservationId; }
    public void setReservationId(int reservationId)      { this.reservationId = reservationId; }

    public int  getRoomNo()                              { return roomNo; }
    public void setRoomNo(int roomNo)                    { this.roomNo = roomNo; }

    public int  getCustomerId()                          { return customerId; }
    public void setCustomerId(int customerId)            { this.customerId = customerId; }

    public Date getReservationDate()                     { return reservationDate; }
    public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }
}
