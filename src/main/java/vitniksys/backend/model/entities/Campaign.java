package vitniksys.backend.model.entities;

import java.util.List;
import java.time.Month;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Campaign
{
    //fk id
    private Integer catalogueCode;

    //Entity properties
    private Integer number;
    private String alias;
    private Integer month;
    private Integer year;
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

    //incomingOrders is supposed to be used for
    //registrate all new orders from this cp
    private List<Order> incomingOrders;
    
    public Campaign(Integer number)
    {
        this.number = number;
    }

    public Campaign(Integer number, Integer month, Integer year)
    {
        this.number = number;
        this.month = month;
        this.year = year;
    }

    //Getters && Setters
    public Integer getCatalogueCode()
    {
        return this.catalogueCode;
    }

    public void setCatalogueCode(Integer catalogueCode)
    {
        this.catalogueCode = catalogueCode;
    }

    /**
     * 
     * @return return the BD table key (column name: nro_camp).
     */
    public Integer getNumber()
    {
        return this.number;
    }

    /**
     * 
     * @param number set the BD table key (column name: nro_camp).
     */
    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Integer getMonth()
    {
        return this.month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Month getEnumMonth()
    {
        return Month.of(this.month);
    }

    public Integer getYear()
    {
        return this.year;
    }

    public void setYear(Integer year)
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

    public Catalogue getCatalogue()
    {
        return this.catalogue;
    }

    public void setCatalogue(Catalogue catalogue)
    {
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

    @Override
    public String toString()
    {
        return ""+Month.of(this.getMonth())+"--"+this.getYear()+"--"+(this.alias!=null?this.alias:"S/N")+"--"+this.number;
    }
}