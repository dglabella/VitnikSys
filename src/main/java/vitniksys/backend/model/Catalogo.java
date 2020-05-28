package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.model.Camp;
import vitniksys.backend.model.EntregaCatalogo;

public class Catalogo
{
    //Entity properties
    private int code;
    private int initialStock;
    private float price;
    private String link;
    private int actualStock;

    //Domain Associations
    private Camp[] camps;
    private List<EntregaCatalogo> entregas;

    //Others
    private boolean active;

    public Catalogo(int code, int initialStock, float price)
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

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }   
}