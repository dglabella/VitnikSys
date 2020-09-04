package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class Order
{
    //Entity properties
    private int code;
    private int quantity;
    private float cost;
    private Timestamp withdrawalDate;
    private Timestamp registrationTime;
    private boolean commissionable;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;
    private Article article;

    //Others
    private boolean active;

    public Order(int quantity, float cost, boolean commissionable)
    {
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
    }

    public Order(int code, int quantity, float cost, boolean commissionable)
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

    public PreferentialClient getClient() {
        return this.client;
    }

    public void setClient(PreferentialClient client) {
        this.client = client;
    }

    public Campaign getCampaign() {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}