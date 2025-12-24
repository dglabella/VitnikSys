package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.OrderType;
import vitniksys.backend.model.entities.ReturnedArticle;

public class StockTableRow
{
    private Integer cp;
    private Integer campNumb;
    private Integer unitCode;
    private Integer deliveryNumber;
    private Float cost;
    private String articleId;
    private String articleName;
    private OrderType orderType;
    private Reason reason;

    
    public StockTableRow(Integer cp, Integer campNumb, Integer unitCode, Integer deliveryNumber, Float cost, String articleId, String articleName, OrderType orderType, Reason reason)
    {
        this.cp = cp;
        this.campNumb = campNumb;
        this.unitCode = unitCode;
        this.deliveryNumber = deliveryNumber;
        this.cost = cost;
        this.articleId = articleId;
        this.articleName = articleName;
        this.orderType = orderType;
        this.reason = reason;
    }

    public static List<StockTableRow> generateRows(List<ReturnedArticle> returnedArticles)
    {
        List<StockTableRow> ret = new ArrayList<>();

        if(returnedArticles != null)
        {
            ReturnedArticle returnedArticle = null;
            Iterator<ReturnedArticle> it = returnedArticles.iterator();
            while(it.hasNext())
            {
                returnedArticle = it.next();
                ret.add
                (
                    new StockTableRow
                    (
                        returnedArticle.getOrder().getPrefClientId(), returnedArticle.getOrder().getCampNumber(), returnedArticle.getUnitCode(), 
                        returnedArticle.getOrder().getDeliveryNumber(), returnedArticle.getOrder().getCost() / returnedArticle.getOrder().getQuantity(), 
                        returnedArticle.getOrder().getArticle().getId(), returnedArticle.getOrder().getArticle().getName(), returnedArticle.getOrder().getType(), 
                        returnedArticle.getReason()
                    )
                );
            }
        }

        return ret;
    }

    //Getting && Setters
    public Integer getCp()
    {
        return this.cp;
    }

    public void setCp(Integer cp)
    {
        this.cp = cp;
    }

    public Integer getCampNumb()
    {
        return this.campNumb;
    }

    public void setCampNumb(Integer campNumb)
    {
        this.campNumb = campNumb;
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

    public Float getCost()
    {
        return this.cost;
    }

    public void setCost(Float cost)
    {
        this.cost = cost;
    }

    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }

    public String getArticleName()
    {
        return this.articleName;
    }

    public void setArticleName(String articleName)
    {
        this.articleName = articleName;
    }

    public OrderType getOrderType()
    {
        return this.orderType;
    }

    public void setOrderType(OrderType orderType)
    {
        this.orderType = orderType;
    }

    public Reason getReason()
    {
        return this.reason;
    }

    public void setReason(Reason reason)
    {
        this.reason = reason;
    }
}