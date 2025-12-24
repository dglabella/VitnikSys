package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.OrderType;
import vitniksys.backend.model.entities.Devolution;

public class DevolutionsRowTable
{
    private Integer campNumber;
    private Integer prefClientId;
    private Integer unitCode;
    private Integer deliveryNumber;
    private Float cost;
    private String articleId;
    private String articleName;
    private OrderType orderType;
    private Reason reason;

    public DevolutionsRowTable(Integer campNumber, Integer prefClientId, Integer unitCode, Integer deliveryNumber, Float cost, 
            String articleId, String articleName, OrderType orderType, Reason reason)
    {
        this.campNumber = campNumber;
        this.prefClientId = prefClientId;
        this.unitCode = unitCode;
        this.deliveryNumber = deliveryNumber;
        this.cost = cost;
        this.articleId = articleId;
        this.articleName = articleName;
        this.orderType = orderType;
        this.reason = reason;
    }

    public static List<DevolutionsRowTable> generateRows(List<Devolution> devolutions)
    {
        List<DevolutionsRowTable> ret = new ArrayList<>();

        Devolution devolution = null;
        if(devolutions != null)
        {
            Iterator<Devolution> it = devolutions.iterator();
            while(it.hasNext())
            {
                devolution = it.next();
                ret.add(new DevolutionsRowTable(devolution.getCampNumber(), devolution.getPrefClientId(), devolution.getUnitCode(), devolution.getReturnedArticle().getOrder().getDeliveryNumber(), 
                        devolution.getCost(), devolution.getReturnedArticle().getOrder().getArticleId(), devolution.getReturnedArticle().getOrder().getArticle().getName(), 
                        devolution.getReturnedArticle().getOrder().getType(), devolution.getReturnedArticle().getReason()));
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public Integer getCampNumber()
    {
        return this.campNumber;
    }

    public void setCampNumber(Integer campNumber)
    {
        this.campNumber = campNumber;
    }

    public Integer getPrefClientId()
    {
        return this.prefClientId;
    }

    public void setPrefClientId(Integer prefClientId)
    {
        this.prefClientId = prefClientId;
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