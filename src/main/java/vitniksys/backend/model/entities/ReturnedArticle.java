package vitniksys.backend.model.entities;

import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.enums.ArticleType;

public class ReturnedArticle extends Article
{
    //fk id
    private String articleId;

    //Entity properties
    private int unitCode; //Table id = ejemplar
    private Reason reason;
    private boolean repurchased;

    //Others
    private boolean active;

    public ReturnedArticle(String id, String name, ArticleType type, float unitPrice, 
        int unitCode, Reason reason, boolean repurchased)
    {
        super(id, name, type, unitPrice);
        this.unitCode = unitCode;
        this.reason = reason;
        this.repurchased = repurchased;
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
     * @return return the BD table key (column name: ejemplar).
     */
    public int getUnitCode()
    {
        return this.unitCode;
    }

    /**
     * 
     * @param unitCode set the BD table key (column name: ejemplar).
     */
    public void setUnitCode(int unitCode)
    {
        this.unitCode = unitCode;
    }

    public Reason getReason()
    {
        return this.reason;
    }

    public void setReason(Reason reason)
    {
        this.reason = reason;
    }

    public boolean isRepurchased()
    {
        return this.repurchased;
    }

    public void setRepurchased(boolean repurchased)
    {
        this.repurchased = repurchased;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}