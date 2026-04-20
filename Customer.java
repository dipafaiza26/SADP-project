import java.io.Serializable;

public class Customer implements Serializable {
    private int customerId;
    private String name;
    private Gender gender;

    
    private static Customer currentCustomer = null;

    
    public Customer(int customerId, String name, Gender gender) {
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
    }

    // Singleton access
    public static Customer getCurrentCustomer() {
        if (currentCustomer == null) {
            currentCustomer = new Customer(0, "Guest", Gender.MALE);
        }
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }

    
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public Gender getGender() { return gender; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public void setGender(Gender gender) { this.gender = gender; }
}