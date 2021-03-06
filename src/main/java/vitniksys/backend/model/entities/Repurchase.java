package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class Repurchase extends VitnikSearchableEntity
{
    //fk id
    private Integer returnedArticleId;

    //Entity properties
    private Integer code;
    private float cost;
    private boolean countForCommission;

    private boolean returned;
    private Timestamp registrationTime;

    //Domain Associations
    private PreferentialClient preferentialClient;
    private Campaign campaign;
    private ReturnedArticle returnedArticle;

    //Others
    private boolean active;

    public Repurchase(float cost)
    {
        this.cost = cost;
    }
   
    public Repurchase(int code, float cost, Timestamp registrationTime)
    {
        this.code = code;
        this.cost = cost;
        this.registrationTime = registrationTime;
    }

    //Getters && Setters
    public Integer getReturnedArticleId()
    {
        return this.returnedArticleId;
    }

    public void setReturnedArticleId(Integer returnedArticleId)
    {
        this.returnedArticleId = returnedArticleId;
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

    public boolean isCountForCommission()
    {
        return this.countForCommission;
    }

    public void setCountForCommission(boolean countForCommission)
    {
        this.countForCommission = countForCommission;
    }

    public boolean isReturned()
    {
        return this.returned;
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public PreferentialClient getPreferentialClient()
    {
        return this.preferentialClient;
    }
    
    public void setPreferentialClient(PreferentialClient preferentialClient)
    {
        this.preferentialClient = preferentialClient;
    }
    
    public Campaign getCampaign()
    {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign)
    {
        this.campaign = campaign;
    }

    public ReturnedArticle getReturnedArticle()
    {
        return this.returnedArticle;
    }
    
    public void setReturnedArticle(ReturnedArticle returnedArticle)
    {
        this.returnedArticle = returnedArticle;
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