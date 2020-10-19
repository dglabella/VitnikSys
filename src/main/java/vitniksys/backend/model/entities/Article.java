package vitniksys.backend.model.entities;

import vitniksys.backend.model.enums.ArticleType;

public class Article
{
    //Entity properties
    private String id; //Table id = letra
    private String name;
    private ArticleType type;
    private Float unitPrice;

    //Domain Associations


    //Others
    private boolean active;
    
    public Article(String id, String name, ArticleType type, Float unitPrice)
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

    public ArticleType getType()
    {
        return this.type;
    }

    public void setType(ArticleType type)
    {
        this.type = type;
    }

    public Float getUnitPrice()
    {
        return this.unitPrice;
    }

    public void setUnitPrice(Float unitPrice)
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