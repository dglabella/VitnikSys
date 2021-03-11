package vitniksys.backend.util;

import java.util.List;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.Repurchase;

public class RepurchasesRowTable
{
    private Integer cod;
    private Integer unitCode;
    private Integer deliveryNumber;
    private String articleId;
    private Float cost;
    private Float repurchaseCost;
    private String name;
    private ArticleType articleType;
    private Timestamp registrationTime;
    private CheckBox countForCommission;

    private Repurchase repurchase;

    
    public RepurchasesRowTable(Integer cod, Integer unitCode, Integer deliveryNumber, String articleId, Float cost, Float repurchaseCost, 
        String name, ArticleType articleType, Timestamp registrationTime, boolean countForCommission, Repurchase repurchase)
    {
        this.cod = cod;
        this.unitCode = unitCode;
        this.deliveryNumber = deliveryNumber;
        this.articleId = articleId;
        this.cost = cost;
        this.repurchaseCost = repurchaseCost;
        this.name = name;
        this.articleType = articleType;
        this.registrationTime = registrationTime;
        this.countForCommission = new CheckBox();
        this.countForCommission.setSelected(countForCommission);

        this.repurchase = repurchase;

        this.countForCommission.selectedProperty().addListener
        (
            (ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> 
            {
                this.repurchase.setCountForCommission(newValue);
            }
        );
    }

    public static List<RepurchasesRowTable> generateRows(List<Repurchase> repurchases)
    {
        List<RepurchasesRowTable> ret = new ArrayList<>();

        if(repurchases != null)
        {
            Repurchase repurchase = null;
            Iterator<Repurchase> it = repurchases.iterator();
            while(it.hasNext())
            {
                repurchase = it.next();

                ret.add(new RepurchasesRowTable(repurchase.getCode(), repurchase.getReturnedArticleId(), repurchase.getReturnedArticle().getOrder().getDeliveryNumber(), repurchase.getReturnedArticle().getOrder().getArticleId(), 
                                                repurchase.getReturnedArticle().getOrder().getArticle().getUnitPrice(), repurchase.getCost(), repurchase.getReturnedArticle().getOrder().getArticle().getName(), 
                                                repurchase.getReturnedArticle().getOrder().getArticle().getType(), repurchase.getRegistrationTime(), repurchase.isCountForCommission(), repurchase));
            }
        }

        return ret;
    }

    //Getters && Setters
    public Integer getCod()
    {
        return this.cod;
    }

    public void setCod(Integer cod)
    {
        this.cod = cod;
    }

    public Integer getUnitCode()
    {
        return this.unitCode;
    }

    public void setUnitCode(Integer unitCode)
    {
        this.unitCode = unitCode;
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

    public CheckBox getCountForCommission()
    {
        return this.countForCommission;
    }

    public void setCountForCommission(CheckBox countForCommission)
    {
        this.countForCommission = countForCommission;
    }

    public Repurchase getRepurchase()
    {
        return this.repurchase;
    }

    public void setRepurchase(Repurchase repurchase)
    {
        this.repurchase = repurchase;
    }
}