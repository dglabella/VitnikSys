package vitniksys.backend.model;

import java.util.List;
import vitniksys.backend.persistence.ClientePreferencialOperator;
import vitniksys.backend.persistence.ClientePreferencialBaseOperator;

public class ClientePreferencialBase extends ClientePreferencial
{
    //Entity properties


    //Domain Associations
    private List<EntregaDeCatalogo> entregasDeCatalogo;

    //Others


    public ClientePreferencialBase(int id, String name, String lastName)
    {
        super(id, name, lastName);
    }

    public List<EntregaDeCatalogo> getEntregasDeCatalogos()
    {
        return this.entregasDeCatalogo;
    }

    public void setEntregaDeCatalogos(List<EntregaDeCatalogo> entregasDeCatalogo)
    {
        this.entregasDeCatalogo = entregasDeCatalogo;
    }

    @Override
    public ClientePreferencialOperator operator()
    {
        return ClientePreferencialBaseOperator.getOperator();
    }
}