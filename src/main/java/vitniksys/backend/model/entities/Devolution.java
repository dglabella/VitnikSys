package vitniksys.backend.model.entities;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.Reason;

public class Devolution extends VitnikSearchableEntity
{
    //fk id
    private String articleId;
    private Integer unitCode;

    //Entity properties
    private int code;
    private float cost;
    private Reason reason;
    private Timestamp registrationTime;

    //Domain Associations
    private PreferentialClient client;
    private Article article;
    private Campaign campaign;

    //Others
    private boolean active;

    public Devolution(float cost, Reason reason)
    {
        this.cost = cost;
        this.reason = reason;
    }

    public Devolution(int code, float cost, Reason reason, Timestamp registrationTime)
    {
        this.code = code;
        this.cost = cost;
        this.reason = reason;
        this.registrationTime = registrationTime;
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

    public Integer getUnitCode()
    {
        return this.unitCode;
    }

    public void setUnitCode(Integer unitCode)
    {
        this.unitCode = unitCode;
    }

    /**
     * 
     * @return return the BD table key (column name: cod).
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: cod).
     */
    public void setCode(int code)
    {
        this.code = code;
    }

    public float getCost()
    {
        return this.cost;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public Reason getReason()
    {
        return this.reason;
    }

    public void setReason(Reason reason)
    {
        this.reason = reason;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
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

    public void setCliente(PreferentialClient client)
    {
        this.client = client;
    }

    public Article getArticle()
    {
        return this.article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }

    public Campaign getCampaign()
    {
        return this.campaign;
    }

    public void setCamp(Campaign campaign)
    {
        this.campaign = campaign;
    }
}