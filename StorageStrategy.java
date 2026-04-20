public interface StorageStrategy 
{
    void save(HotelDataState state);
    HotelDataState load();
}
