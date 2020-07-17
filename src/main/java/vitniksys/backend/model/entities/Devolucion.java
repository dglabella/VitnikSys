package vitniksys.backend.model.entities;

import java.sql.Timestamp;

import vitniksys.backend.model.enums.Motivo;

public class Devolucion
{
    //Entity properties
    private int code;
    private int quantity;
    private float cost;
    private Motivo reason;
    private Timestamp registrationTime;

    //Domain Associations
    private ClientePreferencial cliente;
    private Articulo articulo;
    private Camp camp;

    //Others
    private boolean active;

    public Devolucion(int code, int quantity, float cost, Motivo reason, Timestamp registrationTime)
    {
        this.code = code;
        this.quantity = quantity;
        this.cost = cost;
        this.reason = reason;
        this.registrationTime = registrationTime;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: cod).
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: cod).
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

    public Motivo getReason()
    {
        return this.reason;
    }

    public void setReason(Motivo reason)
    {
        this.reason = reason;
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

    public ClientePreferencial getCliente()
    {
        return this.cliente;
    }

    public void setCliente(ClientePreferencial cliente)
    {
        this.cliente = cliente;
    }

    public Articulo getArticulo()
    {
        return this.articulo;
    }

    public void setArticulo(Articulo articulo)
    {
        this.articulo = articulo;
    }

    public Camp getCamp()
    {
        return this.camp;
    }

    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }
}