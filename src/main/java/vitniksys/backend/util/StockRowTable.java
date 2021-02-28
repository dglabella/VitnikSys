package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.Repurchase;

public class StockRowTable
{
    private Integer unitCode;
    private Integer deliveryNumber;
    private Float price;
    private Float repurchasePrice;
    private String articleId;
    private String articleName;
    private ArticleType articleType;
    private Reason reason;

    public StockRowTable(Integer unitCode, Integer deliveryNumber, Float price, Float repurchasePrice, String articleId, 
        String articleName, ArticleType articleType, Reason reason)
    {
        this.unitCode = unitCode;
        this.deliveryNumber = deliveryNumber;
        this.price = price;
        this.repurchasePrice = repurchasePrice;
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleType = articleType;
        this.reason = reason;
    }

    public static List<StockRowTable> generateRows(List<Repurchase> repurchases)
    {
        List<StockRowTable> ret = new ArrayList<>();

        Repurchase repurchase = null;
        Iterator<Repurchase> it = repurchases.iterator();
        while(it.hasNext())
        {
            repurchase = it.next();

            ret.add( new StockRowTable(repurchase.getCode(), repurchase.getReturnedArticle().getOrder().getDeliveryNumber(), repurchase.getReturnedArticle().getOrder().getArticle().getUnitPrice(), 
                                        repurchase.getCost(), repurchase.getReturnedArticle().getOrder().getArticle().getId(), repurchase.getReturnedArticle().getOrder().getArticle().getName(), 
                                        repurchase.getReturnedArticle().getOrder().getArticle().getType(), repurchase.getReturnedArticle().getReason()));
        }

        return null;
    }

    //Getting && Setters
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

    public Float getPrice()
    {
        return this.price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Float getRepurchasePrice()
    {
        return this.repurchasePrice;
    }

    public void setRepurchasePrice(Float repurchasePrice)
    {
        this.repurchasePrice = repurchasePrice;
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

    public ArticleType getArticleType()
    {
        return this.articleType;
    }

    public void setArticleType(ArticleType articleType)
    {
        this.articleType = articleType;
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