package vitniksys.backend.model.entities;

import java.util.List;

public class Catalogue
{
    //Entity properties
    private int code;
    private int initialStock;
    private float price;
    private String link;
    private int actualStock;

    //Domain Associations
    private Campaign[] campaigns;
    private List<CatalogueDeliver> CatalogueDeliveries;

    //Others
    private boolean active;

    public Catalogue(int code, int initialStock, float price)
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

    public int getInitialStock()
    {
        return this.initialStock;
    }

    public void setInitialStock(int initialStock)
    {
        this.initialStock = initialStock;
    }

    public float getPrice()
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

    public Campaign[] getCampaigns() {
        return this.campaigns;
    }

    public void setCampaigns(Campaign[] campaigns) {
        this.campaigns = campaigns;
    }

    public List<CatalogueDeliver> getCatalogueDeliveries() {
        return this.CatalogueDeliveries;
    }

    public void setCatalogueDeliveries(List<CatalogueDeliver> CatalogueDeliveries) {
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
}