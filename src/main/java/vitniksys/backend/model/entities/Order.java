package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class Order extends VitnikSearchableEntity
{
    private String articleId;

    //Entity properties
    private Integer code;
    private Integer deliveryNumber;
    private Integer quantity;
    private Float cost;
    private Timestamp withdrawalDate;
    private Timestamp registrationTime;
    private boolean commissionable;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;
    private Article article;

    //Others
    private boolean active;

    public Order(Integer quantity, Float cost, boolean commissionable)
    {
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
    }
    
    public Order(Integer code, Integer quantity, Float cost, boolean commissionable)
    {
        this.code = code;
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
    }

    //Getters && Setters
    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }
    
    /**
     * 
     * @return return the BD table key (column name: cod).
     */
    public Integer getCode()
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: cod).
     */
    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Integer getDeliveryNumber()
    {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber)
    {
        this.deliveryNumber = deliveryNumber;
    }

    public Integer getQuantity()
    {
        return this.quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Float getCost()
    {
        return this.cost;
    }

    public void setCost(Float cost)
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

    public PreferentialClient getClient()
    {
        return this.client;
    }

    public void setClient(PreferentialClient client)
    {
        this.client = client;
    }

    public Campaign getCampaign()
    {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign)
    {
        this.campaign = campaign;
    }

    public Article getArticle()
    {
        return this.article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }
}