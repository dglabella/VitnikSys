package vitniksys.backend.model.entities;

import java.util.List;
import java.sql.Timestamp;
import vitniksys.backend.model.enums.Mes;

public class Campaign
{
    //Entity properties
    private int number;
    private String name;
    private String alias;
    private Mes month;
    private int year;
    private Timestamp registrationTime;

    //Domain Associations
    private Catalogue catalogue;
    private List<Payment> payments;
    private List<Order> orders;
    private List<Repurchase> repurchases;
    private List<Devolution> devolutions;
    private List<Balance> balances;

    //Others
    private boolean active;
    
    public Campaign(int number)
    {
        this.number = number;
    }

    public Campaign(int number, Mes month, int year)
    {
        this.number = number;
        this.month = month;
        this.year = year;
        this.name = this.month.toString()+this.year;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: nro_camp).
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * 
     * @param number set the BD table key (column name: nro_camp).
     */
    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Mes getMonth()
    {
        return this.month;
    }

    public void setMonth(Mes month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return this.year;
    }

    public void setYear(int year)
    {
        this.year = year;
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

    public Catalogue getCatalogue() {
        return this.catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public List<Payment> getPayments()
    {
        return this.payments;
    }

    public void setPayments(List<Payment> payments)
    {
        this.payments = payments;
    }

    public List<Order> getOrders()
    {
        return this.orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    public List<Repurchase> getRepurchases()
    {
        return this.repurchases;
    }

    public void setRepurchases(List<Repurchase> repurchases)
    {
        this.repurchases = repurchases;
    }

    public List<Devolution> getDevolutions()
    {
        return this.devolutions;
    }

    public void setDevolutions(List<Devolution> devolutions)
    {
        this.devolutions = devolutions;
    }

    public List<Balance> getBalances()
    {
        return this.balances;
    }

    public void setBalances(List<Balance> balances)
    {
        this.balances = balances;
    }
}