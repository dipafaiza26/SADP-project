public class RoomFactory {
    
    public static Room createRoom(int roomNo, RoomType roomType) {
        double price;
        switch (roomType) {
            case SINGLE: price = 100.0; break;
            case DOUBLE: price = 150.0; break;
            case DELUXE: price = 250.0; break;
            default: throw new IllegalArgumentException("Unknown type");
        }
        return new Room(roomNo, roomType, price);
    }
    
    public static Room createRoomWithCustomPrice(int roomNo, RoomType roomType, double price) {
        return new Room(roomNo, roomType, price);
    }
    
    public static Room createRoomWithDiscount(int roomNo, RoomType roomType, double discountPercent) {
        double originalPrice;
        switch (roomType) {
            case SINGLE: originalPrice = 100.0; break;
            case DOUBLE: originalPrice = 150.0; break;
            case DELUXE: originalPrice = 250.0; break;
            default: throw new IllegalArgumentException("Unknown type");
        }
        double discountedPrice = originalPrice * (1 - discountPercent / 100);
        return new Room(roomNo, roomType, discountedPrice);
    }
    
    public static Room[] createMultipleRooms(int startRoomNo, RoomType roomType, int count) {
        Room[] rooms = new Room[count];
        for (int i = 0; i < count; i++) {
            rooms[i] = createRoom(startRoomNo + i, roomType);
        }
        return rooms;
    }
}
