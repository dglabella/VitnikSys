package vitniksys.backend.model.entities;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDate;
import vitniksys.backend.util.VitnikSearchableList;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public abstract class PreferentialClient
{
    //Entity properties
    private Integer id;
    private Long dni;
    private String name;
    private String lastName;
    private String location;
    private LocalDate birthDate;
    private String email;
    private Long phoneNumber;
    private Timestamp registrationTime;

    //Domain Associations
    private VitnikSearchableList<Order> orders;
    private VitnikSearchableList<Devolution> devolutions;
    private VitnikSearchableList<Repurchase> repurchases;
    private VitnikSearchableList<Payment> payments;
    private VitnikSearchableList<Balance> balances;

    //Others
    private boolean active;

    //incomingOrders is supposed to be used for
    //registrate all new orders from this cp
    private List<Order> incomingOrders;

    public PreferentialClient(Integer id)
    {
        this.id = id;
    }

    public PreferentialClient(Integer id, String name, String lastName)
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
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 
     * @param id set the BD table key (column name: id).
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getDni()
    {
        return this.dni;
    }

    public void setDni(Long dni)
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

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public LocalDate getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getRegistrationTime()
    {
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
    
    public VitnikSearchableList<Order> getOrders()
    {
        if(this.orders == null)
            this.orders = new VitnikSearchableList<>();

        return this.orders;
    }

    public void setOrders(VitnikSearchableList<Order> orders)
    {
        this.orders = orders;
    }

    public VitnikSearchableList<Devolution> getDevolutions()
    {
        if(this.devolutions == null)
            this.devolutions = new VitnikSearchableList<>();

        return this.devolutions;
    }

    public void setDevolutions(VitnikSearchableList<Devolution> devolutions)
    {
        this.devolutions = devolutions;
    }

    public VitnikSearchableList<Repurchase> getRepurchases()
    {
        if(this.repurchases == null)
            this.repurchases = new VitnikSearchableList<>();
            
        return this.repurchases;
    }

    public void setRepurchases(VitnikSearchableList<Repurchase> repurchases)
    {
        this.repurchases = repurchases;
    }

    public VitnikSearchableList<Payment> getPayments()
    {
        if(this.payments == null)
            this.payments = new VitnikSearchableList<>();
            
        return this.payments;
    }

    public void setPayments(VitnikSearchableList<Payment> payments)
    {
        this.payments = payments;
    }

    public VitnikSearchableList<Balance> getBalances()
    {
        return this.balances;
    }

    public void setBalances(VitnikSearchableList<Balance> balances)
    {
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