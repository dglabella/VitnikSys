package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.ArticleType;

public class RepurchasesRowTable
{
    private Integer devCode;
    private Integer deliveryNumber;
    private String articleId;
    private Float cost;
    private Float repurchaseCost;
    private String name;
    private ArticleType articleType;
    private Timestamp registrationTime;

    public RepurchasesRowTable(Integer devCode, Integer deliveryNumber, String articleId, Float cost, 
        Float repurchaseCost, String name, ArticleType articleType, Timestamp registrationTime)
    {
        this.devCode = devCode;
        this.deliveryNumber = deliveryNumber;
        this.articleId = articleId;
        this.cost = cost;
        this.repurchaseCost = repurchaseCost;
        this.name = name;
        this.articleType = articleType;
        this.registrationTime = registrationTime;
    }

    //Getters && Setters
    public Integer getDevCode()
    {
        return this.devCode;
    }

    public void setDevCode(Integer devCode)
    {
        this.devCode = devCode;
    }

    public Integer getDeliveryNumber()
    {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber)
    {
        this.deliveryNumber = deliveryNumber;
    }

    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }

    public Float getCost()
    {
        return this.cost;
    }

    public void setCost(Float cost)
    {
        this.cost = cost;
    }

    public Float getRepurchaseCost()
    {
        return this.repurchaseCost;
    }

    public void setRepurchaseCost(Float repurchaseCost)
    {
        this.repurchaseCost = repurchaseCost;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArticleType getArticleType()
    {
        return this.articleType;
    }

    public void setArticleType(ArticleType articleType)
    {
        this.articleType = articleType;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }
}