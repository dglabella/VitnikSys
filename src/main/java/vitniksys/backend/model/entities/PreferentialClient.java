package vitniksys.backend.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public abstract class PreferentialClient
{
    //Entity properties
    private int id;
    private long dni;
    private String name;
    private String lastName;
    private String location;
    private LocalDate birthdate;
    private String email;
    private long phoneNumber;
    private Timestamp registrationTime;

    //Domain Associations
    private List<Order> orders;
    private List<Devolution> devolutions;
    private List<Repurchase> repurchases;
    private List<Payment> payments;
    private List<Balance> balances;

    //Others
    private boolean active;

    //incomingOrders is supposed to be used for
    //registrate all new orders from this cp
    private List<Order> incomingOrders;

    public PreferentialClient(int id)
    {
        this.id = id;
    }

    public PreferentialClient(int id, String name, String lastName)
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: id_cp).
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * 
     * @param id set the BD table key (column name: id).
     */
    public void setId(int id)
    {
        this.id = id;
    }

    public long getDni()
    {
        return this.dni;
    }

    public void setDni(long dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public LocalDate getBirthdate()
    {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getRegistrationTime() {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public List<Order> getIncomingOrders()
    {
        if(this.incomingOrders == null)
            this.incomingOrders =  new ArrayList<>();
            
        return this.incomingOrders;
    }

    public void setIncomingOrders(List<Order> incomingOrders)
    {
        this.incomingOrders = incomingOrders;
    }
    
    public List<Order> getOrders() {
        if(this.orders == null)
            this.orders = new ArrayList<>();
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Devolution> getDevolutions() {
        return this.devolutions;
    }

    public void setDevolutions(List<Devolution> devolutions) {
        this.devolutions = devolutions;
    }

    public List<Repurchase> getRepurchases() {
        return this.repurchases;
    }

    public void setRepurchases(List<Repurchase> repurchases) {
        this.repurchases = repurchases;
    }

    public List<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Balance> getBalances() {
        return this.balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString()
    {
        return "Id: "+this.id+" -- Name: "+this.name+" -- LastName: "+this.lastName;
    }

    /**
     * This method supplies an easy way to get the Data Acces Object (DAO).
     * Using polymorphism, this method delegates creating a CRUD Operator.
     * This method is supposed to be only available in objects that are
     * instances of a subclass.
     * Call this method to obtain the DAO for this object. 
     * @return the DAO operator.
     */
    public abstract PreferentialClientOperator operator();
}