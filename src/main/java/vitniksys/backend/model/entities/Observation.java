package vitniksys.backend.model.entities;

public class Observation
{
    //Entity properties
    private String observation;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;

    //Others
    private boolean active;

    public Observation(String observation)
    {
        this.observation = observation;
    }

    //Getters && Setters
    public String getObservation()
    {
        return this.observation;
    }

    public void setObservation(String observation)
    {
        this.observation = observation;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    /**
     * 
     * @return return the corresponding client whose id identifies, in part, to this observation.
     * The BD table key (column name: id_cp).
     */
    public PreferentialClient getClient()
    {
        return this.client;
    }

    /**
     * 
     * @param cliente set the corresponding client whose id identifies, in part, to this observation.
     * The BD table key (column name: id_cp).
     */
    public void setCliente(PreferentialClient client)
    {
        this.client = client;
    }

    /**
     * 
     * @return return the corresponding campaign whose number identifies, in part, to this observation.
     * The BD table key (column name: nro_camp).
     */
    public Campaign getCampaign()
    {
        return this.campaign;
    }

    /**
     * 
     * @param camp set the corresponding campaign whose number identifies, in part, to this observation.
     * The BD table key (column name: nro_camp)
     */
    public void setCamp(Campaign campaign)
    {
        this.campaign = campaign;
    }
}