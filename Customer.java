import java.io.Serializable;

public class Customer implements Serializable 
{
    private int customerId;
    private String name;
    private Gender gender;

    public Customer(int customerId, String name, Gender gender)
     {
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
    }
    public void setCustomerId(int customerId)
    {
        this.customerId=customerId;
    }
    public int getCustomerId()
    {
        return customerId;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }


    public void setGender(Gender gender)
    {
        this.gender=gender;
    }
    public Gender getGender() 
    {
        return gender;
    }
}
