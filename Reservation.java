import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable 
{
    private int reservationId;
    private int roomNo;
    private int customerId;
    private Date reservationDate;

    public Reservation(int reservationId, int roomNo, int customerId, Date reservationDate) 
    {
        this.reservationId = reservationId;
        this.roomNo = roomNo;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
    }
    public void setReservationId(int reservationId) 
    {
        this.reservationId=reservationId;
    }

    public int getReservationId() 
    {
        return reservationId;
    }
    public void setRoomNo(int roomNo)
    {
        this.roomNo=roomNo;
    }

    public int getRoomNo()
     {
        return roomNo;
    }
    public void setetCustomerId(int customerId)
    {
        this.customerId=customerId;
    }
    public int getCustomerId() 
    {
        return customerId;
    }
    public void setReservationDate (Date reservationDate) 
    {
        this.reservationDate=reservationDate;
    }

    public Date getReservationDate() 
    {
        return reservationDate;
    }
}
