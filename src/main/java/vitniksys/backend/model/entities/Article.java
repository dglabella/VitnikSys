package vitniksys.backend.model.entities;

public class Article
{
    //Entity properties
    private String id; //Table id = letra
    private String name;

    //Domain Associations


    //Others
    private boolean active;
    
    public Article(String id, String name)
    {
        this.id = id;
        this.name = name;
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

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}