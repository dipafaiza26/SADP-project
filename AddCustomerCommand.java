import java.util.ArrayList;

public class AddCustomerCommand implements Command {
    private HotelDataState state;
    private Customer customer;

    public AddCustomerCommand(HotelDataState state, Customer customer) {
        this.state = state;
        this.customer = customer;
    }

    @Override
    public void execute() {
        ArrayList<Customer> customers = state.getCustomers();
        customers.add(customer);
        state.setCustomers(customers);
    }
}
