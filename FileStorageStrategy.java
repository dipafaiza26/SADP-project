import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileStorageStrategy implements StorageStrategy {
    private final String filename;

    public FileStorageStrategy(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(HotelDataState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HotelDataState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (HotelDataState) ois.readObject();
        } catch (Exception e) {
            // Return a new empty state if file doesn't exist or error occurs
            return new HotelDataState();
        }
    }
}
