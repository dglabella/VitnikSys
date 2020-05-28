package vitniksys.backend.model;

import java.sql.Timestamp;

public class EntregaCatalogo
{
    //Entity properties
    private int code;
    private int quantity;
    private float cost;
    private Timestamp registrationTime;

    //Domain Associations
    private Catalogo catalogo;
    private ClientePreferencial cliente;
    
    //Others
    private boolean active;

    public EntregaCatalogo(int code, int quantity, float cost)
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

    public Catalogo getCatalogo()
    {
        return this.catalogo;
    }

    public void setCatalogo(Catalogo catalogo)
    {
        this.catalogo = catalogo;
    }

    public ClientePreferencial getCliente()
    {
        return this.cliente;
    }

    public void setCliente(ClientePreferencial cliente)
    {
        this.cliente = cliente;
    }
}