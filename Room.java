import java.io.Serializable;

public class Room implements Serializable 
{
    private int roomNo;
    private RoomType roomType;
    private double price;

    public Room(int roomNo, RoomType roomType, double price) 
    {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.price = price;
    }
    public void setRoomNo(int roomNO)
    {
        this.roomNo=roomNo;
    }

    public int getRoomNo() 
    {
        return roomNo;
    }
    public void setRoomType(RoomType roomType)
    {
        this.roomType=roomType;
    }
   
    public RoomType getRoomType() 
    {
        return roomType;
    } 
    public void setPrice(double price)
    {
        this.price=price;
    }
    public double getPrice()
    {
        return price;
    }
}
