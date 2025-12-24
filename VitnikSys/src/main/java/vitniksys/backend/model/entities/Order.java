package vitniksys.backend.model.entities;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.OrderType;

public class Order extends VitnikSearchableEntity
{
    private String articleId;

    //Entity properties
    private Integer code;
    private Integer deliveryNumber;
    private Integer quantity;
    private Integer returnedQuantity;
    private Float cost;
    private Timestamp withdrawalDate;
    private Timestamp registrationTime;
    private boolean commissionable;
    private boolean countForCommission;
    private OrderType type;
    private Float unitPrice;
    private boolean aggregated;
    private boolean compensated;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;
    private Article article;

    //Others
    private boolean active;

    public Order(Integer quantity, Float cost)
    {
        this.quantity = quantity;
        this.cost = cost;
    }
    
    public Order(Integer code, Integer quantity, Float cost, boolean commissionable, boolean countForCommission)
    {
        this.code = code;
        this.quantity = quantity;
        this.cost = cost;
        this.commissionable = commissionable;
        this.countForCommission = countForCommission;
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

    public Integer getReturnedQuantity()
    {
        return this.returnedQuantity;
    }

    public void setReturnedQuantity(Integer returnedQuantity)
    {
        this.returnedQuantity = returnedQuantity;
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

    public boolean isCountForCommission()
    {
        return this.countForCommission;
    }

    public void setCountForCommission(boolean countForCommission)
    {
        this.countForCommission = countForCommission;
    }

    public OrderType getType()
    {
        return this.type;
    }

    public void setType(OrderType type)
    {
        this.type = type;
    }

    public Float getUnitPrice()
    {
        return this.unitPrice;
    }

    public void setUnitPrice(Float unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public boolean isAggregated()
    {
        return this.aggregated;
    }

    public void setAggregated(boolean aggregated)
    {
        this.aggregated = aggregated;
    }

    public boolean isCompensated()
    {
        return this.compensated;
    }

    public void setCompensated(boolean compensated)
    {
        this.compensated = compensated;
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