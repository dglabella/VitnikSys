package vitniksys.backend.model.entities;

import vitniksys.backend.model.enums.TipoArt;

public class Articulo
{
    //Entity properties
    private String id; //Table id = letra
    private String name;
    private TipoArt type;
    private float unitPrice;

    //Domain Associations


    //Others
    private boolean active;
    
    public Articulo(String id, String name, TipoArt type, float unitPrice)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unitPrice = unitPrice;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: letra).
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * 
     * @param id set the BD table key (column name: letra).
     */
    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public TipoArt getType()
    {
        return this.type;
    }

    public void setType(TipoArt type)
    {
        this.type = type;
    }

    public float getUnitPrice()
    {
        return this.unitPrice;
    }

    public void setUnitPrice(float unitPrice)
    {
        this.unitPrice = unitPrice;
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