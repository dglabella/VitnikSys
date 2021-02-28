package vitniksys.backend.model.entities;

import vitniksys.backend.model.enums.Reason;

public class ReturnedArticle
{
    //fk id
    private Integer orderId;

    //Entity properties
    private int unitCode; //Table id = ejemplar
    private Reason reason;
    private boolean repurchased;

    //Domain Associations
    private Order order;

    //Others
    private boolean active;

    public ReturnedArticle(Reason reason)
    {
        this.reason = reason;
    }

    public ReturnedArticle(int unitCode, Reason reason, boolean repurchased)
    {
        this.unitCode = unitCode;
        this.reason = reason;
        this.repurchased = repurchased;
    }
    
    //Getters && Setters
    public Integer getOrderId()
    {
        return this.orderId;
    }

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    /**
     * 
     * @return return the BD table key (column name: ejemplar).
     */
    public int getUnitCode()
    {
        return this.unitCode;
    }

    /**
     * 
     * @param unitCode set the BD table key (column name: ejemplar).
     */
    public void setUnitCode(int unitCode)
    {
        this.unitCode = unitCode;
    }

    public Reason getReason()
    {
        return this.reason;
    }

    public void setReason(Reason reason)
    {
        this.reason = reason;
    }

    public boolean isRepurchased()
    {
        return this.repurchased;
    }

    public void setRepurchased(boolean repurchased)
    {
        this.repurchased = repurchased;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Order getOrder()
    {
        return this.order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
}