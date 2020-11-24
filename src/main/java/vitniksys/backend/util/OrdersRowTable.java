package vitniksys.backend.util;

import java.sql.Timestamp;
import javafx.scene.control.CheckBox;
import vitniksys.backend.model.enums.ArticleType;

public class OrdersRowTable
{
    private Integer code;
    private Integer deliveryNumber;
    private Integer quantity;
    private Float cost;
    private Float commission;
    private String name;
    private ArticleType type;
    private String articleId; // Table id = letra
    private Timestamp withdrawalDate;
    private boolean commissionable;//private CheckBox commissionable;


    // Getters && Setters
    public Integer getCode()
    {
        return this.code;
    }

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

    public Float getCommission()
    {
        return this.commission;
    }

    public void setCommission(Float commission)
    {
        this.commission = commission;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArticleType getType()
    {
        return this.type;
    }

    public void setType(ArticleType type)
    {
        this.type = type;
    }

    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }

    public Timestamp getWithdrawalDate()
    {
        return this.withdrawalDate;
    }

    public void setWithdrawalDate(Timestamp withdrawalDate)
    {
        this.withdrawalDate = withdrawalDate;
    }

    public boolean isCommissionable()
    {
        return this.commissionable;
    }

    public void setCommissionable(boolean commissionable)
    {
        this.commissionable = commissionable;
    }
}