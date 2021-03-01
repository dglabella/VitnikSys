package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.ReturnedArticle;

public class StockRowTable
{
    private Integer unitCode;
    private Integer deliveryNumber;
    private Float price;
    private String articleId;
    private String articleName;
    private ArticleType articleType;
    private Reason reason;

    public StockRowTable(Integer unitCode, Integer deliveryNumber, Float price, String articleId, String articleName, ArticleType articleType, Reason reason)
    {
        this.unitCode = unitCode;
        this.deliveryNumber = deliveryNumber;
        this.price = price;
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleType = articleType;
        this.reason = reason;
    }

    public static List<StockRowTable> generateRows(List<ReturnedArticle> returnedArticles)
    {
        List<StockRowTable> ret = new ArrayList<>();

        if(returnedArticles != null)
        {
            ReturnedArticle returnedArticle = null;
            Iterator<ReturnedArticle> it = returnedArticles.iterator();
            while(it.hasNext())
            {
                returnedArticle = it.next();

                ret.add(new StockRowTable(returnedArticle.getUnitCode(), returnedArticle.getOrder().getDeliveryNumber(), returnedArticle.getOrder().getArticle().getUnitPrice(), returnedArticle.getOrder().getArticle().getId(), 
                                            returnedArticle.getOrder().getArticle().getName(), returnedArticle.getOrder().getArticle().getType(), returnedArticle.getReason()));
            }
        }

        return ret;
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