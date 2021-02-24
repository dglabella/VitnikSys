package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class Devolution extends VitnikSearchableEntity
{
    //fk id
    private Integer unitCode;

    //Entity properties
    private int code;
    private float cost;
    private Timestamp registrationTime;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;
    private ReturnedArticle returnedArticle;

    //Others
    private boolean active;

    public Devolution(float cost)
    {
        this.cost = cost;
    }

    public Devolution(int code, float cost, Timestamp registrationTime)
    {
        this.code = code;
        this.cost = cost;
        this.registrationTime = registrationTime;
    }

    //Getters && Setters
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

    public Campaign getCampaign()
    {
        return this.campaign;
    }

    public void setCamp(Campaign campaign)
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
}