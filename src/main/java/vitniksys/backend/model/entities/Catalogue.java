package vitniksys.backend.model.entities;

import java.util.List;
import java.sql.Timestamp;

public class Catalogue
{
    //Entity properties
    private Integer code;
    private Integer initialStock;
    private Integer actualStock;
    private Float price;
    private String link;
    private Timestamp registrationTime;

    //Domain Associations
    private Campaign[] campaigns;
    private List<CatalogueDeliver> CatalogueDeliveries;

    //Others
    private boolean active;

    public Catalogue(Integer code, Integer initialStock, Float price)
    {
        this.code = code;
        this.initialStock = initialStock;
        this.price = price;   
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: cod).
     */
    public Integer getCode() 
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: cod).
     */
    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Integer getInitialStock()
    {
        return this.initialStock;
    }

    public void setInitialStock(int initialStock)
    {
        this.initialStock = initialStock;
    }

    public Float getPrice()
    {
        return this.price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getLink()
    {
        return this.link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public int getActualStock()
    {
        return this.actualStock;
    }

    public void setActualStock(int actualStock)
    {
        this.actualStock = actualStock;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public Campaign[] getCampaigns()
    {
        return this.campaigns;
    }

    public void setCampaigns(Campaign[] campaigns)
    {
        this.campaigns = campaigns;
    }

    public List<CatalogueDeliver> getCatalogueDeliveries()
    {
        return this.CatalogueDeliveries;
    }

    public void setCatalogueDeliveries(List<CatalogueDeliver> CatalogueDeliveries)
    {
        this.CatalogueDeliveries = CatalogueDeliveries;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public String toString()
    {
        return ""+this.code;
    }
}