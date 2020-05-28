package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.ClienteBaseOperator;
import vitniksys.backend.persistence.ClientePreferencialOperator;

public class ClienteBase extends ClientePreferencial
{
    //Entity properties


    //Domain Associations
    private List<Observacion> observaciones;

    //Others


    public ClienteBase(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    //Getters && Setters
    public List<Observacion> getObservaciones()
    {
        return this.observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones)
    {
        this.observaciones = observaciones;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClienteBaseOperator.getOperator();
    }
}