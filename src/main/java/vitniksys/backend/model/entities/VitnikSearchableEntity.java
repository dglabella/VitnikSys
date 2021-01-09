package vitniksys.backend.model.entities;

public abstract class VitnikSearchableEntity
{
    //fk id
    private Integer prefClientId;
    private Integer campNumber;

    //Getters && Setters
    public Integer getPrefClientId()
    {
        return this.prefClientId;
    }

    public void setPrefClientId(Integer prefClientId)
    {
        this.prefClientId = prefClientId;
    }

    public Integer getCampNumber()
    {
        return this.campNumber;
    }

    public void setCampNumber(Integer campNumber)
    {
        this.campNumber = campNumber;
    }
}