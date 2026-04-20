import java.util.ArrayList;

public class DeleteCustomerCommand implements Command {
    private HotelDataState state;
    private int index;

    public DeleteCustomerCommand(HotelDataState state, int index) {
        this.state = state;
        this.index = index;
    }

    @Override
    public void execute() {
        ArrayList<Customer> customers = state.getCustomers();
        if(index >= 0 && index < customers.size()) {
            customers.remove(index);
            state.setCustomers(customers);
        }
    }
}
