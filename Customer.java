import java.io.Serializable;
import java.util.ArrayList;

// =============================================
// ITERATOR PATTERN
// =============================================

interface CustomerIterator {
    boolean hasNext();
    Customer next();
}

class CustomerList implements Serializable {
    private ArrayList<Customer> list = new ArrayList<>();

    public void add(Customer c)          { list.add(c); }
    public void remove(int index)        { list.remove(index); }
    public ArrayList<Customer> getAll()  { return list; }
    public int size()                    { return list.size(); }

    // Returns an iterator to loop through customers
    public CustomerIterator iterator() {
        return new CustomerIterator() {
            private int index = 0;

            public boolean hasNext() { return index < list.size(); }
            public Customer next()   { return list.get(index++); }
        };
    }
}

// =============================================
// Customer - data class
// =============================================
public class Customer implements Serializable {

    private int customerId;
    private String name;
    private Gender gender;

    public Customer(int customerId, String name, Gender gender) {
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
    }

    public int    getCustomerId()               { return customerId; }
    public void   setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName()                     { return name; }
    public void   setName(String name)          { this.name = name; }

    public Gender getGender()                   { return gender; }
    public void   setGender(Gender gender)      { this.gender = gender; }
}
