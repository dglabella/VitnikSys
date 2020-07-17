package vitniksys.backend.model.entities;

public class Observacion
{
    //Entity properties
    private String observation;

    //Domain Associations
    private ClientePreferencial cliente;
    private Camp camp;

    //Others
    private boolean active;

    public Observacion(String observation)
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
     * @return return the corresponding  "cliente" whose id identifies, in part, to this "observacion".
     * The BD table key (column name: id_cp).
     */
    public ClientePreferencial getCliente()
    {
        return this.cliente;
    }

    /**
     * 
     * @param cliente set the corresponding  "cliente" whose id identifies, in part, to this "observacion".
     * The BD table key (column name: id_cp).
     */
    public void setCliente(ClientePreferencial cliente)
    {
        this.cliente = cliente;
    }

    /**
     * 
     * @return return the corresponding "camp" whose number identifies, in part, to this "observacion".
     * The BD table key (column name: nro_camp).
     */
    public Camp getCamp()
    {
        return this.camp;
    }

    /**
     * 
     * @param camp set the corresponding "camp" whose number identifies, in part, to this "observacion".
     * The BD table key (column name: nro_camp)
     */
    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }
}