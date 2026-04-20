import java.io.*;
import java.util.ArrayList;


public class HotelFacade {
    
    private static final String CUSTOMER_FILE = "customers.dat";
    private static final String ROOM_FILE = "rooms.dat";
    private static final String RESERVATION_FILE = "reservations.dat";
    
    
    public void saveCustomers(ArrayList<Customer> customers) {
        saveToFile(customers, CUSTOMER_FILE);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = (ArrayList<Customer>) loadFromFile(CUSTOMER_FILE);
        return customers != null ? customers : new ArrayList<>();
    }
    
    
    public void saveRooms(ArrayList<Room> rooms) {
        saveToFile(rooms, ROOM_FILE);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Room> loadRooms() {
        ArrayList<Room> rooms = (ArrayList<Room>) loadFromFile(ROOM_FILE);
        return rooms != null ? rooms : new ArrayList<>();
    }
    
    
    public void saveReservations(ArrayList<Reservation> reservations) {
        saveToFile(reservations, RESERVATION_FILE);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Reservation> loadReservations() {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) loadFromFile(RESERVATION_FILE);
        return reservations != null ? reservations : new ArrayList<>();
    }
    
    
    private void saveToFile(Object data, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.err.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }
    
    
    private Object loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public boolean hasCustomerData() {
        return new File(CUSTOMER_FILE).exists();
    }
    
    public boolean hasRoomData() {
        return new File(ROOM_FILE).exists();
    }
    
    public boolean hasReservationData() {
        return new File(RESERVATION_FILE).exists();
    }
    
    
    public void clearAllData() {
        deleteFile(CUSTOMER_FILE);
        deleteFile(ROOM_FILE);
        deleteFile(RESERVATION_FILE);
    }
    
    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
