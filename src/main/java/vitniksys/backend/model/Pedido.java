package vitniksys.backend.model;

import java.sql.Timestamp;

public class Pedido
{
    //Entity properties
    private int code;
    private int quantity;
    private float cost;
    private Timestamp withdrawalDate;
    private Timestamp registrationTime;
    private boolean commissionable;

    //Domain Associations
    private ClientePreferencial cliente;
    private Camp camp;
    private Articulo articulo;

    //Others
    private boolean active;

    public Pedido(int quantity, float cost, boolean commissionable)
    {
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
    }

    public Pedido(int code, int quantity, float cost, boolean commissionable)
    {
        this.code = code;
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
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

    public Timestamp getWithdrawalDate()
    {
        return this.withdrawalDate;
    }

    public void setWithdrawalDate(Timestamp withdrawalDate)
    {
        this.withdrawalDate = withdrawalDate;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public boolean isCommissionable()
    {
        return this.commissionable;
    }

    public void setCommissionable(boolean commissionable)
    {
        this.commissionable = commissionable;
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

    public Camp getCamp()
    {
        return this.camp;
    }

    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }

    public Articulo getArticulo()
    {
        return this.articulo;
    }

    public void setArticulo(Articulo articulo)
    {
        this.articulo = articulo;
    }
}