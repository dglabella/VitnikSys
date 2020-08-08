package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class CatalogueDeliver
{
    //Entity properties
    private int code;
    private int quantity;
    private float cost;
    private Timestamp registrationTime;

    //Domain Associations
    private Catalogue catalogue;
    private PreferentialClient client;
    
    //Others
    private boolean active;

    public CatalogueDeliver(int code, int quantity, float cost)
    {
        this.code = code;
        this.quantity = quantity;
        this.cost = cost;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: nro_entrega).
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: nro_entrega).
     */
    public void setCode(int code)
    {
        this.code = code;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public float getCost()
    {
        return this.cost;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
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

    public Catalogue getCatalogo()
    {
        return this.catalogue;
    }

    public void setCatalogo(Catalogue catalogue)
    {
        this.catalogue = catalogue;
    }

    public PreferentialClient getCliente()
    {
        return this.client;
    }

    public void setCliente(PreferentialClient client)
    {
        this.client = client;
    }
}